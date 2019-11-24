package com.example.salesmanagerment.data.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemOrder implements Parcelable {
    public String ID;
    public Double Quantity;
    public String Name;
    public String Image;
    public Double Price;
    public String UnitName;
    public Double TotalMoney = 0.0;
    public String OrderDetailID;
    public Double CookedQuantity;
    public Double ServedQuantity;
    public Double CookingQuantity;
    public int OrderDetailStatus;
    public String CancelEmployeeID;

    public ItemOrder(Builder builder) {
        this.ID = builder.ID;
        this.Quantity = builder.Quantity;
        this.Name = builder.Name;
        this.Image = builder.Image;
        this.Price = builder.Price;
        this.UnitName = builder.UnitName;
        this.TotalMoney = builder.TotalMoney;
        this.OrderDetailID = builder.OrderDetailID;
        this.CookedQuantity = builder.CookedQuantity;
        this.ServedQuantity = builder.ServedQuantity;
        this.CookingQuantity = builder.CookingQuantity;
        this.OrderDetailStatus = builder.OrderDetailStatus;
        this.CancelEmployeeID = builder.CancelEmployeeID;
    }


    public static class Builder {
        public Builder setID(String ID) {
            this.ID = ID;
            return this;
        }

        public Builder setQuantity(Double quantity) {
            Quantity = quantity;
            return this;
        }

        public Builder setName(String name) {
            Name = name;
            return this;
        }

        public Builder setImage(String image) {
            Image = image;
            return this;
        }

        public Builder setPrice(Double price) {
            Price = price;
            return this;
        }

        public Builder setUnitName(String unitName) {
            UnitName = unitName;
            return this;
        }

        public Builder setTotalMoney(Double totalMoney) {
            TotalMoney = totalMoney;
            return this;
        }

        public Builder setOrderDetailID(String orderDetailID) {
            OrderDetailID = orderDetailID;
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

        public ItemOrder build() {
            return new ItemOrder(this);
        }


        public String ID;
        public Double Quantity;
        public String Name;
        public String Image;
        public Double Price;
        public String UnitName;
        public Double TotalMoney = 0.0;
        public String OrderDetailID;
        public Double CookedQuantity;
        public Double ServedQuantity;
        public Double CookingQuantity;
        public int OrderDetailStatus;
        public String CancelEmployeeID;
    }

    protected ItemOrder(Parcel in) {
        ID = in.readString();
        Quantity = in.readDouble();
        Name = in.readString();
        Image = in.readString();
        if (in.readByte() == 0) {
            Price = null;
        } else {
            Price = in.readDouble();
        }
        UnitName = in.readString();
        if (in.readByte() == 0) {
            TotalMoney = null;
        } else {
            TotalMoney = in.readDouble();
        }
        OrderDetailID = in.readString();
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
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeDouble(Quantity);
        dest.writeString(Name);
        dest.writeString(Image);
        if (Price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Price);
        }
        dest.writeString(UnitName);
        if (TotalMoney == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(TotalMoney);
        }
        dest.writeString(OrderDetailID);
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemOrder> CREATOR = new Creator<ItemOrder>() {
        @Override
        public ItemOrder createFromParcel(Parcel in) {
            return new ItemOrder(in);
        }

        @Override
        public ItemOrder[] newArray(int size) {
            return new ItemOrder[size];
        }
    };
}
