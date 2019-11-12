package com.example.salesmanagerment.screen.sales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;
import com.example.salesmanagerment.screen.chooseinventoryitem.InventoryItemActivity;
import com.example.salesmanagerment.utils.Navigator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SalesFragment extends BaseFragment implements View.OnClickListener {
    private Navigator mNavigator;
    private FloatingActionButton btnAddOrder;
    private TextView tvAddOrder;
    private RecyclerView rvOrder;

    public static SalesFragment newInstance() {
        return new SalesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, container, false);
        initView(view);
        initEvents();
        baseInit();
        mNavigator = mActivity.getNavigator();
        return view.getRootView();
    }

    private void initEvents() {
        btnAddOrder.setOnClickListener(this);
        tvAddOrder.setOnClickListener(this);
    }

    private void initView(View view) {
        btnAddOrder = view.findViewById(R.id.btnAddOrder);
        tvAddOrder = view.findViewById(R.id.tvAddOrder);
        rvOrder = view.findViewById(R.id.rvOrder);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddOrder:
            case R.id.tvAddOrder:
                mNavigator.startActivity(InventoryItemActivity.class);
                break;
            default:
                break;
        }
    }
}
