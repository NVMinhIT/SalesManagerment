package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.screen.sales.createorder.CreateOrderActivity;
import com.example.salesmanagerment.utils.Navigator;

public class ChooseInventoryItemActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton btnBack;
    private Button buttonAccept;
    private Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_item);
        initViews();
        initEvents();
    }

    private void initEvents() {
        btnBack.setOnClickListener(this);
        buttonAccept.setOnClickListener(this);
    }

    private void initViews() {
        navigator= new Navigator(this);
        buttonAccept = findViewById(R.id.btnAccept);
        btnBack = findViewById(R.id.btnBack);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                super.onBackPressed();
                break;
            case R.id.btnAccept:
                navigator.startActivity(CreateOrderActivity.class);
            default:
                break;
        }
    }
}
