package com.example.salesmanagerment.screen.authentication.changepassword;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.request.ChangePassRequest;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;

public class ChangePassPresenter implements IChangePassContact.IPresenter {
    private IChangePassContact.IView iView;
    private DataSource dataSource;

    public ChangePassPresenter() {
        dataSource = DataSource.getInstance();
    }

    @Override
    public void changePass(final ChangePassRequest changePassRequest) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        iView.showLoading(true);
        dataSource.ChangePassword(changePassRequest, new IDataCallBack<Boolean, String>() {
            @Override
            public void onDataSuccess(Boolean data) {
                iView.showLoading(false);
                if(data){
                    CommonFunc.showToastSuccess(R.string.change_pass_success);
                    iView.changePassSuccess();
                } else {
                    CommonFunc.showToastSuccess("Mật khẩu cũ không chính xác");
                }
            }

            @Override
            public void onDataFailed(String error) {
                iView.showLoading(false);
                CommonFunc.showToastError(R.string.somthing_went_wrong);


            }
        });


    }

    @Override
    public void setView(IChangePassContact.IView view) {
        iView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
