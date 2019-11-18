package com.example.salesmanagerment.screen.sales.createorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;
import com.example.salesmanagerment.screen.sales.choosetable.OptionTableActivity;
import com.example.salesmanagerment.screen.sales.fragmentarea.TableFragment;
import com.example.salesmanagerment.screen.sales.promotion.SalesInventoryItem;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;
import com.example.salesmanagerment.utils.Navigator;

import java.util.List;

public class CreateOrderActivity extends BaseActivity implements ICreateOrderContact.IView, View.OnClickListener, AddPersonDialogFragment.SetPerson {

    private ImageButton imageButtonSale;
    private Navigator navigator;
    private TextView tvAddPerson;
    private AddPersonDialogFragment addPersonDialogFragment;
    private static final String EXTRA = "ListOrderDishActivity";
    private TextView tvOptionTable;
    private ImageButton imageButtonSend;
    private ImageButton imageButtonBack;
    private RecyclerView recyclerView;
    private ImageButton imageButtonPay;
    private List<ItemOrder> mItemOrders;
    private CreateOrderAdapter mAdapter;
    private Button btnAddMore;
    private TextView tvSumMoney;
    private Double dSumMoney;
    private TableMappingCustom mTableMappingCustom;
    public static final String TABLE_ID_EXTRA = "TABLE_ID_EXTRA";
    public ImageButton imb_save_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        initView();
        initEvent();
        getData();
    }

    private void initEvent() {
        btnAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mItemOrders = bundle.getParcelableArrayList(Constants.EXTRAS_INVENTORY_ITEM_LIST);
            mAdapter.setListData(mItemOrders);
            recyclerView.setAdapter(mAdapter);

        }
    }

    private void initView() {
        tvSumMoney = findViewById(R.id.tv_sum_money);

        imageButtonPay = findViewById(R.id.imb_pay);
        imageButtonPay.setOnClickListener(this);
        recyclerView = findViewById(R.id.recycle_name_dish);
        imageButtonBack = findViewById(R.id.btn_Back_Order);
        imageButtonBack.setOnClickListener(this);
        imageButtonSend = findViewById(R.id.imb_send_chef);
        btnAddMore = findViewById(R.id.btnAddMore);
        imageButtonSend.setOnClickListener(this);
        tvOptionTable = findViewById(R.id.tv_Table);
        navigator = new Navigator(this);
        addPersonDialogFragment = new AddPersonDialogFragment();
        tvAddPerson = findViewById(R.id.tv_Add_Person);
        tvOptionTable.setOnClickListener(this);
        tvAddPerson.setOnClickListener(this);
        imageButtonSale = findViewById(R.id.imb_sale_dish);
        imageButtonSale.setOnClickListener(this);
        recyclerView = findViewById(R.id.recycle_name_dish);
        imb_save_order = findViewById(R.id.imb_save_order);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CreateOrderAdapter(this);
        tvSumMoney.setText("670,000");
        if (mTableMappingCustom != null && !CommonFunc.isNullOrEmpty(mTableMappingCustom.TableName)) {
            tvOptionTable.setText(mTableMappingCustom.TableName);
        }


    }

    @Override
    public void setMyName(String string) {
        tvAddPerson.setText(string);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Table:
                Bundle bundle = new Bundle();
               // bundle.putString(TABLE_ID_EXTRA, mTableMappingCustom.TableID);
                navigator.startActivityForResult(OptionTableActivity.class, bundle, Constants.REQUEST_CODE);
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
                break;
            case R.id.imb_save_order:

                //navigator.startActivity(BillActivity.class);
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            mTableMappingCustom = data.getParcelableExtra(TableFragment.EXTRA_NAME_TABLE);
            tvOptionTable.setText(mTableMappingCustom.TableName);
        }
    }

    @Override
    public void showLoading(boolean isShowLoading) {
        //showDialog(isShowLoading);
    }
}
