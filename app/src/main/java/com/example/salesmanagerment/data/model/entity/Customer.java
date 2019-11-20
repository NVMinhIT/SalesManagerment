package com.example.salesmanagerment.data.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer implements Parcelable {
    @SerializedName("CustomerID")
    @Expose
    private String customerID;
    @SerializedName("CustomerCode")
    @Expose
    private String customerCode;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("Birthday")
    @Expose
    private String birthday;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Description")
    @Expose
    private String description;

    @SerializedName("Inactive")
    @Expose


    private Boolean inactive;


    public Customer() {

    }

    public Customer(String customerID, String customerCode, String customerName, String birthday, String email, String mobile, String address, String description, Boolean inactive) {
        this.customerID = customerID;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.birthday = birthday;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.description = description;
        this.inactive = inactive;
    }

    protected Customer(Parcel in) {
        customerID = in.readString();
        customerCode = in.readString();
        customerName = in.readString();
        birthday = in.readString();
        email = in.readString();
        mobile = in.readString();
        address = in.readString();
        description = in.readString();
        byte tmpInactive = in.readByte();
        inactive = tmpInactive == 0 ? null : tmpInactive == 1;
        byte tmpIsSelected = in.readByte();

    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(customerID);
        parcel.writeString(customerCode);
        parcel.writeString(customerName);
        parcel.writeString(birthday);
        parcel.writeString(email);
        parcel.writeString(mobile);
        parcel.writeString(address);
        parcel.writeString(description);
        parcel.writeByte((byte) (inactive == null ? 0 : inactive ? 1 : 2));

    }
}
