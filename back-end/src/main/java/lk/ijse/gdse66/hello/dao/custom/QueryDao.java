package lk.ijse.gdse66.hello.dao.custom;

import lk.ijse.gdse66.hello.bo.SuperBo;
import lk.ijse.gdse66.hello.entity.OrderJoinEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDao  extends SuperBo {
    ArrayList<OrderJoinEntity> searchOrderByOID(String oid, Connection connection) throws SQLException, ClassNotFoundException;

}
