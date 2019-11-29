package com.example.salesmanagerment.screen.paydish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;

import java.util.List;

public class ExpandListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    public List<OrderResponse> lsOrderOverView;

    public void setKitchenCallBack(kitchenCallBack kitchenCallBack) {
        mKitchenCallBack = kitchenCallBack;
    }

    private kitchenCallBack mKitchenCallBack;

    public List<List<ItemOrder>> getLsItemOrderDetails() {
        return lsItemOrderDetails;
    }

    public void setLsOrderOverView(List<OrderResponse> lsOrderOverView) {
        this.lsOrderOverView = lsOrderOverView;
        if (lsOrderOverView != null) {
            lsItemOrderDetails.clear();
            for (OrderResponse orderResponse : lsOrderOverView) {
                lsItemOrderDetails.add(null);
            }
        }
        notifyDataSetChanged();
    }


    public void setItemsChild(List<ItemOrder> itemOrders, int groupPosition) {
        if (lsItemOrderDetails != null) {
            lsItemOrderDetails.set(groupPosition, itemOrders);
        }
        notifyDataSetChanged();
    }

    public void setLsItemOrderDetails(List<List<ItemOrder>> lsItemOrderDetails) {
        this.lsItemOrderDetails = lsItemOrderDetails;
        notifyDataSetChanged();
    }

    private List<List<ItemOrder>> lsItemOrderDetails;

    public ExpandListAdapter(Context mContext, List<OrderResponse> lsOrderOverView, List<List<ItemOrder>> lsItemOrderDetails) {
        this.mContext = mContext;
        this.lsOrderOverView = lsOrderOverView;
        this.lsItemOrderDetails = lsItemOrderDetails;
    }


    @Override
    public int getGroupCount() {
        return lsOrderOverView != null ? lsOrderOverView.size() : 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return (this.lsItemOrderDetails != null && lsItemOrderDetails.get(i) != null) ? lsItemOrderDetails.get(i).size() : 0;
    }

    @Override
    public Object getGroup(int i) {
        return lsOrderOverView.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return lsItemOrderDetails.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        OrderResponse orderResponse = (OrderResponse) getGroup(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expand_listview_group_order, null);
        }
        TextView tvOrderName = view.findViewById(R.id.tvOrderName);
        if (orderResponse != null) {
            tvOrderName.setText(orderResponse.OrderNo + ": " + orderResponse.AreaName + ", " + orderResponse.TableName);
        }


        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        final ItemOrder itemOrder = (ItemOrder) getChild(i, i1);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expand_listview_inventory_order, null);
        }
        TextView tvNameInventoryItem, tvAccount, tvUnit, txtDesc;
        ImageButton imb_delete_dish, imb_pay_dish, imb_return;
        tvAccount = view.findViewById(R.id.tvAccount);
        tvNameInventoryItem = view.findViewById(R.id.tvNameInventoryItem);
        imb_return = view.findViewById(R.id.imb_return);
        tvUnit = view.findViewById(R.id.tvUnit);
        txtDesc = view.findViewById(R.id.txtDesc);
        imb_delete_dish = view.findViewById(R.id.imb_delete_dish);
        imb_pay_dish = view.findViewById(R.id.imb_pay_dish);
        if (itemOrder.OrderDetailStatus == Constants.ORDER_DETAIL_SENT_KITCHEN) {
            imb_delete_dish.setVisibility(View.VISIBLE);
            imb_return.setVisibility(View.GONE);
            imb_pay_dish.setVisibility(View.VISIBLE);
        } else if (itemOrder.OrderDetailStatus == Constants.ORDER_DETAIL_PROCESSING) {
            imb_delete_dish.setVisibility(View.GONE);
            imb_return.setVisibility(View.VISIBLE);
            imb_pay_dish.setVisibility(View.GONE);
        }
        if (itemOrder != null) {
            tvNameInventoryItem.setText(itemOrder.Name);
            if (CommonFunc.isNullOrEmpty(itemOrder.Description)) {
                txtDesc.setText("Ghi chú: " + itemOrder.Description);
            }
            tvAccount.setText(itemOrder.Quantity.toString().replace(".0", ""));
            tvUnit.setText(itemOrder.UnitName);

        }
        imb_delete_dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKitchenCallBack.declineCooking(itemOrder, i, i1);
            }
        });

        imb_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKitchenCallBack.returnCooking(itemOrder, i, i1);
            }
        });

        imb_pay_dish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKitchenCallBack.acceptCooking(itemOrder, i, i1);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public interface kitchenCallBack {

        void acceptCooking(ItemOrder itemOrder, int pos, int itemPos);

        void declineCooking(ItemOrder itemOrder, int pos, int itemPos);

        void returnCooking(ItemOrder itemOrder, int pos, int itemPos);
    }
}
