package lk.ijse.gdse66.hello.dao.custom.impl;


import lk.ijse.gdse66.hello.dao.SQLUtil;
import lk.ijse.gdse66.hello.dao.custom.OrderDetailDao;
import lk.ijse.gdse66.hello.entity.OrderDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public ArrayList<OrderDetail> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(connection,"SELECT * FROM orderDetail");
        ArrayList<OrderDetail> allItems = new ArrayList<>();
        while (rst.next()) {
            allItems.add(new OrderDetail(rst.getString(1), rst.getString(2), rst.getInt(3),rst.getDouble(4)));
        }
        return allItems;
    }

    @Override
    public boolean save(OrderDetail entity, Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"INSERT INTO orderDetail (ItemCode, orderID, qtyOnHand ,unitPrice) VALUES (?,?,?,?)", entity.getOrderID(), entity.getItemCode(), entity.getQty(), entity.getUnitPrice());
    }

    @Override
    public boolean update(OrderDetail dto, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String code, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}
