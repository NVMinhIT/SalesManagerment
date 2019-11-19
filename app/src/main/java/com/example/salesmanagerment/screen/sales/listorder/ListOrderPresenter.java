package com.example.salesmanagerment.screen.sales.listorder;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
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
    public void getListOrder() {
        orderResponses = mDataSource.getOrderResponseList();
        if (orderResponses != null) {
            iView.getListSuccess(orderResponses);
        } else {
            mDataSource.getListOrder(new IDataCallBack<List<OrderResponse>, String>() {
                @Override
                public void onDataSuccess(List<OrderResponse> data) {
                    orderResponses = data;
                    iView.getListSuccess(orderResponses);

                }

                @Override
                public void onDataFailed(String error) {
                    CommonFunc.showToastError(R.string.somthing_went_wrong);
                }
            });
        }
    }
}
