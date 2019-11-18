package com.example.salesmanagerment.screen.sales.createorder;

import com.example.salesmanagerment.data.model.entity.Order;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.screen.sales.choosetable.IOptionTableContact;

public class CreateOrderPresenter implements ICreateOrderContact.IPresenter {

    private ICreateOrderContact.IView mView;
    private DataSource mDataSource;

    public CreateOrderPresenter(){
        mDataSource = DataSource.getInstance();
    }

    @Override
    public void saveOrder(Order order) {

    }

    @Override
    public void setView(ICreateOrderContact.IView view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
