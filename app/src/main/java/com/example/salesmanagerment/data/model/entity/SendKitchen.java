package com.example.salesmanagerment.data.model.entity;

import java.util.Date;

public class SendKitchen {
    public String SendKitchenID;
    public String KitchenID;
    public String DataContent;
    public String SenderID;
    public Date SendDate;
    public int SendKitchenStatus;
    public String Description;
    public String InventoryItemID;
    public int Quantity;

    public SendKitchen(Builder builder) {
        SendKitchenID = builder.SendKitchenID;
        KitchenID = builder.KitchenID;
        DataContent = builder.DataContent;
        SenderID = builder.SenderID;
        SendDate = builder.SendDate;
        SendKitchenStatus = builder.SendKitchenStatus;
        Description = builder.Description;
        InventoryItemID = builder.InventoryItemID;
        Quantity = builder.Quantity;
    }

    public static class Builder {
        public String SendKitchenID;
        public String KitchenID;
        public String DataContent;
        public String SenderID;
        public Date SendDate;
        public int SendKitchenStatus;
        public String Description;
        public String InventoryItemID;
        public int Quantity;

        public Builder setSendKitchenID(String sendKitchenID) {
            SendKitchenID = sendKitchenID;
            return this;
        }

        public Builder setKitchenID(String kitchenID) {
            KitchenID = kitchenID;
            return this;
        }

        public Builder setDataContent(String dataContent) {
            DataContent = dataContent;
            return this;
        }

        public Builder setSenderID(String senderID) {
            SenderID = senderID;
            return this;
        }

        public Builder setSendDate(Date sendDate) {
            SendDate = sendDate;
            return this;
        }

        public Builder setSendKitchenStatus(int sendKitchenStatus) {
            SendKitchenStatus = sendKitchenStatus;
            return this;
        }

        public Builder setDescription(String description) {
            Description = description;
            return this;
        }

        public Builder setInventoryItemID(String inventoryItemID) {
            InventoryItemID = inventoryItemID;
            return this;
        }

        public Builder setQuantity(int quantity) {
            Quantity = quantity;
            return this;
        }

        public SendKitchen build() {
            return new SendKitchen(this);
        }
    }
}
