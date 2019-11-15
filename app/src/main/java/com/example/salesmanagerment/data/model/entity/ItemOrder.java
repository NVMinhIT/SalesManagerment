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

    public ItemOrder(String ID, int quantity, String name, String image, Double price, String unitName) {
        this.ID = ID;
        Quantity = quantity;
        Name = name;
        Image = image;
        Price = price;
        UnitName = unitName;
    }

    protected ItemOrder(Parcel in) {
        ID = in.readString();
        Quantity = in.readInt();
        Name = in.readString();
        Image = in.readString();
        UnitName = in.readString();
        if (in.readByte() == 0) {
            Price = null;
        } else {
            Price = in.readDouble();
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeInt(Quantity);
        dest.writeString(Name);
        dest.writeString(Image);
        dest.writeString(UnitName);
        if (Price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Price);
        }
    }
}
