package lk.ijse.gdse66.hello.bo.custom;


import lk.ijse.gdse66.hello.bo.SuperBo;
import lk.ijse.gdse66.hello.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBo {

    ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException;

    boolean saveCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id,Connection connection) throws SQLException, ClassNotFoundException;



}
