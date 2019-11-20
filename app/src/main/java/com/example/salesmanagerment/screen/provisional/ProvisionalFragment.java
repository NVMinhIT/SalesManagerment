package com.example.salesmanagerment.screen.provisional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;

public class ProvisionalFragment extends BaseFragment {
    public static ProvisionalFragment newInstance(){
        return new ProvisionalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_provisional, container, false);
        initViews(view);
        initEvents();
        return view;
    }

    private void initEvents() {

    }

    private void initViews(View view) {

    }
}
