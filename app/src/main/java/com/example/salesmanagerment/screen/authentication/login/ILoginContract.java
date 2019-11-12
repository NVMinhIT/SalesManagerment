package com.example.salesmanagerment.screen.authentication.login;

import com.example.salesmanagerment.base.IBasePresenter;
import com.example.salesmanagerment.base.IBaseView;
import com.example.salesmanagerment.data.enums.EError;
import com.example.salesmanagerment.data.model.request.LoginRequest;

public interface ILoginContract {
    interface IView extends IBaseView {
        void loginSuccess();
    }

    interface IPresenter extends IBasePresenter<IView> {

        void login(LoginRequest loginRequest);
    }
}
