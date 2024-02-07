package lk.ijse.gdse66.hello.bo.custom;

import lk.ijse.gdse66.hello.bo.SuperBo;
import lk.ijse.gdse66.hello.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo extends SuperBo {
    ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException ;

    boolean deleteItem(String code ,Connection connection) throws SQLException, ClassNotFoundException ;

    boolean saveItem(ItemDTO dto,Connection connection) throws SQLException, ClassNotFoundException ;

    boolean updateItem(ItemDTO dto,Connection connection) throws SQLException, ClassNotFoundException;

}
