package com.example.salesmanagerment.base;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.utils.Navigator;

public class BaseFragment extends Fragment {

    protected String loadingMsg = "Đang xử lý";
    private ProgressDialog mDialog;
    protected Context mContext;
    protected BaseActivity mActivity;

    public void setLoadingMessage(String msg) {
        mDialog.setMessage(msg);
    }

    public void baseInit() {
        mDialog = new ProgressDialog(mContext, R.style.AppCompatAlertDialogStyle);
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        mActivity = (BaseActivity) context;
    }
}
