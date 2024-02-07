package lk.ijse.gdse66.hello.dto;

public class OrderDetailDTO {
    private String orderID;
    private String itemCode;
    private int qty;
    private double unitPrice;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderID, String itemCode, int qty, double unitPrice) {
        this.orderID = orderID;
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", orderID='" + orderID + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
