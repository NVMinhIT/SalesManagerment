package com.example.salesmanagerment.screen.paydish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PayDishFragment extends BaseFragment {
    private ExpandListAdapter expandListAdapter;
    private ExpandableListView expandableListView;
    private List<String> itemOrderList;
    private HashMap<String, List<String>> listHashMap;

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

    private void initViews(View view) {
        expandableListView = view.findViewById(R.id.ExpandListView);
        initData();
        expandListAdapter = new ExpandListAdapter(mActivity, itemOrderList, listHashMap);
        expandableListView.setAdapter(expandListAdapter);


    }

    private void initData() {
        itemOrderList = new ArrayList<>();
        listHashMap = new HashMap<>();
        itemOrderList.add("2.3");
        itemOrderList.add("2.4");
        itemOrderList.add("2.5");
        itemOrderList.add("2.6");


        List<String> list1 = new ArrayList<>();
        list1.add("Ba Ba rang muối");
        list1.add("Ba Ba rang muối");
        list1.add("Ba Ba rang muối");
        list1.add("Ba Ba rang muối");
        List<String> list2 = new ArrayList<>();
        list2.add("Ba Ba rang muối");
        list2.add("Ba Ba rang muối");
        list2.add("Ba Ba rang muối");
        list2.add("Ba Ba rang muối");
        List<String> list3 = new ArrayList<>();
        list3.add("Ba Ba rang muối");
        list3.add("Ba Ba rang muối");
        list3.add("Ba Ba rang muối");
        list3.add("Ba Ba rang muối");
        List<String> list4 = new ArrayList<>();
        list4.add("Ba Ba rang muối");
        list4.add("Ba Ba rang muối");
        list4.add("Ba Ba rang muối");
        list4.add("Ba Ba rang muối");

        listHashMap.put(itemOrderList.get(0),list1);
        listHashMap.put(itemOrderList.get(1),list2);
        listHashMap.put(itemOrderList.get(2),list3);
        listHashMap.put(itemOrderList.get(3),list4);

    }
}
