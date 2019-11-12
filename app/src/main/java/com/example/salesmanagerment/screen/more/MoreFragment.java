package com.example.salesmanagerment.screen.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;
import com.example.salesmanagerment.screen.authentication.logout.LogOutDialogFragment;

import retrofit2.http.PUT;

public class MoreFragment extends BaseFragment implements View.OnClickListener {
    private final String LOGOUT_DIALOG = "LOGOUT_DIALOG";
    private Button btnLogout;

    public static MoreFragment newInstance(){
        return new MoreFragment();
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
        btnLogout.setOnClickListener(this);
    }

    private void initViews(View view) {
        btnLogout = view.findViewById(R.id.btnLogout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogout:
                mActivity.getSupportFragmentManager().beginTransaction().add(new LogOutDialogFragment(), LOGOUT_DIALOG).commit();
                break;
            default:
                break;
        }
    }
}
