package com.example.salesmanagerment.screen.sales.choosetable;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.Area;
import com.example.salesmanagerment.data.model.entity.Unit;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.screen.sales.chooseinventoryitem.IInventoryItemContact;
import com.example.salesmanagerment.utils.CommonFunc;

import java.util.ArrayList;
import java.util.List;

public class OptionTablePresenter implements IOptionTableContact.IPresenter {

    private static final String TAG = "ChooseInventoryItemPres";
    private DataSource mDataSource;
    private IOptionTableContact.IView mView;
    private List<Area> mAreas = new ArrayList<>();

    public OptionTablePresenter() {
        mDataSource = DataSource.getInstance();
    }

    @Override
    public void setView(IOptionTableContact.IView view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getArea() {
        mAreas = mDataSource.getAreaList();
        if (mAreas != null){
            mView.getAreaSuccess(mAreas);
        } else {
            //mView.showLoading(true);
            mDataSource.getListArea(new IDataCallBack<List<Area>, String>() {
                @Override
                public void onDataSuccess(List<Area> data) {
                  // mView.showLoading(false);
                    mAreas = data;
                    mView.getAreaSuccess(mAreas);
                }

                @Override
                public void onDataFailed(String error) {
                   //mView.showLoading(false);
                    CommonFunc.showToastError(R.string.somthing_went_wrong);
                }
            });
        }
    }
}
