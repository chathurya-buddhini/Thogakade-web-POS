package lk.ijse.gdse66.hello.bo.custom;

import lk.ijse.gdse66.hello.bo.SuperBo;
import lk.ijse.gdse66.hello.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBo extends SuperBo {
    ArrayList<OrderDetailDTO> getAllDetails(Connection connection) throws SQLException, ClassNotFoundException;

}
