package lk.ijse.gdse66.hello.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse66.hello.bo.BoFactory;
import lk.ijse.gdse66.hello.bo.custom.ItemBo;
import lk.ijse.gdse66.hello.dto.ItemDTO;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/items")
public class ItemServlet extends HttpServlet {

    ItemBo itemBO= BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ITEM);
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

        try (Connection connection = source.getConnection();){
            ArrayList<ItemDTO> allitems = itemBO.getAllItems(connection);
            resp.setContentType("application/json");
            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(allitems,resp.getWriter());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
        String code = itemDTO.getItemCode();
        String description = itemDTO.getItemName();
        double unitPrice = itemDTO.getItemQty();
        int qty = itemDTO.getItemQty();


        if(code==null || !code.matches("^(P0)[0-9]{3}$")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Item Code is empty or invalid");
            return;
        } else if (description == null || !description.matches("^[A-Za-z ]{5,}$")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Description is empty or invalid");
            return;
        } else if (unitPrice < 0.0) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unit Price is empty or invalid");
            return;
        }else if (qty < 0 ){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quantity is empty or invalid");
            return;
        }

        try (Connection connection = source.getConnection();){

            boolean saveItem = itemBO.saveItem(new ItemDTO(code,description,unitPrice,qty), connection);
            if (saveItem) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Added item successfully");
            }else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save the item");
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
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
        String code = itemDTO.getItemCode();
        String description = itemDTO.getItemName();
        double unitPrice = itemDTO.getItemPrice();
        int qty = itemDTO.getItemQty();

        try (Connection connection = source.getConnection();) {

            boolean updateItem = itemBO.updateItem(new ItemDTO(code,description,unitPrice,qty), connection);
            if (updateItem) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Updated item successfully");
            }else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update the item");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("itemCode");

        try (Connection connection = source.getConnection();){
            boolean deleteItem = itemBO.deleteItem(id, connection);
            if (deleteItem) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete the item!");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    }
