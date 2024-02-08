package lk.ijse.gdse66.hello.api;

import jakarta.json.*;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse66.hello.bo.BoFactory;
import lk.ijse.gdse66.hello.bo.custom.OrderDetailBo;
import lk.ijse.gdse66.hello.bo.custom.PurchaseOrderBo;
import lk.ijse.gdse66.hello.dto.OrderDTO;
import lk.ijse.gdse66.hello.dto.OrderDetailDTO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/orders")
//@WebServlet(name = "purchaseOrderServlet",urlPatterns = "/orders")
public class PurchaseOrderServlet extends HttpServlet {

    PurchaseOrderBo purchaseOrderBo= BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PURCHASE_ORDER);
    private DataSource source;

    @Override
    public void init() throws ServletException {
        try {
            source = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/pos");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Jsonb jsonb = JsonbBuilder.create();
        OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);
        String id = orderDTO.getOrderID();
        LocalDate date = orderDTO.getDate();
        String customerId = orderDTO.getCustomerID();
        List<OrderDetailDTO> detaisList = orderDTO.getOrderDetailsDTOList();


        if(id==null || !id.matches("/^(ORD-)[0-9]{3}$/")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is empty or invalid");
            return;
        } else if (date == null ) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Date is empty or invalid");
            return;
        } else if (customerId == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "CustomerId is empty or invalid");
            return;
        }else if ( detaisList==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Detail list is empty or invalid");
            return;
        }

        try {

            boolean saveOrder = purchaseOrderBo.saveOrder(new OrderDTO(id, date, customerId, detaisList),source);
            if (saveOrder) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Added order successfully");
            }else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save the order");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}


