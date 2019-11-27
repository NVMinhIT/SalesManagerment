package com.example.salesmanagerment.screen.paydish;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.salesmanagerment.R;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ExpandListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> itemOrderList;
    private HashMap<String, List<String>> listHashMap;

    public ExpandListAdapter(Context mContext, List<String> itemOrderList, HashMap<String, List<String>> listHashMap) {
        this.mContext = mContext;
        this.itemOrderList = itemOrderList;
        this.listHashMap = listHashMap;
    }


    @Override
    public int getGroupCount() {
        return itemOrderList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.listHashMap.get(this.itemOrderList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listHashMap.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(itemOrderList.get(i)).get(i1);
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

        //OrderResponse orderResponse = (OrderResponse) getGroup(i);
        String orderName = (String) getGroup(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expand_listview_group_order, null);
        }
        TextView tvOrderName = view.findViewById(R.id.tvOrderName);
//        TextView tvAreaName = view.findViewById(R.id.tvAreaName);
        tvOrderName.setTypeface(null, Typeface.BOLD);
        tvOrderName.setText(orderName);
        // tvOrderName.setText(orderResponse.OrderNo);
//        tvAreaName.setTypeface(null, Typeface.BOLD);
//        tvAreaName.setText(orderResponse.AreaName);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        //ItemOrder itemOrder = (ItemOrder) getChild(i, i1);
        String nameInventoryItem = (String) getChild(i, i1);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expand_listview_inventory_order, null);
        }
        TextView tvNameInventoryItem = view.findViewById(R.id.tvNameInventoryItem);
        tvNameInventoryItem.setTypeface(null, Typeface.BOLD);
        tvNameInventoryItem.setText(nameInventoryItem);
//        TextView tvAccount = view.findViewById(R.id.tvAccount);
//        TextView tvUnit = view.findViewById(R.id.tvUnit);
        //tvNameInventoryItem.setText(itemOrder.Name);
//        tvAccount.setText(String.valueOf(itemOrder.Quantity));
//        tvUnit.setText(itemOrder.UnitName);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
