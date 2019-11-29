package com.example.salesmanagerment.screen.paydish;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderResponse;

import java.util.List;

public interface PayDishContact {
    interface IView extends IBaseView {
        void getOrderResponseSuccess(List<OrderResponse> items);

        void getListOrderDetailSuccess(List<OrderDetail> orderDetails, int groupPos);

        void setItemOderByOrderDetailSuccess(List<ItemOrder> itemOrders, int groupPos);

        void updateStatusSuccess(int groupPosition , int itemPos, int type);
    }

    interface IPresenter extends IBasePresenter<IView> {

        void getOrderDetailsByOrderID(String orderID, int groupPos);

        void setItemOrders(List<OrderDetail> orderDetails, int groupPos);

        void updateStatusOrderDetail(OrderDetail orderDetail, int groupPosition, int itemPos, int type);
    }
}
