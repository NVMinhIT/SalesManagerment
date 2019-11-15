package com.example.salesmanagerment.data.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemOrder implements Parcelable {
    public String ID;
    public int Quantity;
    public String Name;
    public String Image;
    public Double Price;
    public String UnitName;
    private Double TotalMoney;

    public ItemOrder(String ID, int quantity, String name, String image, Double price, String unitName, Double totalMoney) {
        this.ID = ID;
        Quantity = quantity;
        Name = name;
        Image = image;
        Price = price;
        UnitName = unitName;
        TotalMoney = totalMoney;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public Double getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        TotalMoney = totalMoney;
    }

    public static Creator<ItemOrder> getCREATOR() {
        return CREATOR;
    }

    protected ItemOrder(Parcel in) {
        ID = in.readString();
        Quantity = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ID);
        parcel.writeInt(Quantity);
        parcel.writeString(Name);
        parcel.writeString(Image);
        if (Price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(Price);
        }
        parcel.writeString(UnitName);
        if (TotalMoney == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(TotalMoney);
        }
    }
}
