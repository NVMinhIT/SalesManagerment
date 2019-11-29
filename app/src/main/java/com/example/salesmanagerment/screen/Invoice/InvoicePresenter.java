package com.example.salesmanagerment.screen.Invoice;

import com.example.salesmanagerment.data.model.entity.Invoice;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.repository.DataSource;

import java.util.List;

public class InvoicePresenter implements IInvoiceContact.IPresenter {

    private IInvoiceContact.IView iView;
    public List<ItemOrder> mItemOrders;
    public DataSource mDataSource;


    public InvoicePresenter() {
        mDataSource = DataSource.getInstance();
    }


    @Override
    public void setView(IInvoiceContact.IView view) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
