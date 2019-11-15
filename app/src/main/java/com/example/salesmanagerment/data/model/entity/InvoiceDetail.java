package com.example.salesmanagerment.data.model.entity;

public class InvoiceDetail {
    public String RefDetailID;
    public String RefID;
    public String ItemID;
    public String ItemAdditionID;
    public Double Quantity;
    public Double QuantityAddition;
    public Double UnitPrice;
    public Double SaleAmount;
    public Double DiscountRate;
    public Double DiscountAmount;
    public String Description;
    public int SortOrder;
    public String PromotionID;
    public String OrderDetailID;

    public InvoiceDetail(Builder builder) {
        RefDetailID = builder.RefDetailID;
        RefID = builder.RefID;
        ItemID = builder.ItemID;
        ItemAdditionID = builder.ItemAdditionID;
        Quantity = builder.Quantity;
        QuantityAddition = builder.QuantityAddition;
        UnitPrice = builder.UnitPrice;
        SaleAmount = builder.SaleAmount;
        DiscountRate = builder.DiscountRate;
        DiscountAmount = builder.DiscountAmount;
        Description = builder.Description;
        SortOrder = builder.SortOrder;
        PromotionID = builder.PromotionID;
        OrderDetailID = builder.OrderDetailID;

    }

    public static class Builder {
        public String RefDetailID;
        public String RefID;
        public String ItemID;
        public String ItemAdditionID;
        public Double Quantity;
        public Double QuantityAddition;
        public Double UnitPrice;
        public Double SaleAmount;
        public Double DiscountRate;
        public Double DiscountAmount;
        public String Description;
        public int SortOrder;
        public String PromotionID;
        public String OrderDetailID;

        public Builder setRefDetailID(String refDetailID) {
            RefDetailID = refDetailID;
            return this;
        }

        public Builder setRefID(String refID) {
            RefID = refID;
            return this;
        }

        public Builder setItemID(String itemID) {
            ItemID = itemID;
            return this;
        }

        public Builder setItemAdditionID(String itemAdditionID) {
            ItemAdditionID = itemAdditionID;
            return this;
        }

        public Builder setQuantity(Double quantity) {
            Quantity = quantity;
            return this;
        }

        public Builder setQuantityAddition(Double quantityAddition) {
            QuantityAddition = quantityAddition;
            return this;
        }

        public Builder setUnitPrice(Double unitPrice) {
            UnitPrice = unitPrice;
            return this;
        }

        public Builder setSaleAmount(Double saleAmount) {
            SaleAmount = saleAmount;
            return this;
        }

        public Builder setDiscountRate(Double discountRate) {
            DiscountRate = discountRate;
            return this;
        }

        public Builder setDiscountAmount(Double discountAmount) {
            DiscountAmount = discountAmount;
            return this;
        }

        public Builder setDescription(String description) {
            Description = description;
            return this;
        }

        public Builder setSortOrder(int sortOrder) {
            SortOrder = sortOrder;
            return this;
        }

        public Builder setPromotionID(String promotionID) {
            PromotionID = promotionID;
            return this;
        }

        public Builder setOrderDetailID(String orderDetailID) {
            OrderDetailID = orderDetailID;
            return this;
        }

        public InvoiceDetail build() {
            return new InvoiceDetail(this);
        }
    }
}