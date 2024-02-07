package lk.ijse.gdse66.hello.bo.custom.impl;

import lk.ijse.gdse66.hello.bo.custom.ItemBo;
import lk.ijse.gdse66.hello.dao.CrudDao;
import lk.ijse.gdse66.hello.dao.DAOFactory;
import lk.ijse.gdse66.hello.dao.custom.ItemDao;
import lk.ijse.gdse66.hello.dto.ItemDTO;
import lk.ijse.gdse66.hello.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {

    ItemDao itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> list = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll(connection);
        for (Item i : all) {
            list.add(new ItemDTO(i.getItemCode(), i.getItemName(), i.getItemPrice(), i.getItemQty()));
        }
        return list;
    }

    @Override
    public boolean deleteItem(String code,Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code,connection);    }

    @Override
    public boolean saveItem(ItemDTO dto,Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItemCode(), dto.getItemName(), dto.getItemPrice(), dto.getItemQty()),connection);
    }

    @Override
    public boolean updateItem(ItemDTO dto,Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getItemCode(), dto.getItemName(), dto.getItemPrice(), dto.getItemQty()),connection);
    }

}
