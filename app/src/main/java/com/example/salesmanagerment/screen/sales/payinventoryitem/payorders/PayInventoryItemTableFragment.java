package com.example.salesmanagerment.screen.sales.payinventoryitem.payorders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.salesmanagerment.R;

public class PayInventoryItemTableFragment extends Fragment {
    public static PayInventoryItemTableFragment newInstance() {
        return new PayInventoryItemTableFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_inventoryitem_table, container, false);
        return view.getRootView();
    }
}
