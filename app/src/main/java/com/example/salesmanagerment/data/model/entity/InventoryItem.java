package com.example.salesmanagerment.data.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InventoryItem {
    @SerializedName("InventoryItemID")
    @Expose
    private String inventoryItemID;
    @SerializedName("InventoryItemCode")
    @Expose
    private String inventoryItemCode;
    @SerializedName("InventoryItemName")
    @Expose
    private String inventoryItemName;
    @SerializedName("InventoryItemType")
    @Expose
    private Integer inventoryItemType;
    @SerializedName("InventoryItemCategoryID")
    @Expose
    private String inventoryItemCategoryID;
    @SerializedName("UnitID")
    @Expose
    private String unitID;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("FileResource")
    @Expose
    private String fileResource;
    @SerializedName("CourseType")
    @Expose
    private Integer courseType;
    @SerializedName("UnitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("Inactive")
    @Expose
    private Boolean inactive;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("ModifiedBy")
    @Expose
    private String modifiedBy;
    @SerializedName("OldIDs")
    @Expose
    private String oldIDs;

    public String getInventoryItemID() {
        return inventoryItemID;
    }

    public void setInventoryItemID(String inventoryItemID) {
        this.inventoryItemID = inventoryItemID;
    }

    public String getInventoryItemCode() {
        return inventoryItemCode;
    }

    public void setInventoryItemCode(String inventoryItemCode) {
        this.inventoryItemCode = inventoryItemCode;
    }

    public String getInventoryItemName() {
        return inventoryItemName;
    }

    public void setInventoryItemName(String inventoryItemName) {
        this.inventoryItemName = inventoryItemName;
    }

    public Integer getInventoryItemType() {
        return inventoryItemType;
    }

    public void setInventoryItemType(Integer inventoryItemType) {
        this.inventoryItemType = inventoryItemType;
    }

    public String getInventoryItemCategoryID() {
        return inventoryItemCategoryID;
    }

    public void setInventoryItemCategoryID(String inventoryItemCategoryID) {
        this.inventoryItemCategoryID = inventoryItemCategoryID;
    }

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileResource() {
        return fileResource;
    }

    public void setFileResource(String fileResource) {
        this.fileResource = fileResource;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getOldIDs() {
        return oldIDs;
    }

    public void setOldIDs(String oldIDs) {
        this.oldIDs = oldIDs;
    }
}
