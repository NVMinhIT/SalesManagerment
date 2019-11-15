package com.example.salesmanagerment.data.model.entity;

import java.util.Date;

public class Kitchen {
    public String KitchenID;
    public String BranchID;
    public String KitchenName;
    public String Description;
    public Boolean Inactive;
    public Date CreatedDate;
    public String CreatedBy;
    public Date ModifiedDate;
    public String ModifiedBy;
    public String OldIDs;

    public Kitchen(Builder builder) {
        KitchenID = builder.KitchenID;
        BranchID = builder.BranchID;
        KitchenName = builder.KitchenName;
        Description = builder.Description;
        Inactive = builder.Inactive;
        CreatedDate = builder.CreatedDate;
        CreatedBy = builder.CreatedBy;
        ModifiedDate = builder.ModifiedDate;
        ModifiedBy = builder.ModifiedBy;
        OldIDs = builder.OldIDs;
    }

    public static class Builder {
        public String KitchenID;
        public String BranchID;
        public String KitchenName;
        public String Description;
        public Boolean Inactive;
        public Date CreatedDate;
        public String CreatedBy;
        public Date ModifiedDate;
        public String ModifiedBy;
        public String OldIDs;

        public Builder setKitchenID(String kitchenID) {
            KitchenID = kitchenID;
            return this;
        }

        public Builder setBranchID(String branchID) {
            BranchID = branchID;
            return this;
        }

        public Builder setKitchenName(String kitchenName) {
            KitchenName = kitchenName;
            return this;
        }

        public Builder setDescription(String description) {
            Description = description;
            return this;
        }

        public Builder setInactive(Boolean inactive) {
            Inactive = inactive;
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

        public Builder setOldIDs(String oldIDs) {
            OldIDs = oldIDs;
            return this;
        }
        public Kitchen build() {
            return new Kitchen(this);
        }
    }
}
