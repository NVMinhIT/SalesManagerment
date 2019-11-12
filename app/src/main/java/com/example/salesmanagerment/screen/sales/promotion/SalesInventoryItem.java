package com.example.salesmanagerment.screen.sales.promotion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.salesmanagerment.R;

import java.util.Objects;

public class SalesInventoryItem extends Fragment {
    private ImageButton btBackSale;

    public static SalesInventoryItem newInstance() {
        return new SalesInventoryItem();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sales_inventoryitem, container, false);
        initView(v);
        return v.getRootView();
    }

    private void initView(View v) {
        btBackSale = v.findViewById(R.id.bt_back_sale);
        btBackSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });
    }
}
