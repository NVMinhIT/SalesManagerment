package com.example.salesmanagerment.data.model.entity;

import com.example.salesmanagerment.utils.CommonFunc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderEntity {
    public Order order;
    public List<OrderDetail> orderDetails;

    public OrderEntity initOrderEntity() {
        orderDetails = new ArrayList<>();

        order = new Order.Builder().setOrderID(UUID.randomUUID().toString()).
                setCreatedDate(CommonFunc.getStringCurrentDateTime())
                .build();
        return this;
    }
}
