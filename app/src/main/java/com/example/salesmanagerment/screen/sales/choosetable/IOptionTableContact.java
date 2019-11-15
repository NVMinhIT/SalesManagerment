package com.example.salesmanagerment.screen.sales.choosetable;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.entity.Area;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;

import java.util.List;

public interface IOptionTableContact {
    interface IView extends IBaseView {
        void getAreaSuccess(List<Area> areaList);
    }

    interface IPresenter extends IBasePresenter<IView> {
        void getArea();
    }
}
