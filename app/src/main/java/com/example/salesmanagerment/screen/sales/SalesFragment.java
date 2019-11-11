package com.example.salesmanagerment.screen.sales;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.utils.Navigator;

import java.util.Objects;

public class SalesFragment extends Fragment {
    private Navigator navigator;

    public static SalesFragment newInstance() {
        return new SalesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, container, false);
        initView(view);
        return view.getRootView();
    }

    private void initView(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            navigator = new Navigator(Objects.requireNonNull(getActivity()));
        }

    }


}
