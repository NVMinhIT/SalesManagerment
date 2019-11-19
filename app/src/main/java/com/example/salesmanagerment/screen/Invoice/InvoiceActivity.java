package com.example.salesmanagerment.screen.Invoice;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.data.model.entity.Invoice;
import com.example.salesmanagerment.data.model.entity.InvoiceDetail;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InvoiceActivity extends AppCompatActivity implements IInvoiceContact.IView, View.OnClickListener {
    private InvoiceAdapter invoiceAdapter;
    private RecyclerView recyclerView;
    private TextView textViewMoney;
    private InvoicePresenter invoicePresenter;
    Double dSumMoney = 0.0;
    private ImageButton imageButtonBack;
    String DateCreateInventoryItem;
    private List<ItemOrder> orderList;
    private OrderEntity orderEntity;
    Double aDouble;
    TextView dataCreateInvoice;
    private TableMappingCustom tableMappingCustom;
    private TextView tvAreaName, tvTableName;


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
            orderEntity = bundle.getParcelable(Constants.EXTRAS_INVOICE_ENTITY);
            aDouble = bundle.getDouble(Constants.SUM_MONEY);

        }
        initView();
        tvAreaName.setText(tableMappingCustom.AreaName);
        tvTableName.setText(tableMappingCustom.TableName);
    }

    private void initView() {
        tvAreaName = findViewById(R.id.tv_Area_Name);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        invoiceAdapter.setListData(orderList);
        recyclerView.setAdapter(invoiceAdapter);
        textViewMoney.setText(NumberFormat.getNumberInstance(Locale.US).format((aDouble)));


    }


    @Override
    public void paySuccess() {

    }

    @Override
    public void setBill(Invoice invoice, List<InvoiceDetail> invoiceList, int NumberInvoice) {

    }

    @Override
    public void showLoading(boolean isShowLoading) {

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
