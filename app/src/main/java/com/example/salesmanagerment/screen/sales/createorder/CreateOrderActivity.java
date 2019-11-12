package com.example.salesmanagerment.screen.sales.createorder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.screen.sales.choosetable.OptionTableActivity;
import com.example.salesmanagerment.screen.sales.promotion.SalesInventoryItem;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;
import com.example.salesmanagerment.utils.Navigator;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderActivity extends BaseActivity implements View.OnClickListener,AddPersonDialogFragment.SetPerson {

    ImageButton imageButtonSale;
    Navigator navigator;
    TextView tvAddPerson;
    AddPersonDialogFragment addPersonDialogFragment;
    private static final String EXTRA = "ListOrderDishActivity";
    private TextView tvOptionTable;
    private ImageButton imageButtonSend;
    private ImageButton imageButtonBack;
    private RecyclerView recyclerView;
    private ImageButton imageButtonPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        baseInit();
        initView();
    }

    private void initView() {
        imageButtonPay = findViewById(R.id.imb_pay);
        imageButtonPay.setOnClickListener(this);
        recyclerView = findViewById(R.id.recycle_name_dish);
        imageButtonBack = findViewById(R.id.btn_Back_Order);
        imageButtonBack.setOnClickListener(this);
        imageButtonSend = findViewById(R.id.imb_send_chef);
        imageButtonSend.setOnClickListener(this);
        tvOptionTable = findViewById(R.id.tv_Table);
        navigator = new Navigator(this);
        addPersonDialogFragment = new AddPersonDialogFragment();
        tvAddPerson = findViewById(R.id.tv_Add_Person);
        tvOptionTable.setOnClickListener(this);
        tvAddPerson.setOnClickListener(this);
        imageButtonSale = findViewById(R.id.imb_sale_dish);
        imageButtonSale.setOnClickListener(this);
    }

    @Override
    public void setMyName(String string) {
        tvAddPerson.setText(string);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Table:
                navigator.startActivityForResult(OptionTableActivity.class, new Bundle(), Constants.REQUEST_CODE);
                break;
            case R.id.tv_Add_Person:
                getSupportFragmentManager().beginTransaction().add(addPersonDialogFragment, EXTRA).commit();
                break;
            case R.id.imb_sale_dish:
                navigator.addFragment(R.id.content_order, SalesInventoryItem.newInstance(),
                        true, Navigator.NavigateAnim.BOTTOM_UP, SalesInventoryItem.class.getSimpleName());
                break;
            case R.id.imb_send_chef:
                CommonFunc.showToastSuccess(R.string.send_chef);
                break;
            case R.id.btn_Back_Order:
                finish();
                break;
            case R.id.imb_pay:
                //navigator.startActivity(BillActivity.class);
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String s = data.getStringExtra("NAME");
            tvOptionTable.setText(s);
        }
    }
}
