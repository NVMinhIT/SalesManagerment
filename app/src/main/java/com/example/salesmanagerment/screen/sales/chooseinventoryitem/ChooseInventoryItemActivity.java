package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.screen.sales.createorder.CreateOrderActivity;
import com.example.salesmanagerment.screen.sales.customer.choosecustomer.ListCustomerActivity;
import com.example.salesmanagerment.screen.sales.customer.choosecustomer.ListCustomerFragment;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;
import com.example.salesmanagerment.utils.Navigator;
import com.google.gson.Gson;

import java.util.List;

public class ChooseInventoryItemActivity extends BaseActivity implements View.OnClickListener, IInventoryItemContact.IView {

    private ImageButton btnBack;
    private Button buttonAccept;
    private Navigator navigator;
    private ImageButton imageButtonAddInformation;

    private ChooseInventoryItemPresenter mPresenter;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ChooseInventoryItemAdapter mAdapter;
    private EditText edtSearch;
    public static String EXTRA_ORDER_RESPONSE = "EXTRA_ORDER_RESPONSE";
    private String idCustomer;
    private String tableName;
    private String numOfPeople;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null) {
                if (intent.getAction().equals(ListCustomerFragment.ACTION_CUSTOMER_SELECTED)) {
                    try {
                        idCustomer = intent.getStringExtra(ListCustomerFragment.EXTRA_CUSTOMER_SELECTED);
                        if (idCustomer != null) {
                            mOrderEntiy.order.CustomerID = idCustomer;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    private BroadcastReceiver mReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null) {
                if (intent.getAction().equals(ListCustomerFragment.ACTION_ADD_CUSTOMER)) {
                    try {
                        idCustomer = intent.getStringExtra(ListCustomerFragment.ARG_CUSTOMER_ID);
                        if (!CommonFunc.isNullOrEmpty(idCustomer)) {
                            mOrderEntiy.order.CustomerID = idCustomer;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    public OrderEntity getOrderEntity() {
        return mOrderEntiy;
    }

    private OrderEntity mOrderEntiy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_item);
        initViews();
        initEvents();
        mPresenter = new ChooseInventoryItemPresenter();
        mPresenter.setView(this);
        Intent intent = getIntent();
        OrderResponse orderResponse = intent.getParcelableExtra(EXTRA_ORDER_RESPONSE);
        if (orderResponse == null) {
            mPresenter.getOrderNo();
        } else {
            mOrderEntiy = new OrderEntity();
            mOrderEntiy.order = orderResponse.getOrder();
            tableName = orderResponse.TableName;
            numOfPeople = String.valueOf(orderResponse.NumberOfPeople);
            mPresenter.getOrderDetailsByOrderID(mOrderEntiy.order.OrderID);
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(ListCustomerFragment.ACTION_CUSTOMER_SELECTED));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver2, new IntentFilter(ListCustomerFragment.ACTION_ADD_CUSTOMER));
    }

    private void initEvents() {
        btnBack.setOnClickListener(this);
        buttonAccept.setOnClickListener(this);
        imageButtonAddInformation.setOnClickListener(this);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.getInventoryItem(true, false);
//            }
//        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initViews() {
        imageButtonAddInformation = findViewById(R.id.imb_AddInformation);
        // swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        edtSearch = findViewById(R.id.edtSearch);
        navigator = new Navigator(this);
        buttonAccept = findViewById(R.id.btnAccept);
        btnBack = findViewById(R.id.btnBack);
        mRecyclerView = findViewById(R.id.rvInventoryItem);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ChooseInventoryItemAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                super.onBackPressed();
                break;
            case R.id.btnAccept:
//                List<ItemOrder> itemOrders = mAdapter.getTotalItemSelected();
//                 if (itemOrders.size() > 0) {
                // mPresenter.setOrderDetails(itemOrders);
                if (CommonFunc.isNullOrEmpty(mOrderEntiy.order.CustomerID)) {
                    CommonFunc.showToastWarning("Vui lòng chọn khách hàng");
                    return;
                }
                new MyAsyn().execute();
//                    // bundle.putString(Constants.EXTRAS_INVENTORY_ITEM_LIST, new Gson().toJson(itemOrders,));
//
//                } else {
//                    CommonFunc.showToastWarning(R.string.not_inventory_item_selected);
//                }
                break;
            case R.id.imb_AddInformation:
                Intent intent = new Intent();
                intent.setClass(this, ListCustomerActivity.class);
                intent.putExtra(ListCustomerFragment.ARG_CUSTOMER_ID, mOrderEntiy.order.CustomerID);
                navigator.startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoading(boolean isShowLoading) {
        showDialog(isShowLoading);
    }

    @Override
    public void getListSuccess(List<ItemOrder> items) {
        if (items != null && items.size() > 0) {
            mAdapter.setRootList(items);
            mAdapter.setListData(items);
        }
        // swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getOrderNoSuccess(String orderNo) {
        mOrderEntiy = new OrderEntity().initOrderEntity(orderNo);
        mPresenter.setOrderEntity(mOrderEntiy);
        mPresenter.onStart();
    }

    @Override
    public void getListOrderDetailSuccess(List<OrderDetail> orderDetails) {
        mOrderEntiy.orderDetails = orderDetails;
        mPresenter.setOrderEntity(mOrderEntiy);
        mPresenter.onStart();
    }

    @SuppressLint("StaticFieldLeak")
    public class MyAsyn extends AsyncTask<Void, Void, List<ItemOrder>> {

        @Override
        protected List<ItemOrder> doInBackground(Void... voids) {
            List<ItemOrder> itemOrders = mAdapter.getTotalItemSelected();
            if (itemOrders.size() > 0) {
                mPresenter.setOrderDetails(itemOrders);
                return itemOrders;
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<ItemOrder> orderDetails) {
            super.onPostExecute(orderDetails);
            if (orderDetails == null) {
                CommonFunc.showToastWarning(R.string.not_inventory_item_selected);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(Constants.EXTRAS_INVENTORY_ITEM_LIST, new Gson().toJson(orderDetails));
            bundle.putString(Constants.EXTRAS_TABLE_NAME, tableName);
            bundle.putString(Constants.EXTRAS_NUM_OF_PEOPLE, numOfPeople);
            bundle.putString(Constants.EXTRAS_ORDER_ENTITY, new Gson().toJson(mPresenter.getOrderEntity()));
            navigator.startActivity(CreateOrderActivity.class, bundle);

        }
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver2);
        super.onDestroy();
    }
}

