package lk.ijse.gdse66.hello.dao.custom.impl;

import lk.ijse.gdse66.hello.dao.SQLUtil;
import lk.ijse.gdse66.hello.dao.custom.QueryDao;
import lk.ijse.gdse66.hello.entity.OrderJoinEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class QueryDaoImpl implements QueryDao {
    @Override
    public ArrayList<OrderJoinEntity> searchOrderByOID(String oid, Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<OrderJoinEntity> allRecords = new ArrayList<>();
        String sql = "select orders.orderID,orders.date,orders.cusId,orderDetail.orderID,orderDetail.ItemCode,orderDetail.qtyOnHand,orderDetail.unitPrice from orders inner join orderDetail on orders.oid=OrderDetails.oid where orders.oid=?";
        ResultSet rst = SQLUtil.execute(connection,sql, oid);
        while (rst.next()) {
            String orderID = rst.getString(1);
            LocalDate orderDate = LocalDate.parse(rst.getString(2));
            String customerID = rst.getString(3);
            String itemCode = rst.getString(5);
            int itemQty = rst.getInt(6);
            double unitPrice = rst.getDouble(7);
            OrderJoinEntity joinEntity = new OrderJoinEntity(orderID, orderDate, customerID, itemCode, itemQty, unitPrice);
            allRecords.add(joinEntity);
        }
        return allRecords;
    }
}
