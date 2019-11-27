package com.example.salesmanagerment.data.model.entity;

public class InventoryItemMapping {
    public String InventoryItemID;
    public String InventoryName;
    public String UnitName;
    public Double UnitPrice;

    public InventoryItemMapping(Builder builder) {
        InventoryItemID = builder.InventoryItemID;
        InventoryName = builder.InventoryName;
        UnitName = builder.UnitName;
        UnitPrice = builder.UnitPrice;
    }

    public static class Builder {
        public String InventoryItemID;
        public String InventoryName;
        public String UnitName;
        public Double UnitPrice;

        public Builder setInventoryItemID(String inventoryItemID) {
            InventoryItemID = inventoryItemID;
            return this;
        }

        public Builder setInventoryName(String inventoryName) {
            InventoryName = inventoryName;
            return this;
        }

        public Builder setUnitName(String unitName) {
            UnitName = unitName;
            return this;
        }

        public Builder setUnitPrice(Double unitPrice) {
            UnitPrice = unitPrice;
            return this;
        }

        public InventoryItemMapping build() {
            return new InventoryItemMapping(this);
        }
    }
}
