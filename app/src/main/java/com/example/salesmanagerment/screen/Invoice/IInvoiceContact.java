package com.example.salesmanagerment.screen.Invoice;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.Invoice;
import com.example.salesmanagerment.data.model.entity.InvoiceDetail;

import java.util.List;

public interface IInvoiceContact {

    interface IView extends IBaseView {
        void paySuccess();

        void setBill(Invoice invoice, List<InvoiceDetail> invoiceList, int NumberInvoice);
    }

    interface IPresenter extends IBasePresenter<IView> {
        void pay(Invoice invoice);
    }
}
