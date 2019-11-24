package com.example.salesmanagerment.screen.sales.listorder;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.data.model.request.CancelOrderRequest;

import java.util.List;

public interface IListOrderContact {
    interface IView extends IBaseView {
        void getListSuccess(List<OrderResponse> orderResponse);

        void requestPaySuccess(Boolean isSuccess);

        void checkCancelOrderDone(Boolean isCancelable);

        void cancelOrderSuccess();
    }

    interface IPresenter extends IBasePresenter<IListOrderContact.IView> {

        void requestPay(String orderID);

        void getListOrder(Boolean isShowLoading, int orderStatus);

        void checkCancelOrder(String orderID);

        void cancelOrder(CancelOrderRequest cancelOrderRequest);

    }
}
