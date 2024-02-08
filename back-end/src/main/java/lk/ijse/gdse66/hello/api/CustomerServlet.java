package lk.ijse.gdse66.hello.api;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import jakarta.json.*;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse66.hello.bo.BoFactory;
import lk.ijse.gdse66.hello.bo.custom.CustomerBo;
import lk.ijse.gdse66.hello.dto.CustomerDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//@WebServlet(urlPatterns = "/customers")
 @WebServlet(name = "customerServlet",urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {


    CustomerBo customerBO= BoFactory.getBoFactory().getBO(BoFactory.BOTypes.CUSTOMER);
    private DataSource source;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ic = new InitialContext();
            source = (DataSource) ic.lookup("java:/comp/env/jdbc/pos");

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = source.getConnection();
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers(connection);
            resp.setContentType("application/json");
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(allCustomers,resp.getWriter());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
        String id = customerDTO.getCusId();
        String name = customerDTO.getCusName();
        String address = customerDTO.getCusAddress();
        double salary = customerDTO.getCusSalary();


        if(id==null || !id.matches("C\\d{3}")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is empty or invalid");
            return;
        } else if (name == null || !name.matches("[A-Za-z ]+")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name is empty or invalid");
            return;
        } else if (address == null || address.length() < 3) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Address is empty or invalid");
            return;
        }else if (salary < 0.0 ){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Salary is empty or invalid");
            return;
        }
        System.out.printf("id=%s, name=%s, address=%s,salary=%s \n", id,name,address,salary);
System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
        try {

            Connection connection = source.getConnection();
            boolean saveCustomer = customerBO.saveCustomer(new CustomerDTO(id,name,address,salary),connection);
            if (saveCustomer) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Added customer successfully");
            }else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save the customer");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
        String id = customerDTO.getCusId();
        String name = customerDTO.getCusName();
        String address = customerDTO.getCusAddress();
        double salary = customerDTO.getCusSalary();

        try {

            Connection connection = source.getConnection();

            boolean updateCustomer = customerBO.updateCustomer(new CustomerDTO(id, name, address, salary),connection);
            if (updateCustomer) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Updated customer successfully");
            }else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update the customer");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        try {
            Connection connection = source.getConnection();
            boolean deleteCustomer = customerBO.deleteCustomer(id,connection);
            if(deleteCustomer){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else{
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete the customer!");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


}
