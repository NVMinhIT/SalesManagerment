package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.InventoryItem;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;

import java.util.List;

public interface IInventoryItemContact {
    interface IView extends IBaseView {
        void getListSuccess(List<ItemOrder> items);

        void getOrderNoSuccess(String orderNo);

        void getListOrderDetailSuccess(List<OrderDetail> orderDetails);
    }

    interface IPresenter extends IBasePresenter<IInventoryItemContact.IView> {
        void getInventoryItem(Boolean isLoadNewData, Boolean isShowLoading);

        void getOrderNo();

        void getOrderDetailsByOrderID(String orderID);
    }
}
