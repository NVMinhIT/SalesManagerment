package com.example.salesmanagerment.screen.sales.payinventoryitem.payorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.adapters.ListAdapter;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.utils.Constants;

public class PayOrderInventoryItemAdapter extends ListAdapter<ItemOrder, ItemOrder> {

    private ISendCallBack mISendCallBack;

    public PayOrderInventoryItemAdapter setISendCallBack(ISendCallBack ISendCallBack) {
        mISendCallBack = ISendCallBack;
        return this;
    }

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
        private TextView tvNameInventoryItem, tvAccountUnit, txtProcessing;
        private ImageButton imb_pay_dish;
        private ItemOrder mItemOrder;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            tvNameInventoryItem = itemView.findViewById(R.id.tvNameInventoryItem);
            tvAccountUnit = itemView.findViewById(R.id.tvAccountUnit);
            imb_pay_dish = itemView.findViewById(R.id.imb_pay_dish);
            txtProcessing = itemView.findViewById(R.id.txtProcessing);
            imb_pay_dish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mISendCallBack.sendCallBack(mItemOrder, getAdapterPosition());
                }
            });
        }

        @SuppressLint("SetTextI18n")
        public void bind(ItemOrder itemOrder) {
            mItemOrder = itemOrder;
            Double quantity = itemOrder.Quantity;
            tvNameInventoryItem.setText(itemOrder.Name);
            tvAccountUnit.setText(String.valueOf(quantity).replace(".0", "") + " " + itemOrder.UnitName);
            if (itemOrder.OrderDetailStatus == Constants.ORDER_DETAIL_PROCESSED) {
                imb_pay_dish.setVisibility(View.VISIBLE);
                txtProcessing.setVisibility(View.GONE);
            } else {
                imb_pay_dish.setVisibility(View.GONE);
                txtProcessing.setVisibility(View.VISIBLE);
            }

        }
    }


    public interface ISendCallBack {
        void sendCallBack(ItemOrder itemOrder, int pos);
    }
}

