package com.example.salesmanagerment.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.utils.Navigator;

public class BaseActivity extends AppCompatActivity {

    protected String loadingMsg = "Đang xử lý";

    public Navigator getNavigator() {
        return mNavigator;
    }

    protected Navigator mNavigator;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseInit();
    }

    public void setLoadingMessage(int msg) {
        mDialog.setMessage(getString(msg));
    }

    public void baseInit() {
        mNavigator = new Navigator(this);
        mDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
        mDialog.setMessage(loadingMsg);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
    }

    public void showDialog(Boolean isShow) {
        if (isShow) {
            if (mDialog != null && !mDialog.isShowing()) {
                mDialog.show();
            }
        } else {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
        }
    }

    @Override
    protected void onDestroy() {
        showDialog(false);
        super.onDestroy();
    }
}
