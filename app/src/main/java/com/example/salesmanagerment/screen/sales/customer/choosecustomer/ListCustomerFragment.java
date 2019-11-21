package com.example.salesmanagerment.screen.sales.customer.choosecustomer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;
import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.data.model.entity.Customer;
import com.example.salesmanagerment.screen.sales.customer.addcustomer.AddCustomerFragment;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Navigator;

import java.util.List;

public class ListCustomerFragment extends BaseFragment implements IListCustomerContact.IView, View.OnClickListener, IOnItemClickListener<Customer> {
    private RecyclerView recyclerView;
    private ListCustomerAdapter listCustomerAdapter;
    private ListCustomerPresenter listCustomerPresenter;
    private ImageButton imbAddCustomer, btnBack;
    private AddCustomerFragment dialogFragmentAddCustomer;
    public static final String ACTION_ADD_CUSTOMER = "ACTION_ADD_CUSTOMER";
    private Button btnSave;
    public static final String ACTION_CUSTOMER_SELECTED = "ACTION_CUSTOMER_SELECTED";
    public static final String EXTRA_CUSTOMER_SELECTED = "EXTRA_CUSTOMER_SELECTED";
    public static final String ARG_CUSTOMER_ID = "ARG_CUSTOMER_ID";
    private Navigator mNavigator;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ACTION_ADD_CUSTOMER)) {
                //listCustomerPresenter.getListCustomer();

            }
        }
    };

    public static ListCustomerFragment newInstance(String customerID) {
        ListCustomerFragment fragment = new ListCustomerFragment();
        if (!CommonFunc.isNullOrEmpty(customerID)) {
            Bundle bundle = new Bundle();
            bundle.putString(ARG_CUSTOMER_ID, customerID);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_customer, container, false);
        initView(view);
        return view.getRootView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(mActivity).unregisterReceiver(mReceiver);

    }

    private void initEvent() {
        btnBack.setOnClickListener(this);
        imbAddCustomer.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    private void initView(View view) {
        btnSave = view.findViewById(R.id.btnSave);
        dialogFragmentAddCustomer = new AddCustomerFragment();
        btnBack = view.findViewById(R.id.btnBack);
        imbAddCustomer = view.findViewById(R.id.imbAddCustomer);
        recyclerView = view.findViewById(R.id.rvListCustomer);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        String customerID = null;
        Bundle bundle = getArguments();
        if (bundle != null) {
            customerID = bundle.getString(ARG_CUSTOMER_ID);
        }

        listCustomerAdapter = new ListCustomerAdapter(mActivity, customerID);
        recyclerView.setAdapter(listCustomerAdapter);
        listCustomerPresenter = new ListCustomerPresenter();
        listCustomerPresenter.setView(this);
        listCustomerPresenter.getListCustomer();
        initEvent();
        LocalBroadcastManager.getInstance(mActivity).registerReceiver(mReceiver, new IntentFilter(ACTION_ADD_CUSTOMER));
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
                mNavigator.addFragment(R.id.content_add, AddCustomerFragment.newInstance(), true, Navigator.NavigateAnim.BOTTOM_UP, AddCustomerFragment.class.getSimpleName());
                break;
            case R.id.btnBack:
                mActivity.finish();
                break;
            case R.id.btnSave:
                setIdCustomer(listCustomerAdapter.customerID);
            default:
                break;
        }
    }

    private void setIdCustomer(String idCustomer) {
        try {
            Intent intent = new Intent(ACTION_CUSTOMER_SELECTED);
            intent.putExtra(EXTRA_CUSTOMER_SELECTED, idCustomer);
            LocalBroadcastManager.getInstance(mActivity).sendBroadcast(intent);
            mActivity.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mNavigator = mActivity.getNavigator();
    }

    @Override
    public void onItemClick(Customer data) {

    }
}
