package com.example.salesmanagerment.screen.sales.listorder;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.InventoryItemMapping;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.data.model.request.CancelOrderRequest;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;

import java.util.ArrayList;
import java.util.List;

public class ListOrderPresenter implements IListOrderContact.IPresenter {

    private IListOrderContact.IView iView;
    private DataSource mDataSource;
    private List<OrderResponse> orderResponses = new ArrayList<>();

    public ListOrderPresenter() {
        mDataSource = DataSource.getInstance();

    }


    @Override
    public void setView(IListOrderContact.IView view) {
        iView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }


    @Override
    public void requestPay(String orderID) {
        iView.showLoading(true);
        mDataSource.RequestPayOrder(orderID, new IDataCallBack<Boolean, String>() {
            @Override
            public void onDataSuccess(Boolean data) {
                iView.showLoading(false);
                iView.requestPaySuccess(data);
            }

            @Override
            public void onDataFailed(String error) {
                iView.showLoading(false);
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    @Override
    public void getListOrder(Boolean isShowLoading, int orderStatus) {
        iView.showLoading(isShowLoading);
        mDataSource.getListOrder(orderStatus, new IDataCallBack<List<OrderResponse>, String>() {
            @Override
            public void onDataSuccess(List<OrderResponse> data) {
                iView.showLoading(false);
                orderResponses = data;
                iView.getListSuccess(orderResponses);

            }

            @Override
            public void onDataFailed(String error) {
                iView.showLoading(false);
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    @Override
    public void checkCancelOrder(String orderID) {
        iView.showLoading(true);
        mDataSource.checkOrderIsSendKitchen(orderID, new IDataCallBack<Boolean, String>() {
            @Override
            public void onDataSuccess(Boolean data) {
                iView.showLoading(false);
                iView.checkCancelOrderDone(!data);
            }

            @Override
            public void onDataFailed(String error) {
                iView.showLoading(false);
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    @Override
    public void cancelOrder(CancelOrderRequest cancelOrderRequest) {
        iView.showLoading(true);
        mDataSource.cancelOrder(cancelOrderRequest, new IDataCallBack<Boolean, String>() {
            @Override
            public void onDataSuccess(Boolean data) {
                iView.showLoading(false);
                if (data) {
                    CommonFunc.showToastSuccess("Hủy order thành công");
                    iView.cancelOrderSuccess();
                } else {
                    CommonFunc.showToastError(R.string.somthing_went_wrong);
                }
            }

            @Override
            public void onDataFailed(String error) {
                iView.showLoading(false);
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    @Override
    public void getItemOrderByOderID(String orderID, final OrderResponse orderResponse) {
        iView.showLoading(true);
        mDataSource.GetOrderDetailsByOrderID(orderID, new IDataCallBack<List<OrderDetail>, String>() {
            @Override
            public void onDataSuccess(List<OrderDetail> data) {
                setItemOrders(data, orderResponse);
            }

            @Override
            public void onDataFailed(String error) {
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public void setItemOrders(final List<OrderDetail> orderDetails, final OrderResponse orderResponse) {
        new AsyncTask<Void, Void, List<ItemOrder>>() {

            @Override
            protected List<ItemOrder> doInBackground(Void... voids) {
                List<ItemOrder> itemOrders = new ArrayList<>();
                for (OrderDetail item : orderDetails) {
                    InventoryItemMapping itemMapping = mDataSource.getInventoryItemMapping(item.InventoryItemID);
                    itemOrders.add(new ItemOrder.Builder()
                            .setID(item.InventoryItemID)
                            .setQuantity(item.Quantity)
                            .setOrderDetailID(item.OrderDetailID)
                            .setName(itemMapping.InventoryName)
                            .setPrice(itemMapping.UnitPrice)
                            .setUnitName(itemMapping.UnitName)
                            .setTotalMoney(itemMapping.UnitPrice * item.Quantity)
                            .build());
                }
                return itemOrders;
            }

            @Override
            protected void onPostExecute(List<ItemOrder> itemOrders) {
                super.onPostExecute(itemOrders);
                iView.showLoading(false);
                iView.getItemOrdersSuccess(itemOrders, orderResponse);
            }
        }.execute();
    }

}
