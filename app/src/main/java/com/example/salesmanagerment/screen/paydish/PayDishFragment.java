package com.example.salesmanagerment.screen.paydish;

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

public class PayDishFragment extends BaseFragment {
    public static PayDishFragment newInstance(){
        return new PayDishFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paydish, container, false);
        initViews(view);
        initEvents();
        return view;
    }

    private void initEvents() {

    }

    private void initViews(View view) {

    }
}
