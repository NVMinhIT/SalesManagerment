package com.example.salesmanagerment.screen.sales.createorder;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;

import java.util.List;

public interface ICreateOrderContact {
    interface IView extends IBaseView {
        void gotoOrdersScreen();

        void getOrderNoSuccess(String data);

        void getListOrderDetailSuccess(List<OrderDetail> orderDetails);

        void setItemOderByOrderDetailSuccess();
    }

    interface IPresenter extends IBasePresenter<ICreateOrderContact.IView> {
        void saveOrder(int type);

        void getOrderNo();

        void addItemOrders(List<ItemOrder> itemOrders);

        void getOrderDetailsByOrderID(String orderID);

        void setItemOrders(List<OrderDetail> orderDetails);
    }
}


