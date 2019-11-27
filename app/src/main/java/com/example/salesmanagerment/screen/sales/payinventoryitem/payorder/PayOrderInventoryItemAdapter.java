package com.example.salesmanagerment.screen.sales.payinventoryitem.payorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.adapters.ListAdapter;
import com.example.salesmanagerment.data.model.entity.ItemOrder;

public class PayOrderInventoryItemAdapter extends ListAdapter<ItemOrder, ItemOrder> {
    /**
     * Là phương thức khởi tạo cho ListAdapter
     *
     * @param context là được truyền tới từ context nơi khởi tạo thể hiện của lớp
     */
    public PayOrderInventoryItemAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inventoryitem_order, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        itemViewHolder.bind(mListData.get(i));
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameInventoryItem, tvAccountUnit;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            tvNameInventoryItem = itemView.findViewById(R.id.tvNameInventoryItem);
            tvAccountUnit = itemView.findViewById(R.id.tvAccountUnit);
        }

        @SuppressLint("SetTextI18n")
        public void bind(ItemOrder itemOrder) {
            Double quantity = itemOrder.Quantity;
            tvNameInventoryItem.setText(itemOrder.Name);
            tvAccountUnit.setText(quantity + " " + itemOrder.UnitName + " x ");

        }
    }


}

