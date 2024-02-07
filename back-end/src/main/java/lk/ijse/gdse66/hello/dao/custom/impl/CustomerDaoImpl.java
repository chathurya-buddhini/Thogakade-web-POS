package lk.ijse.gdse66.hello.dao.custom.impl;


import lk.ijse.gdse66.hello.dao.SQLUtil;
import lk.ijse.gdse66.hello.dao.custom.CustomerDao;
import lk.ijse.gdse66.hello.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public ArrayList<Customer> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute(connection,"SELECT * FROM customer");
        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3),rst.getDouble(4)));
        }
        return allCustomers;
    }


    @Override
    public boolean save(Customer entity,Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"INSERT INTO customer (id,name, address,salary) VALUES (?,?,?,?)", entity.getCusId(), entity.getCusName(), entity.getCusAddress() ,entity.getCusSalary());
    }

    @Override
    public boolean update(Customer entity,Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"UPDATE customer SET name=?, address=?, salary=? WHERE id=?", entity.getCusName(), entity.getCusAddress(), entity.getCusSalary(),entity.getCusId());
    }

    @Override
    public boolean delete(String code,Connection connection) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"DELETE FROM customer WHERE id=?", code);
    }

    @Override
    public boolean exist(String s, Connection connection) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Customer search(String code, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

}
