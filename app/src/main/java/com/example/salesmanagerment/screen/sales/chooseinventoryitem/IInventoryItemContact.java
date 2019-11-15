package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.InventoryItem;
import com.example.salesmanagerment.data.model.entity.ItemOrder;

import java.util.List;

public interface IInventoryItemContact {
    interface IView extends IBaseView {
        void getListSuccess(List<ItemOrder> items);
    }

    interface IPresenter extends IBasePresenter<IInventoryItemContact.IView> {
        void getInventoryItem(Boolean isLoadNewData, Boolean isShowLoading);
    }
}
