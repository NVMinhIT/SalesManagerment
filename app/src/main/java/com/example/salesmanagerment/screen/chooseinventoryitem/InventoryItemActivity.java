package com.example.salesmanagerment.screen.chooseinventoryitem;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;

public class InventoryItemActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_item);
        initViews();
        initEvents();
    }

    private void initEvents() {
        btnBack.setOnClickListener(this);
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                super.onBackPressed();
                break;
            default:
                break;
        }
    }
}
