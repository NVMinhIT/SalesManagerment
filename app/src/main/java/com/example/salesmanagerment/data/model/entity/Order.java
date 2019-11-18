package com.example.salesmanagerment.data.model.entity;

import java.util.Date;

public class Order {

    public static final int ARE_SERVING = 0; //đang phục vụ
    public static final int PAYMENT_REQUEST = 1; //yc thanh toán
    public static final int PAYMENT = 2; //đã thanh toán
    public static final int CANCEL = 3; //đã hủy

    public String OrderID;
    public int OrderStatus;
    public String OrderDate;
    public String BranchID;
    public String CustomerID;
    public int NumberOfPeople;
    public String BookingID;
    public String EmployeeID;
    public String CancelEmployeeID;
    public String CancelReason;
    public String TableID;
    public String CreatedDate;
    public String CreatedBy;
    public String ModifiedDate;
    public String ModifiedBy;

    public Order(Builder builder) {
        OrderID = builder.OrderID;
        OrderStatus = builder.OrderStatus;
        OrderDate = builder.OrderDate;
        BranchID = builder.BranchID;
        CustomerID = builder.CustomerID;
        NumberOfPeople = builder.NumberOfPeople;
        BookingID = builder.BookingID;
        EmployeeID = builder.EmployeeID;
        CancelEmployeeID = builder.CancelEmployeeID;
        CancelReason = builder.CancelReason;
        TableID = builder.TableID;
        CreatedDate = builder.CreatedDate;
        CreatedBy = builder.CreatedBy;
        ModifiedDate = builder.ModifiedDate;
        ModifiedBy = builder.ModifiedBy;
    }

    public static class Builder {
        public Builder setOrderID(String orderID) {
            OrderID = orderID;
            return this;
        }

        public Builder setOrderStatus(int orderStatus) {
            OrderStatus = orderStatus;
            return this;
        }

        public Builder setOrderDate(String orderDate) {
            OrderDate = orderDate;
            return this;
        }

        public Builder setBranchID(String branchID) {
            BranchID = branchID;
            return this;
        }

        public Builder setCustomerID(String customerID) {
            CustomerID = customerID;
            return this;
        }

        public Builder setNumberOfPeople(int numberOfPeople) {
            NumberOfPeople = numberOfPeople;
            return this;
        }

        public Builder setBookingID(String bookingID) {
            BookingID = bookingID;
            return this;
        }

        public Builder setEmployeeID(String employeeID) {
            EmployeeID = employeeID;
            return this;
        }

        public Builder setCancelEmployeeID(String cancelEmployeeID) {
            CancelEmployeeID = cancelEmployeeID;
            return this;
        }

        public Builder setCancelReason(String cancelReason) {
            CancelReason = cancelReason;
            return this;
        }

        public Builder setTableID(String tableID) {
            TableID = tableID;
            return this;
        }

        public Builder setCreatedDate(String createdDate) {
            CreatedDate = createdDate;
            return this;
        }

        public Builder setCreatedBy(String createdBy) {
            CreatedBy = createdBy;
            return this;
        }

        public Builder setModifiedDate(String modifiedDate) {
            ModifiedDate = modifiedDate;
            return this;
        }

        public Builder setModifiedBy(String modifiedBy) {
            ModifiedBy = modifiedBy;
            return this;
        }

        public Order build() {
            return new Order(this);
        }

        public String OrderID;
        public int OrderStatus;
        public String OrderDate;
        public String BranchID;
        public String CustomerID;
        public int NumberOfPeople;
        public String BookingID;
        public String EmployeeID;
        public String CancelEmployeeID;
        public String CancelReason;
        public String TableID;
        public String CreatedDate;
        public String CreatedBy;
        public String ModifiedDate;
        public String ModifiedBy;
    }
}
