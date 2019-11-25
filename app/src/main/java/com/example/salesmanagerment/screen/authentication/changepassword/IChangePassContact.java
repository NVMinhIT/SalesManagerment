package com.example.salesmanagerment.screen.authentication.changepassword;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.model.request.ChangePassRequest;

public interface IChangePassContact {
    interface IView extends IBaseView {
        void changePassSuccess();
    }

    interface IPresenter extends IBasePresenter<IView> {
        void changePass(ChangePassRequest changePassRequest);
    }
}
