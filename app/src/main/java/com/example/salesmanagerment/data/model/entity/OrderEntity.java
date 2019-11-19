package com.example.salesmanagerment.data.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.salesmanagerment.utils.CacheManager;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderEntity implements Parcelable {
    public Order order;
    public List<OrderDetail> orderDetails;

    public OrderEntity() {

    }

    protected OrderEntity(Parcel in) {
        order = in.readParcelable(Order.class.getClassLoader());
        orderDetails = in.createTypedArrayList(OrderDetail.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(order, flags);
        dest.writeTypedList(orderDetails);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OrderEntity> CREATOR = new Creator<OrderEntity>() {
        @Override
        public OrderEntity createFromParcel(Parcel in) {
            return new OrderEntity(in);
        }

        @Override
        public OrderEntity[] newArray(int size) {
            return new OrderEntity[size];
        }
    };

    public OrderEntity initOrderEntity() {
        orderDetails = new ArrayList<>();
        order = new Order.Builder().setOrderID(UUID.randomUUID().toString()).
                setCreatedDate(CommonFunc.getStringCurrentDateTime())
                .setOrderNo("Something")
                .setModifiedDate(CommonFunc.getStringCurrentDateTime())
                .setOrderDate(CommonFunc.getStringCurrentDateTime())
                .setCancelReason("")
                .setCreatedBy(CacheManager.cacheManager.getUser().getDisplayName())
                .setModifiedBy(CacheManager.cacheManager.getUser().getDisplayName())
                .setOrderStatus(Constants.ORDER_SERVING)
                .build();
        return this;
    }

    public OrderEntity setOrderDetails(List<ItemOrder> listItemOrder) {
        List<OrderDetail> list = new ArrayList<>();
        int sortOrder = 0;
        for (ItemOrder item : listItemOrder) {
            list.add(new OrderDetail.Builder()
                    .setOrderDetailID(UUID.randomUUID().toString())
                    .setOrderID(this.order.OrderID)
                    .setInventoryItemID(item.ID)
                    .setOrderDetailStatus(Constants.ORDER_DETAIL_NOTHING)
                    .setQuantity((double) item.Quantity).
                            setSortOrder(sortOrder).
                            build());
            sortOrder++;
        }
        this.orderDetails = list;
        return this;
    }

    public OrderEntity setListItemOrders(List<OrderDetail> orderDetails) {
        return this;
    }
}