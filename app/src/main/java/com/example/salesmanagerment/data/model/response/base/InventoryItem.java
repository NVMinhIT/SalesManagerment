package com.example.salesmanagerment.data.model.response.base;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InventoryItem  implements Parcelable {
    @SerializedName("InventoryItemID")
    @Expose
    private String inventoryItemID;
    @SerializedName("InventoryItemCode")
    @Expose
    private String inventoryItemCode;
    @SerializedName("InventoryItemName")
    @Expose
    private String inventoryItemName;
    @SerializedName("InventoryItemNameNonUnicode")
    @Expose
    private String inventoryItemNameNonUnicode;
    @SerializedName("InventoryItemType")
    @Expose
    private Integer inventoryItemType;
    @SerializedName("InventoryItemCategoryID")
    @Expose
    private String inventoryItemCategoryID;
    @SerializedName("ItemType")
    @Expose
    private Integer itemType;
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
    @SerializedName("ApplyType")
    @Expose
    private Integer applyType;
    @SerializedName("UnitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("IsFeature")
    @Expose
    private Boolean isFeature;
    @SerializedName("IsChangePriceByTime")
    @Expose
    private Boolean isChangePriceByTime;
    @SerializedName("HideInMenu")
    @Expose
    private Boolean hideInMenu;
    @SerializedName("IsAutoShowInventoryItemAddition")
    @Expose
    private Boolean isAutoShowInventoryItemAddition;
    @SerializedName("IsCustomCombo")
    @Expose
    private Boolean isCustomCombo;
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
    @SerializedName("EditVersion")
    @Expose
    private String editVersion;

    public InventoryItem() {

    }

    public InventoryItem(String inventoryItemID, String inventoryItemCode, String inventoryItemName, String inventoryItemNameNonUnicode, Integer inventoryItemType, String inventoryItemCategoryID, Integer itemType, String unitID, String description, String fileResource, Integer courseType, Integer applyType, Double unitPrice, Boolean isFeature, Boolean isChangePriceByTime, Boolean hideInMenu, Boolean isAutoShowInventoryItemAddition, Boolean isCustomCombo, Boolean inactive, String createdDate, String createdBy, String modifiedDate, String modifiedBy, String editVersion) {
        this.inventoryItemID = inventoryItemID;
        this.inventoryItemCode = inventoryItemCode;
        this.inventoryItemName = inventoryItemName;
        this.inventoryItemNameNonUnicode = inventoryItemNameNonUnicode;
        this.inventoryItemType = inventoryItemType;
        this.inventoryItemCategoryID = inventoryItemCategoryID;
        this.itemType = itemType;
        this.unitID = unitID;
        this.description = description;
        this.fileResource = fileResource;
        this.courseType = courseType;
        this.applyType = applyType;
        this.unitPrice = unitPrice;
        this.isFeature = isFeature;
        this.isChangePriceByTime = isChangePriceByTime;
        this.hideInMenu = hideInMenu;
        this.isAutoShowInventoryItemAddition = isAutoShowInventoryItemAddition;
        this.isCustomCombo = isCustomCombo;
        this.inactive = inactive;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
        this.editVersion = editVersion;
    }

    protected InventoryItem(Parcel in) {
        inventoryItemID = in.readString();
        inventoryItemCode = in.readString();
        inventoryItemName = in.readString();
        inventoryItemNameNonUnicode = in.readString();
        if (in.readByte() == 0) {
            inventoryItemType = null;
        } else {
            inventoryItemType = in.readInt();
        }
        inventoryItemCategoryID = in.readString();
        if (in.readByte() == 0) {
            itemType = null;
        } else {
            itemType = in.readInt();
        }
        unitID = in.readString();
        description = in.readString();
        fileResource = in.readString();
        if (in.readByte() == 0) {
            courseType = null;
        } else {
            courseType = in.readInt();
        }
        if (in.readByte() == 0) {
            applyType = null;
        } else {
            applyType = in.readInt();
        }
        if (in.readByte() == 0) {
            unitPrice = null;
        } else {
            unitPrice = in.readDouble();
        }
        byte tmpIsFeature = in.readByte();
        isFeature = tmpIsFeature == 0 ? null : tmpIsFeature == 1;
        byte tmpIsChangePriceByTime = in.readByte();
        isChangePriceByTime = tmpIsChangePriceByTime == 0 ? null : tmpIsChangePriceByTime == 1;
        byte tmpHideInMenu = in.readByte();
        hideInMenu = tmpHideInMenu == 0 ? null : tmpHideInMenu == 1;
        byte tmpIsAutoShowInventoryItemAddition = in.readByte();
        isAutoShowInventoryItemAddition = tmpIsAutoShowInventoryItemAddition == 0 ? null : tmpIsAutoShowInventoryItemAddition == 1;
        byte tmpIsCustomCombo = in.readByte();
        isCustomCombo = tmpIsCustomCombo == 0 ? null : tmpIsCustomCombo == 1;
        byte tmpInactive = in.readByte();
        inactive = tmpInactive == 0 ? null : tmpInactive == 1;
        createdDate = in.readString();
        createdBy = in.readString();
        modifiedDate = in.readString();
        modifiedBy = in.readString();
        editVersion = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(inventoryItemID);
        dest.writeString(inventoryItemCode);
        dest.writeString(inventoryItemName);
        dest.writeString(inventoryItemNameNonUnicode);
        if (inventoryItemType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(inventoryItemType);
        }
        dest.writeString(inventoryItemCategoryID);
        if (itemType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(itemType);
        }
        dest.writeString(unitID);
        dest.writeString(description);
        dest.writeString(fileResource);
        if (courseType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(courseType);
        }
        if (applyType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(applyType);
        }
        if (unitPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(unitPrice);
        }
        dest.writeByte((byte) (isFeature == null ? 0 : isFeature ? 1 : 2));
        dest.writeByte((byte) (isChangePriceByTime == null ? 0 : isChangePriceByTime ? 1 : 2));
        dest.writeByte((byte) (hideInMenu == null ? 0 : hideInMenu ? 1 : 2));
        dest.writeByte((byte) (isAutoShowInventoryItemAddition == null ? 0 : isAutoShowInventoryItemAddition ? 1 : 2));
        dest.writeByte((byte) (isCustomCombo == null ? 0 : isCustomCombo ? 1 : 2));
        dest.writeByte((byte) (inactive == null ? 0 : inactive ? 1 : 2));
        dest.writeString(createdDate);
        dest.writeString(createdBy);
        dest.writeString(modifiedDate);
        dest.writeString(modifiedBy);
        dest.writeString(editVersion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InventoryItem> CREATOR = new Creator<InventoryItem>() {
        @Override
        public InventoryItem createFromParcel(Parcel in) {
            return new InventoryItem(in);
        }

        @Override
        public InventoryItem[] newArray(int size) {
            return new InventoryItem[size];
        }
    };

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

    public String getInventoryItemNameNonUnicode() {
        return inventoryItemNameNonUnicode;
    }

    public void setInventoryItemNameNonUnicode(String inventoryItemNameNonUnicode) {
        this.inventoryItemNameNonUnicode = inventoryItemNameNonUnicode;
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

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
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

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Boolean getFeature() {
        return isFeature;
    }

    public void setFeature(Boolean feature) {
        isFeature = feature;
    }

    public Boolean getChangePriceByTime() {
        return isChangePriceByTime;
    }

    public void setChangePriceByTime(Boolean changePriceByTime) {
        isChangePriceByTime = changePriceByTime;
    }

    public Boolean getHideInMenu() {
        return hideInMenu;
    }

    public void setHideInMenu(Boolean hideInMenu) {
        this.hideInMenu = hideInMenu;
    }

    public Boolean getAutoShowInventoryItemAddition() {
        return isAutoShowInventoryItemAddition;
    }

    public void setAutoShowInventoryItemAddition(Boolean autoShowInventoryItemAddition) {
        isAutoShowInventoryItemAddition = autoShowInventoryItemAddition;
    }

    public Boolean getCustomCombo() {
        return isCustomCombo;
    }

    public void setCustomCombo(Boolean customCombo) {
        isCustomCombo = customCombo;
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

    public String getEditVersion() {
        return editVersion;
    }

    public void setEditVersion(String editVersion) {
        this.editVersion = editVersion;
    }
}