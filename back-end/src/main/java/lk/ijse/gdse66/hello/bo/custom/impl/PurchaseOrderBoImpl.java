package lk.ijse.gdse66.hello.bo.custom.impl;


import lk.ijse.gdse66.hello.bo.custom.PurchaseOrderBo;
import lk.ijse.gdse66.hello.dao.DAOFactory;
import lk.ijse.gdse66.hello.dao.custom.ItemDao;
import lk.ijse.gdse66.hello.dao.custom.OrderDao;
import lk.ijse.gdse66.hello.dao.custom.OrderDetailDao;
import lk.ijse.gdse66.hello.dto.CustomerDTO;
import lk.ijse.gdse66.hello.dto.ItemDTO;
import lk.ijse.gdse66.hello.dto.OrderDTO;
import lk.ijse.gdse66.hello.dto.OrderDetailDTO;
import lk.ijse.gdse66.hello.entity.Item;
import lk.ijse.gdse66.hello.entity.OrderDetail;
import lk.ijse.gdse66.hello.entity.Orders;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBoImpl implements PurchaseOrderBo {

    OrderDao orderDAO= (OrderDao) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDao orderDetailsDAO = (OrderDetailDao) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    ItemDao itemDAO = (ItemDao) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean saveOrder(OrderDTO dto, DataSource source) throws SQLException, ClassNotFoundException {

        try {

            Connection connection = source.getConnection();

            if (orderDAO.exist(dto.getOrderID(),source.getConnection())){
                return false;
            }

            connection.setAutoCommit(false);

            Orders orderEntity = new Orders(dto.getOrderID(), dto.getDate(), dto.getCustomerID());
            boolean orderAdded = orderDAO.save(orderEntity,source.getConnection());
            if (!orderAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO odDTO : dto.getOrderDetailsDTOList()) {
                OrderDetail orderDetailsEntity = new OrderDetail(odDTO.getItemCode(), odDTO.getOrderID(), odDTO.getQty(), odDTO.getUnitPrice());
                boolean odAdded = orderDetailsDAO.save(orderDetailsEntity, source.getConnection());
                if (!odAdded) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                ItemDTO item = findItemByID(orderDetailsEntity.getItemCode(), source.getConnection());
                item.setItemQty(item.getItemQty() - orderDetailsEntity.getQty());
                boolean itemUpdate = itemDAO.update(new Item(item.getItemCode(), item.getItemName(), item.getItemPrice(), item.getItemQty()), source.getConnection());

                if (!itemUpdate) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ItemDTO findItemByID(String code,Connection connection) {
        try {
            Item search = itemDAO.search(code,connection);
            return new ItemDTO(search.getItemCode(),search.getItemName(),search.getItemPrice(),search.getItemQty());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
