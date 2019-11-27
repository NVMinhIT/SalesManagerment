package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.InventoryItem;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CacheManager;
import com.example.salesmanagerment.utils.CommonFunc;

import java.util.ArrayList;
import java.util.List;

public class ChooseInventoryItemPresenter implements IInventoryItemContact.IPresenter {

    private static final String TAG = "ChooseInventoryItemPres";
    private DataSource mDataSource;
    private IInventoryItemContact.IView mView;

    public ChooseInventoryItemPresenter() {
        mDataSource = DataSource.getInstance();
    }

    @Override
    public void getInventoryItem(Boolean isLoadNewData, Boolean isShowLoading) {
        mView.showLoading(false);
        if (!isLoadNewData) {
            List<InventoryItem> itemList = CacheManager.cacheManager.getInventoryItems();
            if (itemList != null) {
                mView.getListSuccess(convertData(itemList));
            } else {
                getInventoryItemList(isShowLoading);
            }
            return;
        }
        getInventoryItemList(isShowLoading);
    }

    private List<ItemOrder> convertData(List<InventoryItem> itemList) {
        List<ItemOrder> list = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            InventoryItem item = itemList.get(i);
            list.add(new ItemOrder.Builder()
                    .setID(item.getInventoryItemID())
                    .setName(item.getInventoryItemName())
                    .setImage(item.getFileResource())
                    .setPrice(item.getUnitPrice())
                    .setUnitName(mDataSource.getUnitName(item.getInventoryItemID()))
                    .setQuantity(0.0)
                    .build());
        }
        return list;
    }

    private void getInventoryItemList(Boolean isLoading) {
        mView.showLoading(isLoading);
        mDataSource.getListInventoryItem(new IDataCallBack<List<InventoryItem>, String>() {
            @Override
            public void onDataSuccess(List<InventoryItem> data) {
                if (data != null) {
                    mView.getListSuccess(convertData(data));
                } else {
                    CommonFunc.showToastWarning(R.string.somthing_went_wrong);
                }
                mView.showLoading(false);
            }

            @Override
            public void onDataFailed(String error) {
                mView.showLoading(false);
                CommonFunc.showToastWarning(error);
            }
        });
    }

    @Override
    public void setView(IInventoryItemContact.IView view) {
        mView = view;
    }

    @Override
    public void onStart() {
        getInventoryItem(true, true);
    }

    @Override
    public void onStop() {

    }
}
