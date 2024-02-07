package lk.ijse.gdse66.hello.dto;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OrderDTO {

    private String orderID;
    private LocalDate date;
    private String customerID;

    public OrderDTO(String orderID, LocalDate date, String customerID, List<OrderDetailDTO> orderDetailsDTOList) {
        this.orderID = orderID;
        this.date = date;
        this.customerID = customerID;
        this.orderDetailsDTOList = orderDetailsDTOList;
    }
    public OrderDTO(String orderID, LocalDate date, String customerID) {
        this.orderID = orderID;
        this.date = date;
        this.customerID = customerID;
    }
    public List<OrderDetailDTO> getOrderDetailsDTOList() {
        return orderDetailsDTOList;
    }

    public void setOrderDetailsDTOList(List<OrderDetailDTO> orderDetailsDTOList) {
        this.orderDetailsDTOList = orderDetailsDTOList;
    }

    List<OrderDetailDTO> orderDetailsDTOList;


    public OrderDTO() {
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderID='" + orderID + '\'' +
                ", date=" + date +
                ", customerID='" + customerID + '\'' +
                ", orderDetailsDTOList=" + orderDetailsDTOList +
                '}';
    }
}
