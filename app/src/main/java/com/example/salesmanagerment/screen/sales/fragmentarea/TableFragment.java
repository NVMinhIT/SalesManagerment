package com.example.salesmanagerment.screen.sales.fragmentarea;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;
import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;

import java.util.ArrayList;
import java.util.List;

public class TableFragment extends BaseFragment implements ITableContract.IView, IOnItemClickListener<TableMappingCustom> {
    private RecyclerView recyclerView;
    private TableAdapter mAdapter;
    private String areaID;
    private TablePresenter mPresenter;
    private List<TableMappingCustom> mCustomList;
    public static final String ACTION_NAME_TABLE = "ACTION_NAME_TABLE";
    public static final String EXTRA_NAME_TABLE = "EXTRA_NAME_TABLE";
    String name;

    public static TableFragment newInstance(String areaID) {
        TableFragment fragment = new TableFragment();
        fragment.areaID = areaID;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        initView(view);
        return view.getRootView();
    }


    private void initView(View view) {
        mCustomList = new ArrayList<>();
        mPresenter = new TablePresenter();
        mPresenter.setView(this);
        mPresenter.getTableByAreaID(areaID);
        recyclerView = view.findViewById(R.id.rv_status_table);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mAdapter = new TableAdapter(mActivity, mCustomList, areaID);
        mAdapter.setOnClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getTableSuccess(List<TableMappingCustom> tableMappingCustomList) {
        //khởi gán dữ liệu cho adapter
        mAdapter.setListData(tableMappingCustomList);
    }

    @Override
    public void showLoading(boolean isShowLoading) {
        showDialog(isShowLoading);
    }

    @Override
    public void onItemClick(TableMappingCustom data) {
        Intent intent = new Intent(ACTION_NAME_TABLE);
        intent.putExtra(EXTRA_NAME_TABLE, data);
        LocalBroadcastManager.getInstance(mActivity).sendBroadcast(intent);
    }
}

