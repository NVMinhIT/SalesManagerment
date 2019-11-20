package com.example.salesmanagerment.screen.sales.listorder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.screen.sales.chooseinventoryitem.ChooseInventoryItemActivity;
import com.example.salesmanagerment.utils.Navigator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListOrderFragment extends BaseFragment implements View.OnClickListener, IListOrderContact.IView {
    private Navigator mNavigator;
    private FloatingActionButton btnAddOrder;
    private RecyclerView rvOrder;
    private ListOrderAdapter listOrderAdapter;
    private Spinner spinner;
    private TextView textViewOptionSearch;
    private ListOrderPresenter listOrderPresenter;
    private ConstraintLayout clWaterMark;
    public static String ACTION_ADD_LIST_ORDER = "ACTION_ADD_LIST_ORDER";

    public static ListOrderFragment newInstance() {
        return new ListOrderFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ACTION_ADD_LIST_ORDER)) {
                listOrderPresenter.getListOrder();

            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, container, false);
        listOrderPresenter = new ListOrderPresenter();
        listOrderPresenter.setView(this);
        initView(view);
        initEvents();
        baseInit();
        mNavigator = mActivity.getNavigator();
        return view.getRootView();
    }

    private void initEvents() {
        btnAddOrder.setOnClickListener(this);
        textViewOptionSearch.setOnClickListener(this);


    }

    private void initView(View view) {
        clWaterMark = view.findViewById(R.id.clWaterMark);
        textViewOptionSearch = view.findViewById(R.id.tv_OptionSearch);
        btnAddOrder = view.findViewById(R.id.btnAddOrder);
        rvOrder = view.findViewById(R.id.rvOrder);
        rvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        listOrderAdapter = new ListOrderAdapter(mActivity);
        rvOrder.setAdapter(listOrderAdapter);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mReceiver, new IntentFilter(ACTION_ADD_LIST_ORDER));
        //listOrderPresenter.getListOrder();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAddOrder:
                //mNavigator.startActivity(ChooseInventoryItemActivity.class);
                break;
            case R.id.btnAddOrder:
                mNavigator.startActivity(ChooseInventoryItemActivity.class);
                break;
            case R.id.tv_OptionSearch:
                showOptionSearch();
            default:
                break;
        }
    }

    private void showOptionSearch() {
        PopupMenu popupMenu = new PopupMenu(mActivity, textViewOptionSearch);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_order, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.one:
                        textViewOptionSearch.setText(R.string.search_order);
                        break;
                    case R.id.two:
                        textViewOptionSearch.setText(R.string.search_table);
                        break;
                    case R.id.three:
                        textViewOptionSearch.setText(R.string.search_dish);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        popupMenu.show();
    }

    @Override
    public void getListSuccess(List<OrderResponse> orderResponse) {

        try {
            if (orderResponse != null && orderResponse.size() > 0) {
                clWaterMark.setVisibility(View.GONE);
                listOrderAdapter.setListData(orderResponse);
            } else {
                clWaterMark.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoading(boolean isShowLoading) {

    }
}
