package com.example.salesmanagerment.screen.authentication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.data.enums.EError;
import com.example.salesmanagerment.data.model.request.LoginRequest;
import com.example.salesmanagerment.screen.main.MainActivity;
import com.example.salesmanagerment.utils.CommonFunc;

public class LoginActivity extends BaseActivity implements  ILoginContract.IView {

    private LoginPresenter mPresenter;
    private EditText edtUserName;
    private EditText edtPassword;
    private CheckBox cbShowPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initEvents();
    }

    private void initEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                if (CommonFunc.isNullOrEmpty(username)) {
                    CommonFunc.showToastWarning(R.string
                    .username_empty);
                    edtUserName.requestFocus();
                    return;
                }
                if (CommonFunc.isNullOrEmpty(password)){
                    CommonFunc.showToastWarning(R.string
                            .pw_empty);
                    edtPassword.requestFocus();
                    return;
                }
                mPresenter.login(new LoginRequest(username, password));
            }
        });

        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void initViews() {
        mPresenter = new LoginPresenter();
        mPresenter.setView(this);
        setLoadingMessage(R.string.login_ing);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        cbShowPassword = findViewById(R.id.cbShowPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public void loginSuccess() {
        mNavigator.startActivityAtRoot(MainActivity.class);
    }

    @Override
    public void showLoading(boolean isShowLoading) {
        showDialog(isShowLoading);
    }
}
