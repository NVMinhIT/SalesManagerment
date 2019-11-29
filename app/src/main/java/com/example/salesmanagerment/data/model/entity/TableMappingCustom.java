package com.example.salesmanagerment.data.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class TableMappingCustom implements Parcelable {
    public String AreaID;
    public String AreaName;
    public String TableID;
    public String TableName;
    public int SortOrder;
    public String FromTime;
    public String OrderDate;
    public int TableStatus;
    public Boolean isSelected = false;

    public TableMappingCustom(){

    }

    protected TableMappingCustom(Parcel in) {
        AreaID = in.readString();
        AreaName = in.readString();
        TableID = in.readString();
        TableName = in.readString();
        SortOrder = in.readInt();
        FromTime = in.readString();
        OrderDate = in.readString();
        TableStatus = in.readInt();
        byte tmpIsSelected = in.readByte();
        isSelected = tmpIsSelected == 1;
    }

    public static final Creator<TableMappingCustom> CREATOR = new Creator<TableMappingCustom>() {
        @Override
        public TableMappingCustom createFromParcel(Parcel in) {
            return new TableMappingCustom(in);
        }

        @Override
        public TableMappingCustom[] newArray(int size) {
            return new TableMappingCustom[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(AreaID);
        dest.writeString(AreaName);
        dest.writeString(TableID);
        dest.writeString(TableName);
        dest.writeInt(SortOrder);
        dest.writeString(FromTime);
        dest.writeString(OrderDate);
        dest.writeInt(TableStatus);
        dest.writeByte((byte) (isSelected == null ? 0 : isSelected ? 1 : 2));
    }
}
