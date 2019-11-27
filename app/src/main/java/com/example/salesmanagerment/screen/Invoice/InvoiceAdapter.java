package com.example.salesmanagerment.screen.Invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.adapters.ListAdapter;
import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.data.model.entity.Invoice;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderEntity;

import java.text.NumberFormat;
import java.util.Locale;

public class InvoiceAdapter extends ListAdapter<ItemOrder, IOnItemClickListener<Invoice>> {
    /**
     * Là phương thức khởi tạo cho ListAdapter
     *
     * @param context là được truyền tới từ context nơi khởi tạo thể hiện của lớp
     */
    public InvoiceAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.bind(mListData.get(i));


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_invoice, viewGroup, false);
        return new MyViewHolder(view);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvInventoryItemName, tvQuantity, tvPrice, tvTotalMoney;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            tvInventoryItemName = itemView.findViewById(R.id.tvInventoryItemName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTotalMoney = itemView.findViewById(R.id.tvTotalMoney);
        }


        public void bind(ItemOrder itemOrder) {
            tvInventoryItemName.setText(itemOrder.Name);
            Double quality = itemOrder.Quantity;
            tvQuantity.setText(String.valueOf(quality).replace(".0",""));
            tvPrice.setText(NumberFormat.getNumberInstance(Locale.US).format(itemOrder.Price));
            tvTotalMoney.setText(NumberFormat.getNumberInstance(Locale.US).format((itemOrder.TotalMoney)));

        }
    }


}
