package com.example.salesmanagerment.data.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderDetail implements Parcelable {
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
        CookedQuantity = builder.CookedQuantity;
        ServedQuantity= builder.ServedQuantity;
        CookingQuantity= builder.CookingQuantity;
        OrderDetailStatus= builder.OrderDetailStatus;
        CancelEmployeeID= builder.CancelEmployeeID;
        SendKitchenID= builder.SendKitchenID;
    }

    protected OrderDetail(Parcel in) {
        OrderDetailID = in.readString();
        OrderID = in.readString();
        InventoryItemID = in.readString();
        InventoryItemAdditionID = in.readString();
        if (in.readByte() == 0) {
            Quantity = null;
        } else {
            Quantity = in.readDouble();
        }
        QuantityAddition = in.readString();
        Description = in.readString();
        SortOrder = in.readInt();
        if (in.readByte() == 0) {
            CookedQuantity = null;
        } else {
            CookedQuantity = in.readDouble();
        }
        if (in.readByte() == 0) {
            ServedQuantity = null;
        } else {
            ServedQuantity = in.readDouble();
        }
        if (in.readByte() == 0) {
            CookingQuantity = null;
        } else {
            CookingQuantity = in.readDouble();
        }
        OrderDetailStatus = in.readInt();
        CancelEmployeeID = in.readString();
        SendKitchenID = in.readString();
    }

    public static final Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {
        @Override
        public OrderDetail createFromParcel(Parcel in) {
            return new OrderDetail(in);
        }

        @Override
        public OrderDetail[] newArray(int size) {
            return new OrderDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(OrderDetailID);
        dest.writeString(OrderID);
        dest.writeString(InventoryItemID);
        dest.writeString(InventoryItemAdditionID);
        if (Quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Quantity);
        }
        dest.writeString(QuantityAddition);
        dest.writeString(Description);
        dest.writeInt(SortOrder);
        if (CookedQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(CookedQuantity);
        }
        if (ServedQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(ServedQuantity);
        }
        if (CookingQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(CookingQuantity);
        }
        dest.writeInt(OrderDetailStatus);
        dest.writeString(CancelEmployeeID);
        dest.writeString(SendKitchenID);
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
