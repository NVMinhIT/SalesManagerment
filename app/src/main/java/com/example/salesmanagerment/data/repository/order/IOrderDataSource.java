package com.example.salesmanagerment.data.repository.order;

import com.example.salesmanagerment.data.model.entity.Order;
import com.example.salesmanagerment.data.model.entity.OrderDetail;

import java.util.List;

public interface IOrderDataSource {

    List<Order> getAllOrder();

    boolean cancelOrder(String billId);

    List<OrderDetail> initNewOrderDetailList(String orderID);

    void addBill(Order order, List<OrderDetail> orderDetails);
}
