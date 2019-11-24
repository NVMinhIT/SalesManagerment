package com.example.salesmanagerment.data.model.request;

public class CancelOrderRequest {
    String OrderID;
    String CancelReason;

    public CancelOrderRequest(String orderID, String cancelReason){
        OrderID = orderID;
        CancelReason = cancelReason;
    }
}
