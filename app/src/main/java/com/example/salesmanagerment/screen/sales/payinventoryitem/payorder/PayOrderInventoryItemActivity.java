package com.example.salesmanagerment.screen.sales.payinventoryitem.payorder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.InventoryItemMapping;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.Order;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;

import java.util.ArrayList;
import java.util.List;

public class PayOrderInventoryItemActivity extends BaseActivity implements View.OnClickListener, PayOrderInventoryItemAdapter.ISendCallBack {
    private TextView tvAccountTable, tvArea, tvPerson;
    public static final String EXTRA_PAY_ORDER = "EXTRA_PAY_ORDER";
    OrderResponse orderResponse;
    private ImageButton btnBack;
    private RecyclerView rvPayInventoryItem;
    private PayOrderInventoryItemAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order_inventory_item);
        initView();
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            orderResponse = bundle.getParcelable("OrderID");
            getOrderDetailsByOrderID(orderResponse.OrderID);
        }
        initData();
        initEvent();


    }


    public void getOrderDetailsByOrderID(final String orderID) {
        showDialog(true);
        DataSource.getInstance().GetOrderDetailsByOrderID(orderID, new IDataCallBack<List<OrderDetail>, String>() {
            @Override
            public void onDataSuccess(List<OrderDetail> data) {
                showDialog(false);
                if (data != null) {
                    Order order = new Order();
                    order.OrderID = orderID;
                    setItemOrders(data);

                } else {
                    CommonFunc.showToastError(R.string.somthing_went_wrong);
                }
            }

            @Override
            public void onDataFailed(String error) {
                showDialog(false);
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    List<ItemOrder> mItemOrders = new ArrayList<>();

    public void setItemOrders(final List<OrderDetail> orderDetails) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                mItemOrders.clear();
                for (OrderDetail item : orderDetails) {
                    String desc = "";
                    if (CommonFunc.isNullOrEmpty(item.Description)) {
                        desc = item.Description;
                    }
                    InventoryItemMapping itemMapping = DataSource.getInstance().getInventoryItemMapping(item.InventoryItemID);
                    mItemOrders.add(new ItemOrder.Builder()
                            .setID(item.InventoryItemID)
                            .setQuantity(item.Quantity)
                            .setOrderID(item.OrderID)
                            .setOrderDetailID(item.OrderDetailID)
                            .setCookedQuantity(item.CookedQuantity)
                            .setServedQuantity(item.ServedQuantity)
                            .setCookingQuantity(item.CookingQuantity)
                            .setOrderDetailStatus(item.OrderDetailStatus)
                            .setCancelEmployeeID(item.CancelEmployeeID)
                            .setName(itemMapping.InventoryName)
                            .setPrice(itemMapping.UnitPrice)
                            .setUnitName(itemMapping.UnitName)
                            .setTotalMoney(itemMapping.UnitPrice * item.Quantity)
                            .setDescription(desc)
                            .build());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                showDialog(false);
                mAdapter.setListData(mItemOrders);
            }
        }.execute();
    }


    private void initEvent() {
        btnBack.setOnClickListener(this);
    }

    private void initView() {
        tvAccountTable = findViewById(R.id.tvTable);
        tvArea = findViewById(R.id.tv_Area);
        tvPerson = findViewById(R.id.tvPerson);
        btnBack = findViewById(R.id.btn_Back_Pay);
        rvPayInventoryItem = findViewById(R.id.rvPayInventoryItem);
        rvPayInventoryItem.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PayOrderInventoryItemAdapter(this);
        mAdapter.setISendCallBack(this);
        rvPayInventoryItem.setAdapter(mAdapter);
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

    @Override
    public void sendCallBack(ItemOrder itemOrder, int pos) {
        itemOrder.ServedQuantity = itemOrder.CookedQuantity;
        itemOrder.CookedQuantity = 0.0;
        updateStatusOrderDetail(CommonFunc.ItemOrderToOrderDetail(itemOrder), pos);

    }

    public void updateStatusOrderDetail(OrderDetail orderDetail, final int itemPos) {
        DataSource.getInstance().updateOrderDetail(orderDetail, new IDataCallBack<Boolean, String>() {
            @Override
            public void onDataSuccess(Boolean data) {
                if (data) {
                    mItemOrders.remove(itemPos);
                    mAdapter.setListData(mItemOrders);
                } else {
                    CommonFunc.showToastError(R.string.somthing_went_wrong);
                }
            }

            @Override
            public void onDataFailed(String error) {
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

}
