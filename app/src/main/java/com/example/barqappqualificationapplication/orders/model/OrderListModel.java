package com.example.barqappqualificationapplication.orders.model;

public class OrderListModel {

    private String orderNumber;
    private String orderDescription;
    private String orderPrice;
    private String orderStatus;

    public OrderListModel(String orderNumber, String orderDescription, String orderPrice, String orderStatus) {
        this.orderNumber = orderNumber;
        this.orderDescription = orderDescription;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
