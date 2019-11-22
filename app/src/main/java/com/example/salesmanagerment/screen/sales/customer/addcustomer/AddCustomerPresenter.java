package com.example.salesmanagerment.screen.sales.customer.addcustomer;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.Customer;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;

public class AddCustomerPresenter implements IAddCustomerContact.IPresenter {

    private IAddCustomerContact.IView iView;
    private DataSource dataSource;
    public Customer customer;

    public AddCustomerPresenter() {
        dataSource = DataSource.getInstance();
    }

    @Override
    public void addCustomer() {
        if (CommonFunc.isNullOrEmpty(customer.getCustomerCode())) {
            CommonFunc.showToastError(R.string.enter_code);
        } else if (CommonFunc.isNullOrEmpty(customer.getCustomerName())) {
            CommonFunc.showToastError(R.string.enter_name);
        } else if (CommonFunc.isNullOrEmpty(customer.getMobile())) {
            CommonFunc.showToastError(R.string.enter_phone);
        } else {
            iView.showLoading(true);
            dataSource.createCustomer(customer, new IDataCallBack<Boolean, String>() {
                @Override
                public void onDataSuccess(Boolean data) {
                    if (data) {
                        iView.showLoading(false);
                        CommonFunc.showToastSuccess(R.string.add_customer_success);
                        iView.gotoListCustomerScreen();

                    } else {
                        iView.showLoading(false);
                        CommonFunc.showToastSuccess(R.string.add_customer_failed);
                    }

                }

                @Override
                public void onDataFailed(String error) {
                    iView.showLoading(false);
                    CommonFunc.showToastSuccess(R.string.add_customer_failed);
                }
            });
        }
    }

    @Override
    public void setView(IAddCustomerContact.IView view) {
        iView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
