package com.example.salesmanagerment.screen.sales.createorder;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.app.ErrorCode;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;

import java.util.List;
import java.util.regex.Pattern;

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
                mView.showLoading(false);
                if (data) {
                    mView.gotoOrdersScreen();
                    CommonFunc.showToastSuccess(R.string.create_order_success);
                } else {
                    CommonFunc.showToastSuccess(R.string.create_order_failed);
                }
            }

            @Override
            public void onDataFailed(String error) {
                mView.showLoading(false);
                if (error.equals(ErrorCode.DUPLICATE)) {
                    CommonFunc.showToastInfo("Trùng mã order, đang khởi tạo lại");
                    String[] temp = mOrderEntity.order.OrderNo.split(Pattern.quote("."));
                    int no = Integer.parseInt(temp[1]) + 1;
                    mOrderEntity.order.OrderNo = temp[0] + "." + no;
                    saveOrder();
                } else {
                    CommonFunc.showToastError(error);
                }

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
