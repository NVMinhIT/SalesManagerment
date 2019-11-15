package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.InventoryItem;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.Unit;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;

import java.util.ArrayList;
import java.util.List;

public class ChooseInventoryItemPresenter implements IInventoryItemContact.IPresenter {

    private static final String TAG = "ChooseInventoryItemPres";
    private DataSource mDataSource;
    private IInventoryItemContact.IView mView;
    private List<Unit> mUnits = new ArrayList<>();

    public ChooseInventoryItemPresenter() {
        mDataSource = DataSource.getInstance();
    }

    @Override
    public void getInventoryItem(Boolean isLoadNewData, Boolean isShowLoading) {
        mView.showLoading(false);
        if (!isLoadNewData) {
            List<InventoryItem> itemList = mDataSource.getInventoryItemList();
            if (itemList != null) {
                mView.getListSuccess(convertData(itemList));
            } else {
                getListInventoryItem(isShowLoading);
            }
            return;
        }
        getListInventoryItem(isShowLoading);
    }

    public List<ItemOrder> convertData(List<InventoryItem> itemList) {
        List<ItemOrder> list = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            InventoryItem item = itemList.get(i);
            list.add(new ItemOrder(item.getUnitID(), 0, item.getInventoryItemName(), item.getFileResource(), item.getUnitPrice(), getUnitName(item.getUnitID())));
        }
        return list;
    }

    private String getUnitName(String unitID) {
        String unitName = "";
        for (Unit item : mUnits) {
            if (item.UnitID.equals(unitID)) {
                unitName = item.UnitName;
                break;
            }
        }
        return unitName;
    }

    private void getListUnit(final Boolean isShowLoading) {
        mDataSource.getListUnit(new IDataCallBack<List<Unit>, String>() {
            @Override
            public void onDataSuccess(List<Unit> data) {
                if (data != null) {
                    mUnits = mDataSource.getUnitList();
                    getInventoryItemList(isShowLoading);
                } else {
                    CommonFunc.showToastWarning(R.string.somthing_went_wrong);
                }
            }

            @Override
            public void onDataFailed(String error) {
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    private void getListInventoryItem(Boolean isShowLoading) {
        List<Unit> unit = mDataSource.getUnitList();
        if (unit == null) {
            getListUnit(isShowLoading);
        } else {
            mUnits = mDataSource.getUnitList();
            getInventoryItemList(isShowLoading);
        }

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
        getInventoryItem(false, true);
    }

    @Override
    public void onStop() {

    }
}
