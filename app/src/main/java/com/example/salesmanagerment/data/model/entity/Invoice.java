package com.example.salesmanagerment.data.model.entity;

import java.util.Date;

public class Invoice {
    public String RefID;
    public String BranchID;
    public String OrderID;
    public String RefNo;
    public Date RefDate;
    public String CustomerID;
    public Double TotalSaleAmount;
    public Double TotalDiscountAmount;
    public Double ServiceRate;
    public Double ServiceAmount;
    public String PromotionID;
    public Double TotalAmount;
    public Double VATRate;
    public Double VATAmount;
    public Double PaymentAmount;
    public Double ReceiveAmount;
    public Double ReturnAmount;
    public Double OtherPromotionAmount;
    public String JournalMemo;
    public String CancelReason;
    public int PaymentStatus;
    public String EmployeeID;
    public Boolean IsApplyTaxWhenRequire;
    public Date CreatedDate;
    public String CreatedBy;
    public Date ModifiedDate;
    public String ModifiedBy;

    public Invoice(Builder builder) {
        RefID = builder.RefID;
        BranchID = builder.BranchID;
        OrderID = builder.OrderID;
        RefNo = builder.RefNo;
        RefDate = builder.RefDate;
        CustomerID = builder.CustomerID;
        TotalSaleAmount = builder.TotalSaleAmount;
        TotalDiscountAmount = builder.TotalDiscountAmount;
        ServiceRate = builder.ServiceRate;
        ServiceAmount = builder.ServiceAmount;
        PromotionID = builder.PromotionID;
        TotalAmount = builder.TotalAmount;
        VATRate = builder.VATRate;
        VATAmount = builder.VATAmount;
        PaymentAmount = builder.PaymentAmount;
        ReceiveAmount = builder.ReceiveAmount;
        ReturnAmount = builder.ReturnAmount;
        OtherPromotionAmount = builder.OtherPromotionAmount;
        JournalMemo = builder.JournalMemo;
        CancelReason = builder.CancelReason;
        PaymentStatus = builder.PaymentStatus;
        EmployeeID = builder.EmployeeID;
        IsApplyTaxWhenRequire = builder.IsApplyTaxWhenRequire;
        CreatedDate = builder.CreatedDate;
        CreatedBy = builder.CreatedBy;
        ModifiedDate = builder.ModifiedDate;
        ModifiedBy = builder.ModifiedBy;

    }

    public static class Builder {
        public String RefID;
        public String BranchID;
        public String OrderID;
        public String RefNo;
        public Date RefDate;
        public String CustomerID;
        public Double TotalSaleAmount;
        public Double TotalDiscountAmount;
        public Double ServiceRate;
        public Double ServiceAmount;
        public String PromotionID;
        public Double TotalAmount;
        public Double VATRate;
        public Double VATAmount;
        public Double PaymentAmount;
        public Double ReceiveAmount;
        public Double ReturnAmount;
        public Double OtherPromotionAmount;
        public String JournalMemo;
        public String CancelReason;
        public int PaymentStatus;
        public String EmployeeID;
        public Boolean IsApplyTaxWhenRequire;
        public Date CreatedDate;
        public String CreatedBy;
        public Date ModifiedDate;
        public String ModifiedBy;

        public Builder setRefID(String refID) {
            RefID = refID;
            return this;
        }

        public Builder setBranchID(String branchID) {
            BranchID = branchID;
            return this;
        }

        public Builder setOrderID(String orderID) {
            OrderID = orderID;
            return this;
        }

        public Builder setRefNo(String refNo) {
            RefNo = refNo;
            return this;
        }

        public Builder setRefDate(Date refDate) {
            RefDate = refDate;
            return this;
        }

        public Builder setCustomerID(String customerID) {
            CustomerID = customerID;
            return this;
        }

        public Builder setTotalSaleAmount(Double totalSaleAmount) {
            TotalSaleAmount = totalSaleAmount;
            return this;
        }

        public Builder setTotalDiscountAmount(Double totalDiscountAmount) {
            TotalDiscountAmount = totalDiscountAmount;
            return this;
        }

        public Builder setServiceRate(Double serviceRate) {
            ServiceRate = serviceRate;
            return this;
        }

        public Builder setServiceAmount(Double serviceAmount) {
            ServiceAmount = serviceAmount;
            return this;
        }

        public Builder setPromotionID(String promotionID) {
            PromotionID = promotionID;
            return this;
        }

        public Builder setTotalAmount(Double totalAmount) {
            TotalAmount = totalAmount;
            return this;
        }

        public Builder setVATRate(Double VATRate) {
            this.VATRate = VATRate;
            return this;
        }

        public Builder setVATAmount(Double VATAmount) {
            this.VATAmount = VATAmount;
            return this;
        }

        public Builder setPaymentAmount(Double paymentAmount) {
            PaymentAmount = paymentAmount;
            return this;
        }

        public Builder setReceiveAmount(Double receiveAmount) {
            ReceiveAmount = receiveAmount;
            return this;
        }

        public Builder setReturnAmount(Double returnAmount) {
            ReturnAmount = returnAmount;
            return this;
        }

        public Builder setOtherPromotionAmount(Double otherPromotionAmount) {
            OtherPromotionAmount = otherPromotionAmount;
            return this;
        }

        public Builder setJournalMemo(String journalMemo) {
            JournalMemo = journalMemo;
            return this;
        }

        public Builder setCancelReason(String cancelReason) {
            CancelReason = cancelReason;
            return this;
        }

        public Builder setPaymentStatus(int paymentStatus) {
            PaymentStatus = paymentStatus;
            return this;
        }

        public Builder setEmployeeID(String employeeID) {
            EmployeeID = employeeID;
            return this;
        }

        public Builder setApplyTaxWhenRequire(Boolean applyTaxWhenRequire) {
            IsApplyTaxWhenRequire = applyTaxWhenRequire;
            return this;
        }

        public Builder setCreatedDate(Date createdDate) {
            CreatedDate = createdDate;
            return this;
        }

        public Builder setCreatedBy(String createdBy) {
            CreatedBy = createdBy;
            return this;
        }

        public Builder setModifiedDate(Date modifiedDate) {
            ModifiedDate = modifiedDate;
            return this;
        }

        public Builder setModifiedBy(String modifiedBy) {
            ModifiedBy = modifiedBy;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }
    }
}
