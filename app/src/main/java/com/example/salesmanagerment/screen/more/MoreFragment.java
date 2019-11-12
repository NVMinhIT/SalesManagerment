package com.example.salesmanagerment.screen.more;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;
import com.example.salesmanagerment.screen.authentication.changepassword.ChangePassActivity;
import com.example.salesmanagerment.screen.authentication.logout.LogOutDialogFragment;
import com.example.salesmanagerment.screen.infoapp.InfoAppActivity;
import com.example.salesmanagerment.utils.Navigator;

public class MoreFragment extends BaseFragment implements View.OnClickListener {
    private static final String LOGOUT_DIALOG = "LOGOUT_DIALOG";
    private LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4;
    private Navigator mNavigator;

    public static MoreFragment newInstance() {
        return new MoreFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mNavigator = new Navigator(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        baseInit();
        initViews(view);
        initEvents();
        return view;
    }

    private void initEvents() {
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
    }

    private void initViews(View view) {
        linearLayout1 = view.findViewById(R.id.content_layout1);
        linearLayout2 = view.findViewById(R.id.content_layout2);
        linearLayout3 = view.findViewById(R.id.content_layout3);
        linearLayout4 = view.findViewById(R.id.content_layout4);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.content_layout1:

                break;
            case R.id.content_layout2:
                mNavigator.startActivity(InfoAppActivity.class);
                break;
            case R.id.content_layout3:
                mNavigator.startActivity(ChangePassActivity.class);
                break;
            case R.id.content_layout4:
                mActivity.getSupportFragmentManager().beginTransaction().add(new LogOutDialogFragment(), LOGOUT_DIALOG).commit();
                break;
            default:
                break;
        }
    }
}
