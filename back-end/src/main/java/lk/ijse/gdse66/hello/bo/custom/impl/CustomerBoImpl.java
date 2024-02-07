package lk.ijse.gdse66.hello.bo.custom.impl;


import lk.ijse.gdse66.hello.bo.custom.CustomerBo;
import lk.ijse.gdse66.hello.dao.DAOFactory;
import lk.ijse.gdse66.hello.dao.custom.CustomerDao;
import lk.ijse.gdse66.hello.dto.CustomerDTO;
import lk.ijse.gdse66.hello.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {

    CustomerDao customerDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll(connection);
        ArrayList<CustomerDTO> arrayList= new ArrayList<>();
        for (Customer c : all) {
            arrayList.add(new CustomerDTO(c.getCusId(),c.getCusName(),c.getCusAddress(),c.getCusSalary()));
        }
        return arrayList;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.save( new Customer(dto.getCusId(), dto.getCusName(),dto.getCusAddress() , dto.getCusSalary()),connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto,Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.update( new Customer(dto.getCusId(), dto.getCusName(),dto.getCusAddress() , dto.getCusSalary()),connection);
    }

    @Override
    public boolean deleteCustomer(String id,Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id,connection);
    }
}
