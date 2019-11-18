package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.screen.sales.createorder.CreateOrderActivity;
import com.example.salesmanagerment.screen.sales.customer.DialogFragmentAddCustomer;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;
import com.example.salesmanagerment.utils.Navigator;

import java.util.ArrayList;
import java.util.List;

public class ChooseInventoryItemActivity extends BaseActivity implements View.OnClickListener, IInventoryItemContact.IView {

    private ImageButton btnBack;
    private Button buttonAccept;
    private Navigator navigator;
    private ImageButton imageButtonAddInformation;
    private DialogFragmentAddCustomer dialogFragmentAddCustomer;
    private ChooseInventoryItemPresenter mPresenter;
    //private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ChooseInventoryItemAdapter mAdapter;
    private EditText edtSearch;
    private int TYPE_EDIT = 1;
    private int TYPE_CREATE = 0;
    private int type = TYPE_CREATE;
    private OrderEntity mOrderEntiy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_item);
        initViews();
        initEvents();
        mPresenter = new ChooseInventoryItemPresenter();
        mPresenter.setView(this);
        if (type == TYPE_CREATE) {
            mOrderEntiy = new OrderEntity().initOrderEntity();
            mPresenter.setOrderEntity(mOrderEntiy);
            mPresenter.onStart();
        }
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
        dialogFragmentAddCustomer = new DialogFragmentAddCustomer();
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
                List<ItemOrder> itemOrders = mAdapter.getTotalItemSelected();
                if (itemOrders.size() > 0) {
                    mPresenter.setOrderDetails(itemOrders);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(Constants.EXTRAS_INVENTORY_ITEM_LIST, (ArrayList<? extends Parcelable>) itemOrders);
                    navigator.startActivity(CreateOrderActivity.class, bundle);
                } else {
                    CommonFunc.showToastWarning(R.string.not_inventory_item_selected);
                }
                break;
            case R.id.imb_AddInformation:
                getSupportFragmentManager().beginTransaction().add(dialogFragmentAddCustomer, Constants.EXTRA_CLASS).commit();
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
}
