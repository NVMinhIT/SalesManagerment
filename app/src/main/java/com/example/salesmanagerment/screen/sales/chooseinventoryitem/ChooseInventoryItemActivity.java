package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.screen.sales.createorder.AddPersonDialogFragment;
import com.example.salesmanagerment.screen.sales.createorder.CreateOrderActivity;
import com.example.salesmanagerment.screen.sales.customer.DialogFragmentAddCustomer;
import com.example.salesmanagerment.utils.Constants;
import com.example.salesmanagerment.utils.Navigator;

public class ChooseInventoryItemActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton btnBack;
    private Button buttonAccept;
    private Navigator navigator;
    private ImageButton imageButtonAddInformation;
    private DialogFragmentAddCustomer dialogFragmentAddCustomer;

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
        imageButtonAddInformation.setOnClickListener(this);
    }

    private void initViews() {
        dialogFragmentAddCustomer = new DialogFragmentAddCustomer();
        imageButtonAddInformation = findViewById(R.id.imb_AddInformation);
        navigator = new Navigator(this);
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
                break;
            case R.id.imb_AddInformation:
                getSupportFragmentManager().beginTransaction().add(dialogFragmentAddCustomer, Constants.EXTRA_CLASS).commit();
                break;
            default:
                break;
        }
    }
}
