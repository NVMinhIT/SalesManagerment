package com.example.salesmanagerment.screen.paydish;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class PayDishFragment extends BaseFragment implements PayDishContact.IView, ExpandListAdapter.kitchenCallBack {
    private ExpandListAdapter expandListAdapter;
    private ExpandableListView expandableListView;
    private PayDishPresenter mPresenter;
    int delete = 0;
    int accept = 1;

    public static PayDishFragment newInstance() {
        return new PayDishFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paydish, container, false);
        initViews(view);
        initEvents();
        return view;
    }

    private void initEvents() {

    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mPresenter = new PayDishPresenter();
        mPresenter.setView(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onStart();
    }

    private void initViews(View view) {
        expandableListView = view.findViewById(R.id.ExpandListView);
        expandListAdapter = new ExpandListAdapter(mActivity, new ArrayList<OrderResponse>(), new ArrayList<List<ItemOrder>>());
        expandListAdapter.setKitchenCallBack(this);
        expandableListView.setAdapter(expandListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandableListView.isGroupExpanded(groupPosition)) {
                    expandableListView.collapseGroup(groupPosition);
                } else {
                    mPresenter.getOrderDetailsByOrderID(((OrderResponse) expandListAdapter.getGroup(groupPosition)).OrderID, groupPosition);
                }
                return true;
            }
        });


    }

    @Override
    public void getOrderResponseSuccess(List<OrderResponse> items) {
        expandListAdapter.setLsOrderOverView(items);
    }

    @Override
    public void getListOrderDetailSuccess(List<OrderDetail> orderDetails, int groupPos) {
        showDialog(true);
        mPresenter.setItemOrders(orderDetails, groupPos);
    }

    @Override
    public void setItemOderByOrderDetailSuccess(List<ItemOrder> itemOrders, int groupPos) {
        showDialog(false);
        expandListAdapter.setItemsChild(itemOrders, groupPos);
        expandableListView.expandGroup(groupPos);
    }

    @Override
    public void updateStatusSuccess(int groupPosition, int itemPos, int type) {
        if (type == delete) {
            expandListAdapter.getLsItemOrderDetails().get(groupPosition).remove(itemPos);
            expandListAdapter.notifyDataSetChanged();
        } else {

        }
        expandListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading(boolean isShowLoading) {
        showDialog(isShowLoading);
    }

    @Override
    public void acceptCooking(ItemOrder itemOrder, int pos, int itemPos) {
        itemOrder.OrderDetailStatus = Constants.ORDER_DETAIL_PROCESSING;
        itemOrder.CookingQuantity = itemOrder.Quantity;
        mPresenter.updateStatusOrderDetail(CommonFunc.ItemOrderToOrderDetail(itemOrder), pos, itemPos, accept);
    }


    @Override
    public void declineCooking(ItemOrder itemOrder, int pos, int itemPos) {
        itemOrder.OrderDetailStatus = Constants.ORDER_DETAIL_CANCEL;
        mPresenter.updateStatusOrderDetail(CommonFunc.ItemOrderToOrderDetail(itemOrder), pos, itemPos, delete);
    }


}
