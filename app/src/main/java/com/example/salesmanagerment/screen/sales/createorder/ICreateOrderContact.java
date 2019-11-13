package com.example.salesmanagerment.screen.sales.createorder;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.screen.sales.choosetable.IChooseTableContact;

public interface ICreateOrderContact{
    interface IView extends IBaseView {

    }

    interface IPresenter extends IBasePresenter<IChooseTableContact.IView> {

    }
}


