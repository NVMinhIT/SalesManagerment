package com.example.salesmanagerment.screen.authentication.login;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.app.ErrorCode;
import com.example.salesmanagerment.data.model.entity.UserProfile;
import com.example.salesmanagerment.data.model.request.LoginRequest;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CacheManager;
import com.example.salesmanagerment.utils.CommonFunc;

public class LoginPresenter implements ILoginContract.IPresenter {

    private DataSource mDataSource;
    private ILoginContract.IView mView;

    public LoginPresenter() {
        mDataSource = DataSource.getInstance();
    }

    @Override
    public void login(final LoginRequest loginRequest) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        mView.showLoading(true);
        mDataSource.login(loginRequest, new IDataCallBack<String, String>() {
            @Override
            public void onDataSuccess(String data) {
                getUserInfoPro(loginRequest);
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
    public void getUserInfoPro(LoginRequest loginRequest) {
        mDataSource.getUserInfo(loginRequest, new IDataCallBack<UserProfile, String>() {
            @Override
            public void onDataSuccess(UserProfile data) {
                mView.showLoading(false);
                CacheManager.cacheManager.cacheUser(data);
                mView.loginSuccess();
            }

            @Override
            public void onDataFailed(String error) {
                mView.showLoading(false);
                CommonFunc.showToastSuccess(R.string.somthing_went_wrong);
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
