package com.example.salesmanagerment.screen.sales.listorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;
import com.example.salesmanagerment.screen.sales.chooseinventoryitem.ChooseInventoryItemActivity;
import com.example.salesmanagerment.utils.Navigator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListOrderFragment extends BaseFragment implements View.OnClickListener {
    private Navigator mNavigator;
    private FloatingActionButton btnAddOrder;
    private TextView tvAddOrder;
    private RecyclerView rvOrder;
    private TextView textViewOptionSearch;

    public static ListOrderFragment newInstance() {
        return new ListOrderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, container, false);
        initView(view);
        initEvents();
        baseInit();
        mNavigator = mActivity.getNavigator();
        return view.getRootView();
    }

    private void initEvents() {
        btnAddOrder.setOnClickListener(this);
        tvAddOrder.setOnClickListener(this);
        textViewOptionSearch.setOnClickListener(this);
    }

    private void initView(View view) {
        textViewOptionSearch = view.findViewById(R.id.tv_OptionSearch);
        btnAddOrder = view.findViewById(R.id.btnAddOrder);
        tvAddOrder = view.findViewById(R.id.tvAddOrder);
        rvOrder = view.findViewById(R.id.rvOrder);

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
}
