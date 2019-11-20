package com.example.salesmanagerment.screen.sales.customer.choosecustomer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.data.model.entity.Customer;
import com.example.salesmanagerment.screen.sales.chooseinventoryitem.ChooseInventoryItemActivity;
import com.example.salesmanagerment.screen.sales.customer.addcustomer.AddCustomerFragment;
import com.example.salesmanagerment.utils.Navigator;

import java.util.List;

public class ListCustomerActivity extends BaseActivity implements IListCustomerContact.IView, View.OnClickListener, IOnItemClickListener<Customer> {
    private RecyclerView recyclerView;
    private ListCustomerAdapter listCustomerAdapter;
    private ListCustomerPresenter listCustomerPresenter;
    private ImageButton imbAddCustomer, btnBack;
    private AddCustomerFragment dialogFragmentAddCustomer;
    public static final String ACTION_ADD_CUSTOMER = "ACTION_ADD_CUSTOMER";
    private Button btnSave;
    public static final String ACTION_CUSTOMER_SELECTED = "ACTION_CUSTOMER_SELECTED";
    public static final String EXTRA_CUSTOMER_SELECTED = "EXTRA_CUSTOMER_SELECTED";
    private Navigator navigator;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ACTION_ADD_CUSTOMER)) {
                listCustomerPresenter.getListCustomer();

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);
        listCustomerPresenter = new ListCustomerPresenter();
        listCustomerPresenter.setView(this);
        baseInit();
        initView();
        initEvent();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mReceiver, new IntentFilter(ACTION_ADD_CUSTOMER));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver);

    }

    private void initEvent() {
        btnBack.setOnClickListener(this);
        imbAddCustomer.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    private void initView() {
        btnSave = findViewById(R.id.btnSave);
        navigator = new Navigator(this);
        dialogFragmentAddCustomer = new AddCustomerFragment();
        btnBack = findViewById(R.id.btnBack);
        imbAddCustomer = findViewById(R.id.imbAddCustomer);
        recyclerView = findViewById(R.id.rvListCustomer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listCustomerAdapter = new ListCustomerAdapter(this);
        recyclerView.setAdapter(listCustomerAdapter);
        listCustomerPresenter.getListCustomer();

    }

    @Override
    public void setListSuccess(List<Customer> customerList) {
        listCustomerAdapter.setListData(customerList);

    }

    @Override
    public void showLoading(boolean isShowLoading) {
        showDialog(isShowLoading);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imbAddCustomer:
                //getSupportFragmentManager().beginTransaction().add(dialogFragmentAddCustomer, Constants.EXTRA_CLASS).commit();
                navigator.addFragment(R.id.content_add, AddCustomerFragment.newInstance(), true, Navigator.NavigateAnim.BOTTOM_UP, AddCustomerFragment.class.getSimpleName());
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnSave:
                setIdCustomer(listCustomerAdapter.getCustomerId());
            default:
                break;
        }
    }

    private void setIdCustomer(String idCustomer) {
        try {
            Intent intent = new Intent(ACTION_CUSTOMER_SELECTED);
            intent.putExtra(EXTRA_CUSTOMER_SELECTED, idCustomer);
            //Toast.makeText(this, "hihi" + idCustomer, Toast.LENGTH_SHORT).show();
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigator.hideKeyboard();
    }

    @Override
    public void onItemClick(Customer data) {

    }
}
