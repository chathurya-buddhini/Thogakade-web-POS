package lk.ijse.gdse66.hello.bo.custom;


import lk.ijse.gdse66.hello.bo.SuperBo;
import lk.ijse.gdse66.hello.dto.CustomerDTO;
import lk.ijse.gdse66.hello.dto.ItemDTO;
import lk.ijse.gdse66.hello.dto.OrderDTO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PurchaseOrderBo extends SuperBo {
    boolean saveOrder(OrderDTO dto, DataSource source) throws SQLException, ClassNotFoundException;


}
