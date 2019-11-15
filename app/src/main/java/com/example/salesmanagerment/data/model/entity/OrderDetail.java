package com.example.salesmanagerment.data.model.entity;

public class OrderDetail {
    public String OrderDetailID;
    public String OrderID;
    public String InventoryItemID;
    public String InventoryItemAdditionID;
    public Double Quantity;
    public String QuantityAddition;
    public String Description;
    public int SortOrder;
    public Double CookedQuantity;
    public Double ServedQuantity;
    public Double CookingQuantity;
    public int OrderDetailStatus;
    public String CancelEmployeeID;
    public String SendKitchenID;

    public OrderDetail(Builder builder) {
        OrderDetailID = builder.OrderDetailID;
        OrderID= builder.OrderID;
        InventoryItemID= builder.InventoryItemID;
        InventoryItemAdditionID= builder.InventoryItemAdditionID;
        Quantity= builder.Quantity;
        QuantityAddition= builder.QuantityAddition;
        Description= builder.Description;
        SortOrder= builder.SortOrder;
        ServedQuantity= builder.ServedQuantity;
        CookingQuantity= builder.CookingQuantity;
        OrderDetailStatus= builder.OrderDetailStatus;
        CancelEmployeeID= builder.CancelEmployeeID;
        SendKitchenID= builder.SendKitchenID;
    }

    public static class Builder {
        public String OrderDetailID;
        public String OrderID;
        public String InventoryItemID;
        public String InventoryItemAdditionID;
        public Double Quantity;
        public String QuantityAddition;
        public String Description;
        public int SortOrder;
        public Double CookedQuantity;
        public Double ServedQuantity;
        public Double CookingQuantity;
        public int OrderDetailStatus;
        public String CancelEmployeeID;
        public String SendKitchenID;

        public Builder setOrderDetailID(String orderDetailID) {
            OrderDetailID = orderDetailID;
            return this;
        }

        public Builder setOrderID(String orderID) {
            OrderID = orderID;
            return this;
        }

        public Builder setInventoryItemID(String inventoryItemID) {
            InventoryItemID = inventoryItemID;
            return this;
        }

        public Builder setInventoryItemAdditionID(String inventoryItemAdditionID) {
            InventoryItemAdditionID = inventoryItemAdditionID;
            return this;
        }

        public Builder setQuantity(Double quantity) {
            Quantity = quantity;
            return this;
        }

        public Builder setQuantityAddition(String quantityAddition) {
            QuantityAddition = quantityAddition;
            return this;
        }

        public Builder setDescription(String description) {
            Description = description;
            return this;
        }

        public Builder setSortOrder(int sortOrder) {
            SortOrder = sortOrder;
            return this;
        }

        public Builder setCookedQuantity(Double cookedQuantity) {
            CookedQuantity = cookedQuantity;
            return this;
        }

        public Builder setServedQuantity(Double servedQuantity) {
            ServedQuantity = servedQuantity;
            return this;
        }

        public Builder setCookingQuantity(Double cookingQuantity) {
            CookingQuantity = cookingQuantity;
            return this;
        }

        public Builder setOrderDetailStatus(int orderDetailStatus) {
            OrderDetailStatus = orderDetailStatus;
            return this;
        }

        public Builder setCancelEmployeeID(String cancelEmployeeID) {
            CancelEmployeeID = cancelEmployeeID;
            return this;
        }

        public Builder setSendKitchenID(String sendKitchenID) {
            SendKitchenID = sendKitchenID;
            return this;
        }

        public OrderDetail build() {
            return new OrderDetail(this);
        }
    }
}
