package com.example.salesmanagerment.screen.sales.listorder;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.OrderResponse;

import java.util.List;

public interface IListOrderContact {
    interface IView extends IBaseView {
        void getListSuccess(List<OrderResponse> orderResponse);
    }

    interface IPresenter extends IBasePresenter<IListOrderContact.IView> {

        void getListOrder();
    }
}
