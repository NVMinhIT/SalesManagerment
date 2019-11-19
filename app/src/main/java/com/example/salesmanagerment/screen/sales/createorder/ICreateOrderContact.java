package com.example.salesmanagerment.screen.sales.createorder;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.Order;
import com.example.salesmanagerment.screen.sales.choosetable.IOptionTableContact;

public interface ICreateOrderContact{
    interface IView extends IBaseView {
        void gotoOrdersScreen();
    }

    interface IPresenter extends IBasePresenter<ICreateOrderContact.IView> {
        void saveOrder();
    }
}


