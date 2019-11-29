package com.example.salesmanagerment.screen.sales.fragmentarea;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;

import java.util.ArrayList;
import java.util.List;

public class TablePresenter implements ITableContract.IPresenter {

    private static final String TAG = "ChooseInventoryItemPres";
    private DataSource mDataSource;
    private ITableContract.IView mView;
    private List<TableMappingCustom> mTables = new ArrayList<>();

    public TablePresenter() {
        mDataSource = DataSource.getInstance();
    }

    @Override
    public void getTableByAreaID(String AreaID) {
       // mView.showLoading(true);
        mDataSource.getListTableByAreaID(AreaID, new IDataCallBack<List<TableMappingCustom>, String>() {
            @Override
            public void onDataSuccess(List<TableMappingCustom> data) {
                mView.showLoading(false);
                //CommonFunc.showToastSuccess("" + data.size());
                mView.getTableSuccess(data);
            }

            @Override
            public void onDataFailed(String error) {
                mView.showLoading(false);
               // CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    @Override
    public void setView(ITableContract.IView view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
