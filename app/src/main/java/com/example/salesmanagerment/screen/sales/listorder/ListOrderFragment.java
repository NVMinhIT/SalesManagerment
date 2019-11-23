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
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;
import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.data.model.request.CancelOrderRequest;
import com.example.salesmanagerment.screen.main.MainActivity;
import com.example.salesmanagerment.screen.sales.chooseinventoryitem.ChooseInventoryItemActivity;
import com.example.salesmanagerment.screen.sales.listorder.dialog.ConfirmCancelOrderDialog;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;
import com.example.salesmanagerment.utils.Navigator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListOrderFragment extends BaseFragment implements View.OnClickListener, IListOrderContact.IView, IOnItemClickListener<OrderResponse>, ListOrderAdapter.IOrderRequest {
    private Navigator mNavigator;
    private FloatingActionButton btnAddOrder;
    private RecyclerView rvOrder;
    private ListOrderAdapter listOrderAdapter;
    private Spinner spinner;
    private TextView textViewOptionSearch;
    private ListOrderPresenter listOrderPresenter;
    private ConstraintLayout clWaterMark;
    private SwipeRefreshLayout swipeRefresh;
    public static String ACTION_ADD_LIST_ORDER = "ACTION_ADD_LIST_ORDER";
    private int currentStatus = Constants.ORDER_SERVING;
    private ImageButton imvCancelOrder;
    private String cancelOrderID;
    public static final String CANCEL_ORDER = "CANCEL_ORDER";

    public static ListOrderFragment newInstance() {
        return new ListOrderFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private BroadcastReceiver mReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (intent.getAction() != null) {
                    if (intent.getAction().equals(MainActivity.ACTION_SELECT_ORDER_RESERVING)) {
                        try {
                            currentStatus = intent.getIntExtra(MainActivity.ACTION_SELECT_ORDER_RESERVING, 0);
                            listOrderPresenter.getListOrder(true, currentStatus);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    };

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction().equals(ACTION_ADD_LIST_ORDER)) {
                listOrderPresenter.getListOrder(true, currentStatus);
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
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        rvOrder = view.findViewById(R.id.rvOrder);
        rvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        listOrderAdapter = new ListOrderAdapter(mActivity);
        listOrderAdapter.setOnClickListener(this);
        listOrderAdapter.setOrderCallBack(this);
        rvOrder.setAdapter(listOrderAdapter);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mReceiver, new IntentFilter(ACTION_ADD_LIST_ORDER));
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mReceiver2, new IntentFilter(MainActivity.ACTION_SELECT_ORDER_RESERVING));
        listOrderPresenter.getListOrder(true, currentStatus);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listOrderPresenter.getListOrder(false, currentStatus);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mReceiver2);
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
                swipeRefresh.setRefreshing(false);
            } else {
                clWaterMark.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            swipeRefresh.setRefreshing(false);
            e.printStackTrace();
        }
    }

    @Override
    public void requestPaySuccess(Boolean isSuccess) {
        if (isSuccess) {
            CommonFunc.showToastSuccess("Đã yêu cầu thanh toán");
            listOrderPresenter.getListOrder(true, currentStatus);
        } else {
            CommonFunc.showToastError("Yêu cầu thất bại");
        }
    }

    @Override
    public void checkCancelOrderDone(Boolean isCancelable) {
        if (isCancelable) { //nếu order có thể hủy - chưa gửi bếp -> show dialog confirm
            ConfirmCancelOrderDialog dialog = new ConfirmCancelOrderDialog();
            dialog.setCallBack(new ConfirmCancelOrderDialog.ConfirmCancelOrderCallBack() {
                @Override
                public void onCancelOrder(String cancelReason) {
                    listOrderPresenter.cancelOrder(new CancelOrderRequest(cancelOrderID, cancelReason));
                }
            });
            mActivity.getSupportFragmentManager().beginTransaction().add(dialog, CANCEL_ORDER).commit();
        } else {
            cancelOrderID = null;
            CommonFunc.showToastWarning("Order đã được gửi bếp, bạn không thể hủy");
        }
    }

    @Override
    public void cancelOrderSuccess() {
        listOrderPresenter.getListOrder(true, currentStatus);
    }

    @Override
    public void showLoading(boolean isShowLoading) {
        showDialog(isShowLoading);
    }

    @Override
    public void onItemClick(OrderResponse data) {
        CommonFunc.showToastWarning("Chọn order");
    }

    @Override
    public void onRequestPay(String orderID) {
        listOrderPresenter.requestPay(orderID);
    }

    @Override
    public void onCancelOrder(String orderID) {
        cancelOrderID = orderID;
        listOrderPresenter.checkCancelOrder(orderID);
    }

    @Override
    public void onSendKitchen(String orderID) {

    }

    @Override
    public void onPreview(String orderID) {

    }
}
