package lk.ijse.gdse66.hello.dao.custom.impl;


import lk.ijse.gdse66.hello.dao.SQLUtil;
import lk.ijse.gdse66.hello.dao.custom.OrderDao;
import lk.ijse.gdse66.hello.entity.Orders;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public ArrayList<Orders> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Orders entity, Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"INSERT INTO `orders` (orderID, date, CusId) VALUES (?,?,?)", entity.getOrderID(), Date.valueOf(entity.getDate()), entity.getCustomerID());

    }

    @Override
    public boolean update(Orders dto, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String orderID, Connection connection) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute(connection,"SELECT orderID FROM `orders` WHERE orderID=?", orderID);
        return rst.next();
    }

    @Override
    public Orders search(String code, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }
}