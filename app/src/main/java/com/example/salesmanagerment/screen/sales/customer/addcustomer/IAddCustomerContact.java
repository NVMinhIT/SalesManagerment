package com.example.salesmanagerment.screen.sales.customer.addcustomer;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;

public interface IAddCustomerContact {
    interface IView extends IBaseView {
        void gotoListCustomerScreen();
    }

    interface IPresenter extends IBasePresenter<IView> {
        void addCustomer();
    }
}
