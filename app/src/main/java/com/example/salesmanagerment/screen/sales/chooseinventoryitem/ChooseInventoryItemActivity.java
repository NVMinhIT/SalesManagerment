package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;
import com.google.gson.Gson;

import java.util.List;

public class ChooseInventoryItemActivity extends BaseActivity implements View.OnClickListener, IInventoryItemContact.IView {

    private ImageButton btnBack;
    private Button buttonAccept;
    private ChooseInventoryItemPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private ChooseInventoryItemAdapter mAdapter;
    private EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_item);
        initViews();
        initEvents();
        mPresenter = new ChooseInventoryItemPresenter();
        mPresenter.setView(this);
        mPresenter.onStart();
    }

    private void initEvents() {
        btnBack.setOnClickListener(this);
        buttonAccept.setOnClickListener(this);
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
        edtSearch = findViewById(R.id.edtSearch);
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
                showLoading(true);
                new GetItemOrder().execute();
                break;
            default:
                break;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class GetItemOrder extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... strings) {
            List<ItemOrder> itemOrders = mAdapter.getTotalItemSelected();
            if (itemOrders.size() == 0) {
                CommonFunc.showToastInfo("Bạn chưa chọn món ăn nào");
                return null;
            } else
                return new Gson().toJson(itemOrders);
        }

        @Override
        protected void onPostExecute(String itemOrders) {
            super.onPostExecute(itemOrders);
            showLoading(false);
            if (itemOrders != null) {
                Intent intent = new Intent();
                intent.putExtra(Constants.EXTRA_ITEM_INVENTORY_ITEM_LIST, itemOrders);
                setResult(Activity.RESULT_OK, intent);
                finish();
            } else {
                CommonFunc.showToastError("Bạn chưa chọn món ăn nào");
            }
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

