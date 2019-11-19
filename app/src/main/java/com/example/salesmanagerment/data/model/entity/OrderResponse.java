package com.example.salesmanagerment.data.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderResponse {
    @SerializedName("OrderID")
    @Expose
    private String orderID;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("OrderStatus")
    @Expose
    private Integer orderStatus;
    @SerializedName("OrderDate")
    @Expose
    private String orderDate;
    @SerializedName("BranchID")
    @Expose
    private Object branchID;
    @SerializedName("CustomerID")
    @Expose
    private Object customerID;
    @SerializedName("NumberOfPeople")
    @Expose
    private Integer numberOfPeople;
    @SerializedName("BookingID")
    @Expose
    private Object bookingID;
    @SerializedName("EmployeeID")
    @Expose
    private Object employeeID;
    @SerializedName("CancelEmployeeID")
    @Expose
    private Object cancelEmployeeID;
    @SerializedName("CancelReason")
    @Expose
    private String cancelReason;
    @SerializedName("TableID")
    @Expose
    private String tableID;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("ModifiedBy")
    @Expose
    private String modifiedBy;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Object getBranchID() {
        return branchID;
    }

    public void setBranchID(Object branchID) {
        this.branchID = branchID;
    }

    public Object getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Object customerID) {
        this.customerID = customerID;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Object getBookingID() {
        return bookingID;
    }

    public void setBookingID(Object bookingID) {
        this.bookingID = bookingID;
    }

    public Object getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Object employeeID) {
        this.employeeID = employeeID;
    }

    public Object getCancelEmployeeID() {
        return cancelEmployeeID;
    }

    public void setCancelEmployeeID(Object cancelEmployeeID) {
        this.cancelEmployeeID = cancelEmployeeID;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
