package com.example.salesmanagerment.data.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Area implements Parcelable {
    public String AreaID;
    public String AreaName;
    public String BranchID;
    public String Description;
    public int NumberOfTable;
    public String Background;
    public Boolean Inactive;
    public String CreatedDate;
    public String CreatedBy;
    public String ModifiedDate;
    public String ModifiedBy;
    public String OldIDs;


    protected Area(Parcel in) {
        AreaID = in.readString();
        AreaName = in.readString();
        BranchID = in.readString();
        Description = in.readString();
        NumberOfTable = in.readInt();
        Background = in.readString();
        byte tmpInactive = in.readByte();
        Inactive = tmpInactive == 0 ? null : tmpInactive == 1;
        CreatedBy = in.readString();
        ModifiedBy = in.readString();
        OldIDs = in.readString();
    }

    public static final Creator<Area> CREATOR = new Creator<Area>() {
        @Override
        public Area createFromParcel(Parcel in) {
            return new Area(in);
        }

        @Override
        public Area[] newArray(int size) {
            return new Area[size];
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
        dest.writeString(BranchID);
        dest.writeString(Description);
        dest.writeInt(NumberOfTable);
        dest.writeString(Background);
        dest.writeByte((byte) (Inactive == null ? 0 : Inactive ? 1 : 2));
        dest.writeString(CreatedBy);
        dest.writeString(ModifiedBy);
        dest.writeString(OldIDs);
    }
}
