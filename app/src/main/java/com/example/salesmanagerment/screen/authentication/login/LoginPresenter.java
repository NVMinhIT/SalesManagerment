package com.example.salesmanagerment.screen.authentication.login;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.app.ErrorCode;
import com.example.salesmanagerment.data.model.request.LoginRequest;
import com.example.salesmanagerment.data.repo.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;

public class LoginPresenter implements ILoginContract.IPresenter {

    private DataSource mDataSource;
    private ILoginContract.IView mView;

    public LoginPresenter() {
        mDataSource = DataSource.getInstance();
    }

    @Override
    public void login(LoginRequest loginRequest) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        mView.showLoading(true);
        mDataSource.login(loginRequest, new IDataCallBack<String, String>() {
            @Override
            public void onDataSuccess(String data) {
                mView.loginSuccess();
                mView.showLoading(false);
            }

            @Override
            public void onDataFailed(String error) {
                if (error.equals(ErrorCode.UN_AUTHORIZED)) {
                    CommonFunc.showToastWarning(R.string.login_error);
                }
                mView.showLoading(false);
            }
        });
    }

    @Override
    public void setView(ILoginContract.IView view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
