package com.example.salesmanagerment.screen.sales.customer.choosecustomer;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.Customer;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;

import java.util.ArrayList;
import java.util.List;

public class ListCustomerPresenter implements IListCustomerContact.IPresenter {
    private DataSource dataSource;
    private IListCustomerContact.IView iView;
    private List<Customer> customerList = new ArrayList<>();


    public ListCustomerPresenter() {
        dataSource = DataSource.getInstance();
    }

    @Override
    public void getListCustomer() {
       iView.showLoading(true);
//        customerList = dataSource.getCustomerList();
//        if (customerList != null) {
//            iView.setListSuccess(customerList);
//
//        } else {
            dataSource.getListCustomer(new IDataCallBack<List<Customer>, String>() {
                @Override
                public void onDataSuccess(List<Customer> data) {
                    iView.showLoading(false);
                   // customerList = data;
                    iView.setListSuccess(data);
                }

                @Override
                public void onDataFailed(String error) {
                    iView.showLoading(false);
                    CommonFunc.showToastError(R.string.somthing_went_wrong);
                }
            });
      //  }

    }

    @Override
    public void setView(IListCustomerContact.IView view) {
        iView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
