package com.example.salesmanagerment.screen.Invoice;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.ItemOrder;

import java.util.List;

public interface IInvoiceContact {

    interface IView extends IBaseView {
    }

    interface IPresenter extends IBasePresenter<IView> {
    }
}
