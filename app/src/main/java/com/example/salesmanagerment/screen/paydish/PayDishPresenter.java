package com.example.salesmanagerment.screen.paydish;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.InventoryItemMapping;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class PayDishPresenter implements PayDishContact.IPresenter {

    private static final String TAG = "ChooseInventoryItemPres";
    private DataSource mDataSource;
    private PayDishContact.IView mView;

    public PayDishPresenter() {
        mDataSource = DataSource.getInstance();
    }

    @Override
    public void getOrderDetailsByOrderID(String orderID, final int groupPosition) {
        mView.showLoading(true);
        mDataSource.GetOrderDetailsByOrderID(orderID, new IDataCallBack<List<OrderDetail>, String>() {
            @Override
            public void onDataSuccess(List<OrderDetail> data) {
                mView.showLoading(true);
                if (data != null) {
                    mView.getListOrderDetailSuccess(data, groupPosition);
                } else {
                    CommonFunc.showToastError(R.string.somthing_went_wrong);
                }
            }

            @Override
            public void onDataFailed(String error) {
                mView.showLoading(true);
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    @Override
    public void setView(PayDishContact.IView view) {
        mView = view;
    }

    @Override
    public void onStart() {
        getOrderResponseList();
    }

    private void getOrderResponseList() {
        mView.showLoading(true);
        mDataSource.getListOrder(Constants.ORDER_SERVING, new IDataCallBack<List<OrderResponse>, String>() {
            @Override
            public void onDataSuccess(List<OrderResponse> data) {
                mView.showLoading(false);
                mView.getOrderResponseSuccess(data);
            }

            @Override
            public void onDataFailed(String error) {
                mView.showLoading(false);
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void setItemOrders(final List<OrderDetail> orderDetails, final int groupPosition) {
        new AsyncTask<Void, Void, List<ItemOrder>>() {

            @Override
            protected List<ItemOrder> doInBackground(Void... voids) {
                List<ItemOrder> mItemOrders = new ArrayList<>();
                for (OrderDetail item : orderDetails) {
                    String desc = "";
                    if (CommonFunc.isNullOrEmpty(item.Description)) {
                        desc = item.Description;
                    }
                    InventoryItemMapping itemMapping = mDataSource.getInventoryItemMapping(item.InventoryItemID);
                    mItemOrders.add(new ItemOrder.Builder()
                            .setID(item.InventoryItemID)
                            .setQuantity(item.Quantity)
                            .setOrderID(item.OrderID)
                            .setOrderDetailID(item.OrderDetailID)
                            .setCookedQuantity(item.CookedQuantity)
                            .setServedQuantity(item.ServedQuantity)
                            .setCookingQuantity(item.CookingQuantity)
                            .setOrderDetailStatus(item.OrderDetailStatus)
                            .setCancelEmployeeID(item.CancelEmployeeID)
                            .setName(itemMapping.InventoryName)
                            .setPrice(itemMapping.UnitPrice)
                            .setUnitName(itemMapping.UnitName)
                            .setTotalMoney(itemMapping.UnitPrice * item.Quantity)
                            .setDescription(desc)
                            .build());
                }
                return mItemOrders;
            }

            @Override
            protected void onPostExecute(List<ItemOrder> list) {
                super.onPostExecute(list);
                mView.setItemOderByOrderDetailSuccess(list, groupPosition);
            }
        }.execute();
    }

    @Override
    public void updateStatusOrderDetail(OrderDetail orderDetail, final int groupPosition, final int itemPos, final int type) {
        mDataSource.updateOrderDetail(orderDetail, new IDataCallBack<Boolean, String>() {
            @Override
            public void onDataSuccess(Boolean data) {
                if (data) {
                    mView.updateStatusSuccess(groupPosition, itemPos, type);
                } else {
                    CommonFunc.showToastError(R.string.somthing_went_wrong);
                }
            }

            @Override
            public void onDataFailed(String error) {
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    @Override
    public void onStop() {

    }
}
