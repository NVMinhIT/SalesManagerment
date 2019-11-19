package com.example.salesmanagerment.screen.sales.createorder;

import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.screen.sales.listorder.ListOrderFragment;
import com.example.salesmanagerment.utils.CommonFunc;

import java.util.List;

public class CreateOrderPresenter implements ICreateOrderContact.IPresenter {

    private ICreateOrderContact.IView mView;
    private DataSource mDataSource;
    public OrderEntity mOrderEntity;
    public List<ItemOrder> mItemOrders;

    public CreateOrderPresenter() {
        mDataSource = DataSource.getInstance();
    }

    @Override
    public void saveOrder() {
        mView.showLoading(true);
        mDataSource.createOrder(mOrderEntity, new IDataCallBack<Boolean, String>() {
            @Override
            public void onDataSuccess(Boolean data) {
                if (data) {
                    mView.gotoOrdersScreen();
                    CommonFunc.showToastSuccess(R.string.create_order_success);


                } else {
                    CommonFunc.showToastSuccess(R.string.create_order_failed);
                }
                mView.showLoading(false);
            }

            @Override
            public void onDataFailed(String error) {
                mView.showLoading(false);
                CommonFunc.showToastSuccess(error);
            }
        });
    }



    @Override
    public void setView(ICreateOrderContact.IView view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
