package com.example.salesmanagerment.screen.sales.payinventoryitem.payorders;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.utils.Navigator;

public class PayOrdersActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnInventoryItem, btnTable;
    private Navigator navigator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);
        initView();
        initEvent();
    }

    private void initEvent() {
        btnInventoryItem.setOnClickListener(this);
        btnTable.setOnClickListener(this);
    }

    private void initView() {
        navigator = new Navigator(this);
        btnInventoryItem = findViewById(R.id.btnInventoryItem);
        btnTable = findViewById(R.id.btnTable);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInventoryItem:
                btnInventoryItem.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                btnInventoryItem.setTextColor(getResources().getColor(R.color.white));
                btnTable.setBackgroundColor(Color.WHITE);
                btnTable.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                navigator.addFragment(R.id.ContentRelativeLayout, new PayInventoryFragment(), false, Navigator.NavigateAnim.LEFT_RIGHT, PayInventoryFragment.class.getSimpleName());
                break;
            case R.id.btnTable:
                btnTable.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                btnTable.setTextColor(getResources().getColor(R.color.white));
                btnInventoryItem.setBackgroundColor(Color.WHITE);
                btnInventoryItem.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                navigator.addFragment(R.id.ContentRelativeLayout, new PayInventoryItemTableFragment(), false, Navigator.NavigateAnim.LEFT_RIGHT, PayInventoryItemTableFragment.class.getSimpleName());
                break;

            default:
                break;
        }
    }
}
