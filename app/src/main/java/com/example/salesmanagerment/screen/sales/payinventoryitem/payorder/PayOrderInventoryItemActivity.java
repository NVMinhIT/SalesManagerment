package com.example.salesmanagerment.screen.sales.payinventoryitem.payorder;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.data.model.entity.OrderResponse;

public class PayOrderInventoryItemActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvAccountTable, tvArea, tvPerson;
    public static final String EXTRA_PAY_ORDER = "EXTRA_PAY_ORDER";
    OrderResponse orderResponse;
    private ImageButton btnBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order_inventory_item);
        initView();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            orderResponse = bundle.getParcelable("ORDER");
        }
        initData();
        initEvent();


    }

    private void initEvent() {
        btnBack.setOnClickListener(this);
    }

    private void initView() {
        tvAccountTable = findViewById(R.id.tvTable);
        tvArea = findViewById(R.id.tv_Area);
        tvPerson = findViewById(R.id.tvPerson);
        btnBack = findViewById(R.id.btn_Back_Pay);


    }

    private void initData() {
        tvArea.setText(orderResponse.AreaName);
        tvAccountTable.setText(orderResponse.TableName);
        tvPerson.setText(String.valueOf(orderResponse.NumberOfPeople));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Back_Pay:
                finish();
                break;
            default:
                break;
        }
    }

}
