package lk.ijse.gdse66.hello.entity;

import java.time.LocalDate;

public class OrderJoinEntity {
    private String orderID;
    private LocalDate orderDate;
    private String cusId;
    private String ItemCode;
    private int qtyOnHand;
    private double unitPrice;

    public OrderJoinEntity() {
    }

    public OrderJoinEntity(String orderID, LocalDate orderDate, String cusId, String itemCode, int qtyOnHand, double unitPrice) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.cusId = cusId;
        ItemCode = itemCode;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
