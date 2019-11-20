package com.example.salesmanagerment.base;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.utils.Navigator;

public class BaseFragment extends Fragment {

    protected String loadingMsg = "Đang xử lý";
    protected Context mContext;
    protected BaseActivity mActivity;

    public void setLoadingMessage(int msg) {
        mActivity.setLoadingMessage(msg);
    }


    public void showDialog(Boolean isShow) {
        mActivity.showDialog(isShow);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        mActivity = (BaseActivity) context;
    }
}
