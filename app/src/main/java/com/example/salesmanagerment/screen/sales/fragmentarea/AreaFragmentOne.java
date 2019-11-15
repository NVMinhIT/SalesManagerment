package com.example.salesmanagerment.screen.sales.fragmentarea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.data.model.entity.Area;
import com.example.salesmanagerment.data.model.response.base.Table;
import com.example.salesmanagerment.screen.sales.choosetable.AdapterOrderFood;


import java.util.ArrayList;
import java.util.List;

public class AreaFragmentOne extends Fragment {
    private RecyclerView recyclerView;
    private AdapterOrderFood adapterOrderFood;
    private List<Table> tableList;
    private String title = "";
    private TextView txtDemo;

    public static AreaFragmentOne newInstance(String title){
        AreaFragmentOne fragmentOne = new AreaFragmentOne();
        fragmentOne.title = title;
        return fragmentOne;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_area_one, container, false);
        initView(view);
        return view.getRootView();
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.rv_status_table);
        txtDemo = view.findViewById(R.id.txtDemo);
        tableList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapterOrderFood = new AdapterOrderFood(getActivity(), prepareTable());
        recyclerView.setAdapter(adapterOrderFood);
        txtDemo.setText(title);
    }

    private List<Table> prepareTable() {
        List<Table> tables = new ArrayList<>();

        for (int i = 1; i < 50; i++) {
            tables.add(new Table("", "" + i, 0));
        }
        return tables;
    }
}

