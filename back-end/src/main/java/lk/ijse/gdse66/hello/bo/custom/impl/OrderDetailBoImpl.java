package lk.ijse.gdse66.hello.bo.custom.impl;

import lk.ijse.gdse66.hello.bo.custom.OrderDetailBo;
import lk.ijse.gdse66.hello.dao.DAOFactory;
import lk.ijse.gdse66.hello.dao.custom.OrderDetailDao;
import lk.ijse.gdse66.hello.dto.OrderDetailDTO;
import lk.ijse.gdse66.hello.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBoImpl implements OrderDetailBo {
    OrderDetailDao detailsDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public ArrayList<OrderDetailDTO> getAllDetails(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailDTO> detailDTOS = new ArrayList<>();
        ArrayList<OrderDetail> details = detailsDAO.getAll(connection);
        for (OrderDetail i : details) {
            detailDTOS.add(new OrderDetailDTO(i.getOrderID(), i.getItemCode(), i.getQty(), i.getUnitPrice()));
        }
        return detailDTOS;
    }
}
