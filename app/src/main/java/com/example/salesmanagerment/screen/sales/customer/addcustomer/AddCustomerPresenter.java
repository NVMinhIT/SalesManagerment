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
        if (customer.getCustomerCode().equals("")) {
            CommonFunc.showToastError(R.string.enter_code);
        } else if (customer.getCustomerName().equals("")) {
            CommonFunc.showToastError(R.string.enter_name);
        } else if (customer.getMobile().equals("")) {
            CommonFunc.showToastError(R.string.enter_phone);
        } else if (customer.getAddress().equals("")) {
            CommonFunc.showToastError(R.string.enter_address);

        } else {
            iView.showLoading(true);
            dataSource.createCustomer(customer, new IDataCallBack<Boolean, String>() {
                @Override
                public void onDataSuccess(Boolean data) {
                    if (data) {
                        CommonFunc.showToastSuccess(R.string.add_customer_success);
                        iView.gotoListCustomerScreen();
                        iView.showLoading(false);

                    } else {
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
