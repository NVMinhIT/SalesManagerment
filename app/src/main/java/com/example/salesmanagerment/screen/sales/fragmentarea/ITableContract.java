package com.example.salesmanagerment.screen.sales.fragmentarea;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;

import java.util.List;

public interface ITableContract {
    interface IView extends IBaseView {
        void getTableSuccess(List<TableMappingCustom> tableMappingCustomList);
    }

    interface IPresenter extends IBasePresenter<ITableContract.IView> {
        void getTableByAreaID(String AreaID);
    }
}
