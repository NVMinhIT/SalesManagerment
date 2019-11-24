package com.example.salesmanagerment.data.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.salesmanagerment.utils.CommonFunc;

public class OrderResponse implements Parcelable {
    public String OrderID;
    public String OrderNo;
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
    public String CustomerCode;
    public String CustomerName;
    public String Birthday;
    public String Email;
    public String Mobile;
    public String Address;
    public String TableName;
    public String AreaName;
    public Double TotalAmount;

    protected OrderResponse(Parcel in) {
        OrderID = in.readString();
        OrderNo = in.readString();
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
        CustomerCode = in.readString();
        CustomerName = in.readString();
        Birthday = in.readString();
        Email = in.readString();
        Mobile = in.readString();
        Address = in.readString();
        TableName = in.readString();
        AreaName = in.readString();
        if (in.readByte() == 0) {
            TotalAmount = null;
        } else {
            TotalAmount = in.readDouble();
        }
    }

    public static final Creator<OrderResponse> CREATOR = new Creator<OrderResponse>() {
        @Override
        public OrderResponse createFromParcel(Parcel in) {
            return new OrderResponse(in);
        }

        @Override
        public OrderResponse[] newArray(int size) {
            return new OrderResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(OrderID);
        dest.writeString(OrderNo);
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
        dest.writeString(CustomerCode);
        dest.writeString(CustomerName);
        dest.writeString(Birthday);
        dest.writeString(Email);
        dest.writeString(Mobile);
        dest.writeString(Address);
        dest.writeString(TableName);
        dest.writeString(AreaName);
        if (TotalAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(TotalAmount);
        }
    }

    public Order getOrder() {
        return new Order.Builder()
                .setOrderID(OrderID)
                .setOrderNo(OrderNo)
                .setOrderStatus(OrderStatus)
                .setOrderDate(OrderDate)
                .setBranchID(BranchID)
                .setCustomerID(CustomerID)
                .setNumberOfPeople(NumberOfPeople)
                .setBookingID(BookingID)
                .setEmployeeID(EmployeeID)
                .setCancelEmployeeID(CancelEmployeeID)
                .setCancelReason(CancelReason == null ? "" : CancelReason)
                .setTableID(TableID)
                .setCreatedDate(CreatedDate)
                .setCreatedBy(CreatedBy)
                .setModifiedDate(CommonFunc.getStringCurrentDateTime())
                .setModifiedBy(ModifiedBy)
                .build();
    }
}
