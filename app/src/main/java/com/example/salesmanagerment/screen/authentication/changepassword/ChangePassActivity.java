package com.example.salesmanagerment.screen.authentication.changepassword;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.data.model.entity.UserProfile;
import com.example.salesmanagerment.data.model.request.ChangePassRequest;
import com.example.salesmanagerment.screen.authentication.login.LoginActivity;
import com.example.salesmanagerment.utils.CacheManager;
import com.example.salesmanagerment.utils.CommonFunc;

public class ChangePassActivity extends BaseActivity implements View.OnClickListener, IChangePassContact.IView {
    ImageButton imageButtonBack;
    private Button btnChangePass;
    private ChangePassPresenter changePassPresenter;
    private EditText edtOldPassWord, edtNewPassWord, edtNewPassWordAgain;
    private UserProfile userProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        userProfile = new UserProfile();
        initView();
        initEvent();
        baseInit();
    }

    private void initEvent() {
        imageButtonBack.setOnClickListener(this);
        btnChangePass.setOnClickListener(this);
    }

    private void initView() {
        userProfile = CacheManager.cacheManager.getUser();
        changePassPresenter = new ChangePassPresenter();
        changePassPresenter.setView(this);
        imageButtonBack = findViewById(R.id.btn_back_logout);
        btnChangePass = findViewById(R.id.btnChangePass);
        edtOldPassWord = findViewById(R.id.edtOldPassWord);
        edtNewPassWord = findViewById(R.id.edtNewPassWord);
        edtNewPassWordAgain = findViewById(R.id.edtNewPassWordAgain);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back_logout:
                finish();
                break;
            case R.id.btnChangePass:
                if (CommonFunc.isNullOrEmpty(edtOldPassWord.getText().toString())) {
                    CommonFunc.showToastWarning(R.string
                            .old_empty);
                    edtOldPassWord.requestFocus();
                    return;
                }
                if (CommonFunc.isNullOrEmpty(edtNewPassWord.getText().toString())) {
                    CommonFunc.showToastWarning(R.string
                            .new_pass);
                    edtNewPassWord.requestFocus();
                    return;
                }
                if (CommonFunc.isNullOrEmpty(edtNewPassWordAgain.getText().toString())) {
                    CommonFunc.showToastWarning(R.string
                            .new_pass_again);
                    edtNewPassWordAgain.requestFocus();
                    return;
                }


                String username = userProfile.getUserName();
                //String password = userProfile.getPassword();
                String password = edtOldPassWord.getText().toString();
                String NewPass = edtNewPassWordAgain.getText().toString();
                if (!NewPass.equals(edtNewPassWord.getText().toString())) {
                    CommonFunc.showToastWarning(R.string
                            .no_yes);
                } else {
                    mNavigator.hideKeyboard();
                    changePassPresenter.changePass(new ChangePassRequest(username, password, NewPass));
                }
            default:
                break;
        }
    }

    @Override
    public void changePassSuccess() {
        mNavigator.startActivity(LoginActivity.class);
    }

    @Override
    public void showLoading(boolean isShowLoading) {
        showDialog(isShowLoading);
    }
}
