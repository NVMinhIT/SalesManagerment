package com.example.salesmanagerment.screen.sales.customer.choosecustomer;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.Customer;
import com.example.salesmanagerment.data.model.entity.ItemOrder;

import java.util.List;

public interface IListCustomerContact {

    interface IView extends IBaseView {
        void setListSuccess(List<Customer> customerList);
    }

    interface IPresenter extends IBasePresenter<IView> {
        void getListCustomer();
    }
}
