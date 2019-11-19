package com.example.salesmanagerment.data.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.salesmanagerment.utils.CommonFunc;

import java.util.Date;

public class Order implements Parcelable {

    public static final int ARE_SERVING = 0; //đang phục vụ
    public static final int PAYMENT_REQUEST = 1; //yc thanh toán
    public static final int PAYMENT = 2; //đã thanh toán
    public static final int CANCEL = 3; //đã hủy

    public String OrderID;
    public int OrderStatus;
    public String OrderDate = CommonFunc.getStringCurrentDateTime();;
    public String BranchID;
    public String CustomerID;
    public int NumberOfPeople;
    public String BookingID;
    public String EmployeeID;
    public String CancelEmployeeID;
    public String CancelReason;
    public String TableID;
    public String CreatedDate = CommonFunc.getStringCurrentDateTime();;
    public String CreatedBy;
    public String ModifiedDate = CommonFunc.getStringCurrentDateTime();
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

    protected Order(Parcel in) {
        OrderID = in.readString();
        OrderStatus = in.readInt();
        OrderDate = in.readString();
        BranchID = in.readString();
        CustomerID = in.readString();
        NumberOfPeople = in.readInt();
        BookingID = in.readString();
        EmployeeID = in.readString();
        CancelEmployeeID = in.readString();
        CancelReason = in.readString();
        TableID = in.readString();
        CreatedDate = in.readString();
        CreatedBy = in.readString();
        ModifiedDate = in.readString();
        ModifiedBy = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(OrderID);
        dest.writeInt(OrderStatus);
        dest.writeString(OrderDate);
        dest.writeString(BranchID);
        dest.writeString(CustomerID);
        dest.writeInt(NumberOfPeople);
        dest.writeString(BookingID);
        dest.writeString(EmployeeID);
        dest.writeString(CancelEmployeeID);
        dest.writeString(CancelReason);
        dest.writeString(TableID);
        dest.writeString(CreatedDate);
        dest.writeString(CreatedBy);
        dest.writeString(ModifiedDate);
        dest.writeString(ModifiedBy);
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
