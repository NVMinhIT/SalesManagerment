package com.example.salesmanagerment.screen.Invoice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InvoiceActivity extends BaseActivity implements IInvoiceContact.IView, View.OnClickListener {
    private InvoiceAdapter invoiceAdapter;
    private RecyclerView recyclerView;
    private TextView textViewMoney;
    private TextView tvBillNumber;
    private InvoicePresenter invoicePresenter;
    Double dSumMoney = 0.0;
    private ImageButton imageButtonBack;
    String DateCreateInventoryItem;
    private List<ItemOrder> orderList;
    private String OrderNo;
    Double aDouble;
    TextView dataCreateInvoice;
    private TableMappingCustom tableMappingCustom;
    private TextView tvAreaName, tvTableName;
    private Button btn_Done_Pay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        getData();
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tableMappingCustom = bundle.getParcelable(Constants.TABLE_MAPPING);
            orderList = bundle.getParcelableArrayList(Constants.EXTRAS_INVOICE_ENTITY_lIST);
            OrderNo = bundle.getString(Constants.EXTRAS_INVOICE_ENTITY);
            aDouble = bundle.getDouble(Constants.SUM_MONEY);

        }
        initView();
    }

    private void initView() {
        tvAreaName = findViewById(R.id.tv_Area_Name);
        btn_Done_Pay = findViewById(R.id.btn_Done_Pay);
        tvBillNumber = findViewById(R.id.tvBillNumber);
        tvTableName = findViewById(R.id.tv_Table_Name);
        dataCreateInvoice = findViewById(R.id.tvDateCreated);
        DateCreateInventoryItem = CommonFunc.getCurrentDateTime(new Date());
        dataCreateInvoice.setText(DateCreateInventoryItem);
        imageButtonBack = findViewById(R.id.btnBack);
        imageButtonBack.setOnClickListener(this);
        invoicePresenter = new InvoicePresenter();
        invoicePresenter.setView(this);
        textViewMoney = findViewById(R.id.tvMoneyHaveToPay);
        recyclerView = findViewById(R.id.rv_InventoryItem);
        invoiceAdapter = new InvoiceAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);

        tvAreaName.setText(tableMappingCustom.AreaName);
        tvTableName.setText(tableMappingCustom.TableName);
        tvBillNumber.setText(OrderNo);
        orderList = groupData(orderList);
        invoiceAdapter.setListData(orderList);
        recyclerView.setAdapter(invoiceAdapter);
        textViewMoney.setText(NumberFormat.getNumberInstance(Locale.US).format((aDouble)));
        btn_Done_Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private List<ItemOrder> groupData(List<ItemOrder> orderList) {
        List<ItemOrder> itemOrderGroup = new ArrayList<>();
        for (ItemOrder itemOrder : orderList) {
            boolean isExist = false;
            for (ItemOrder itemOrder1 : itemOrderGroup) {
                if (itemOrder1.ID.equalsIgnoreCase(itemOrder.ID)) {
                    isExist = true;
                    itemOrder1.Quantity = itemOrder1.Quantity + itemOrder.Quantity;
                    itemOrder1.TotalMoney = itemOrder1.TotalMoney + itemOrder.TotalMoney;
                }
            }
            if (!isExist) {
                itemOrderGroup.add(itemOrder);
            }
        }
        return itemOrderGroup;
    }

    @Override
    public void showLoading(boolean isShowLoading) {
        showDialog(isShowLoading);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            default:
                break;
        }
    }
}
