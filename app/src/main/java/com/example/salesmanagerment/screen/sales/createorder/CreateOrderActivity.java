package com.example.salesmanagerment.screen.sales.createorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;
import com.example.salesmanagerment.screen.Invoice.InvoiceActivity;
import com.example.salesmanagerment.screen.main.MainActivity;
import com.example.salesmanagerment.screen.sales.chooseinventoryitem.ChooseInventoryItemActivity;
import com.example.salesmanagerment.screen.sales.choosetable.OptionTableActivity;
import com.example.salesmanagerment.screen.sales.customer.choosecustomer.ListCustomerActivity;
import com.example.salesmanagerment.screen.sales.customer.choosecustomer.ListCustomerFragment;
import com.example.salesmanagerment.screen.sales.listorder.ListOrderFragment;
import com.example.salesmanagerment.screen.sales.promotion.SalesInventoryItem;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;
import com.example.salesmanagerment.utils.Navigator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CreateOrderActivity extends BaseActivity implements ICreateOrderContact.IView, View.OnClickListener, AddPersonDialogFragment.SetPerson {

    private ImageButton imageButtonSale;
    private Navigator navigator;
    private TextView tvAddPerson;
    private static final String EXTRA = "ListOrderDishActivity";
    private TextView tvOptionTable;
    private ImageButton imageButtonSend;
    private ImageButton imageButtonBack;
    private ImageButton btnClose;
    private RecyclerView recyclerView;
    private ImageButton imageButtonPay;
    private CreateOrderAdapter mAdapter;
    private Button btnAddMore;
    private TextView tvSumMoney;
    private TableMappingCustom mTableMappingCustom;
    public ImageButton imb_save_order;
    private CreateOrderPresenter mPresenter;
    private int type = Constants.TYPE_ADD;

    private OrderResponse mOrderResponse;
    private OrderEntity mOrderEntity;

    private ImageView imgCustomer;
    public static final int REQUEST_CODE_SELECT_INVENTORY_ITEM = 1001;
    public static final int REQUEST_CODE_TABLE = 1002;


    private BroadcastReceiver mReceiverSelectedCustomer = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null) {
                if (intent.getAction().equals(ListCustomerFragment.ACTION_CUSTOMER_SELECTED)) {
                    try {
                        mOrderEntity.order.CustomerID = intent.getStringExtra(ListCustomerFragment.EXTRA_CUSTOMER_SELECTED);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    private BroadcastReceiver mReceiverAddCustomer = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null) {
                if (intent.getAction().equals(ListCustomerFragment.ACTION_ADD_CUSTOMER)) {
                    try {
                        mOrderEntity.order.CustomerID = intent.getStringExtra(ListCustomerFragment.ARG_CUSTOMER_ID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        mPresenter = new CreateOrderPresenter();
        mPresenter.setView(this);
        getBundle();
    }

    private void getBundle() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt(Constants.EXTRAS_TYPE_SCREEN);
            if (type == Constants.TYPE_ADD) {
                //tạo mã Order mới
                mPresenter.getOrderNo();
            } else if (type == Constants.TYPE_EDIT) {
                String orderResponseString = bundle.getString(Constants.EXTRAS_ORDER_RESPONSE);
                if (!CommonFunc.isNullOrEmpty(orderResponseString)) {
                    mOrderResponse = new Gson().fromJson(orderResponseString, OrderResponse.class);
                    //Gán các trường cần thiết
                    mOrderEntity = new OrderEntity();
                    mOrderEntity.order = mOrderResponse.getOrder();
                    mTableMappingCustom = new TableMappingCustom();
                    mTableMappingCustom.AreaName = mOrderResponse.AreaName;
                    mTableMappingCustom.TableName = mOrderResponse.TableName;
                    //lấy danh sách orderDetail ra
                    mPresenter.getOrderDetailsByOrderID(mOrderEntity.order.OrderID);

                }
            }
        } else {
            CommonFunc.showToastError(R.string.somthing_went_wrong);
        }
    }

    private void initView() {
        tvSumMoney = findViewById(R.id.tv_sum_money);
        imgCustomer = findViewById(R.id.imgCustomer);
        imageButtonPay = findViewById(R.id.imb_pay);
        btnClose = findViewById(R.id.btnClose);
        recyclerView = findViewById(R.id.recycle_name_dish);
        imageButtonBack = findViewById(R.id.btn_Back_Order);
        imageButtonSend = findViewById(R.id.imb_send_chef);
        btnAddMore = findViewById(R.id.btnAddMore);
        tvOptionTable = findViewById(R.id.tv_Table);
        navigator = new Navigator(this);
        tvAddPerson = findViewById(R.id.tv_Add_Person);
        imageButtonSale = findViewById(R.id.imb_sale_dish);
        imageButtonSale.setOnClickListener(this);
        recyclerView = findViewById(R.id.recycle_name_dish);
        imb_save_order = findViewById(R.id.imb_save_order);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CreateOrderAdapter(this);
        mAdapter.setListData(mPresenter.mItemOrders);
        recyclerView.setAdapter(mAdapter);
        tvSumMoney.setText(NumberFormat.getNumberInstance(Locale.US).format((mPresenter.TotalMoney)));
    }

    private void initData() {
        tvAddPerson.setText(String.valueOf(mOrderResponse.NumberOfPeople));
        tvOptionTable.setText(mOrderResponse.TableName);
    }

    private void initEvent() {
        imageButtonPay.setOnClickListener(this);
        btnAddMore.setOnClickListener(this);
        imb_save_order.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        imageButtonSend.setOnClickListener(this);
        tvOptionTable.setOnClickListener(this);
        tvAddPerson.setOnClickListener(this);
        imageButtonBack.setOnClickListener(this);
        imgCustomer.setOnClickListener(this);

        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiverSelectedCustomer, new IntentFilter(ListCustomerFragment.ACTION_CUSTOMER_SELECTED));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiverAddCustomer, new IntentFilter(ListCustomerFragment.ACTION_ADD_CUSTOMER));
    }

    public static List<ItemOrder> getListTrack(String itemsString) {
        Type listType = new TypeToken<ArrayList<ItemOrder>>() {
        }.getType();
        return new Gson().fromJson(itemsString, listType);
    }

    public static OrderEntity getOrder(String itemOrderString) {
        return new Gson().fromJson(itemOrderString, OrderEntity.class);
    }


    @Override
    public void setMyName(String string) {
        tvAddPerson.setText(string);
        mPresenter.mOrderEntity.order.NumberOfPeople = Integer.parseInt(string);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_Table:
                Bundle bundle = new Bundle();
                navigator.startActivityForResult(OptionTableActivity.class, bundle, REQUEST_CODE_TABLE);
                break;
            case R.id.tv_Add_Person:
                AddPersonDialogFragment dialogFragment = AddPersonDialogFragment.getInstance(tvAddPerson.getText().toString());
                getSupportFragmentManager().beginTransaction().add(dialogFragment, EXTRA).commit();
                break;
            case R.id.imb_sale_dish:
                navigator.addFragment(R.id.content_order, SalesInventoryItem.newInstance(),
                        true, Navigator.NavigateAnim.BOTTOM_UP, SalesInventoryItem.class.getSimpleName());
                break;
            case R.id.imb_send_chef:

                CommonFunc.showToastSuccess(R.string.send_chef);
                break;
            case R.id.imb_pay:
                if (validateData()) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putParcelableArrayList(Constants.EXTRAS_INVOICE_ENTITY_lIST, (ArrayList<? extends Parcelable>) mPresenter.mItemOrders);
                    bundle1.putString(Constants.EXTRAS_INVOICE_ENTITY, mPresenter.mOrderEntity.order.OrderNo);
                    bundle1.putDouble(Constants.SUM_MONEY, mPresenter.TotalMoney);
                    bundle1.putParcelable(Constants.TABLE_MAPPING, mTableMappingCustom);
                    navigator.startActivity(InvoiceActivity.class, bundle1);
                }
                break;
            case R.id.imb_save_order:
                if (validateData()) {
                    mPresenter.saveOrder(type);
                }
                break;
            case R.id.btnAddMore:
                intent.setClass(this, ChooseInventoryItemActivity.class);
                mNavigator.startActivityForResult(intent, REQUEST_CODE_SELECT_INVENTORY_ITEM);
                break;
            case R.id.btn_Back_Order:
            case R.id.btnClose:
                finish();
                break;
            case R.id.imgCustomer:
                intent.setClass(this, ListCustomerActivity.class);
                intent.putExtra(ListCustomerFragment.ARG_CUSTOMER_ID, mOrderEntity.order.CustomerID);
                navigator.startActivity(intent);
                break;
            default:
                break;

        }
    }

    private Boolean validateData() {
        if (CommonFunc.isNullOrEmpty(mOrderEntity.order.CustomerID)) {
            CommonFunc.showToastInfo("Vui lòng chọn khách hàng!");
            return false;
        }
        if (mAdapter.getItemCount() == 0) {
            CommonFunc.showToastInfo("Vui lòng thêm món ăn!");
            return false;
        }

        if (CommonFunc.isNullOrEmpty(mOrderEntity.order.TableID)) {
            CommonFunc.showToastInfo(R.string.please_enter);
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_SELECT_INVENTORY_ITEM) {
                String listItemOrder = data.getStringExtra(Constants.EXTRA_ITEM_INVENTORY_ITEM_LIST);
                if (!CommonFunc.isNullOrEmpty(listItemOrder)) {
                    Type listType = new TypeToken<ArrayList<ItemOrder>>() {
                    }.getType();
                    List<ItemOrder> itemOrders = new Gson().fromJson(listItemOrder, listType);
                    mPresenter.addItemOrders(itemOrders);
                    tvSumMoney.setText(NumberFormat.getNumberInstance(Locale.US).format((mPresenter.TotalMoney)));
                    mAdapter.addData(itemOrders);
                } else {
                    CommonFunc.showToastError(R.string.somthing_went_wrong);
                }
            } else if (requestCode == REQUEST_CODE_TABLE) {
                mTableMappingCustom = data.getParcelableExtra(Constants.EXTRAS_TABLE_MAPPING);
                tvOptionTable.setText(mTableMappingCustom.TableName);
                mPresenter.mOrderEntity.order.TableID = mTableMappingCustom.TableID;
            }
        }
    }

    @Override
    public void showLoading(boolean isShowLoading) {
        //showDialog(isShowLoading);
    }

    @Override
    public void gotoOrdersScreen() {
        Intent intent = new Intent(ListOrderFragment.ACTION_ADD_LIST_ORDER);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        mNavigator.startActivityAtRoot(MainActivity.class);
    }

    @Override
    public void getOrderNoSuccess(String data) {
        mOrderEntity = new OrderEntity().initOrderEntity(data);
        mPresenter.setOrderEntity(mOrderEntity);
        initView();
        initEvent();
    }

    @Override
    public void getListOrderDetailSuccess(List<OrderDetail> orderDetails) {
        //có ds cũ  -> hiển thị -> dưới dạng ItemOrder
        mPresenter.setOrderEntity(mOrderEntity);
        mPresenter.setItemOrders(orderDetails);
    }

    @Override
    public void setItemOderByOrderDetailSuccess() {
        initView();
        initEvent();
        initData();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiverAddCustomer);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiverSelectedCustomer);
        super.onDestroy();
    }
}
