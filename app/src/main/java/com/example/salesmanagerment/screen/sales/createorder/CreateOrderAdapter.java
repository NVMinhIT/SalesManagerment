package com.example.salesmanagerment.screen.sales.createorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.adapters.ListAdapter;
import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.data.model.entity.ItemOrder;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CreateOrderAdapter extends ListAdapter<ItemOrder, IOnItemClickListener<ItemOrder>> {
    /**
     * Là phương thức khởi tạo cho ListAdapter
     *
     * @param context là được truyền tới từ context nơi khởi tạo thể hiện của lớp
     */
    public CreateOrderAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_inventorydetail, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        itemViewHolder.bind(mListData.get(i));
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvUnitQuantity, tvPrice, tvAmount;
        private TextView tvSumMoney;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        /**
         * Phương thức tham chiếu, khởi tạo view
         * Created_by Nguyễn Bá Linh on 12/04/2019
         */
        private void initViews(View view) {
            tvName = view.findViewById(R.id.tvName);
            tvUnitQuantity = view.findViewById(R.id.tvUnitQuantity);
            tvPrice = view.findViewById(R.id.tvPrice);
            tvAmount = view.findViewById(R.id.tvAmount);
        }

        @SuppressLint("SetTextI18n")
        void bind(ItemOrder itemOrder) {
            int quantity = itemOrder.Quantity;
            tvName.setText(itemOrder.Name);
            tvPrice.setText(NumberFormat.getNumberInstance(Locale.US).format(itemOrder.Price));
            tvUnitQuantity.setText(quantity + " " + itemOrder.UnitName + " x ");
            tvAmount.setText("= " + NumberFormat.getNumberInstance(Locale.US).format((itemOrder.TotalMoney)));
        }
    }
}
