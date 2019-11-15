package com.example.salesmanagerment.screen.sales.listorder;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.enums.EOrderStatus;
import com.example.salesmanagerment.screen.sales.choosetable.IOptionTableContact;

public class IListOrderContract {
    interface IView extends IBaseView {
        void getListSuccess();
    }

    interface IPresenter extends IBasePresenter<IOptionTableContact.IView> {

        void getOrderList(EOrderStatus orderStatus);

        void getOrderListByTableName(EOrderStatus orderStatus, String tableName );
    }
}
