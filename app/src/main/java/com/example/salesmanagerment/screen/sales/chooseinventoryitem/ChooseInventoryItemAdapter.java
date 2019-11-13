package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import android.content.Context;

import com.example.salesmanagerment.base.adapters.ListAdapter;
import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.data.model.response.base.InventoryItem;

public class ChooseInventoryItemAdapter extends ListAdapter<InventoryItem, IOnItemClickListener<InventoryItem>> {
    /**
     * Là phương thức khởi tạo cho ListAdapter
     *
     * @param context là được truyền tới từ context nơi khởi tạo thể hiện của lớp
     */
    public ChooseInventoryItemAdapter(Context context) {
        super(context);
    }


}
