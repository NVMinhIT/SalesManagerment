package com.example.salesmanagerment.screen.sales.createorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.adapters.ListAdapter;
import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.utils.Constants;

import java.text.NumberFormat;
import java.util.Locale;

public class CreateOrderAdapter extends ListAdapter<ItemOrder, IOnItemClickListener<ItemOrder>> {
    public CreateOrderAdapter setItemInventoryCallBack(ItemInventoryCallBack itemInventoryCallBack) {
        mItemInventoryCallBack = itemInventoryCallBack;
        return this;
    }

    private ItemInventoryCallBack mItemInventoryCallBack;

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
        private TextView tvName, tvUnitQuantity, tvPrice, tvAmount, txtCookingQuantity, txtCookedQuantity, txtServedQuantity;
        private TextView tvSumMoney, txtSendKitchen;
        private ImageView btnDesc;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        /**
         * Phương thức tham chiếu, khởi tạo view
         */
        private void initViews(View view) {
            tvName = view.findViewById(R.id.tvName);
            btnDesc = view.findViewById(R.id.btnDesc);
            tvUnitQuantity = view.findViewById(R.id.tvUnitQuantity);
            tvPrice = view.findViewById(R.id.tvPrice);
            tvAmount = view.findViewById(R.id.tvAmount);
            txtCookingQuantity = view.findViewById(R.id.txtCookingQuantity);
            txtCookedQuantity = view.findViewById(R.id.txtCookedQuantity);
            txtServedQuantity = view.findViewById(R.id.txtServedQuantity);
            txtSendKitchen = view.findViewById(R.id.txtSendKitchen);
            btnDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemInventoryCallBack.onClickDesc(getAdapterPosition());
                }
            });
        }

        @SuppressLint("SetTextI18n")
        void bind(ItemOrder itemOrder) {
            Double quantity = itemOrder.Quantity;
            tvName.setText(itemOrder.Name);
            tvPrice.setText(NumberFormat.getNumberInstance(Locale.US).format(itemOrder.Price));
            tvUnitQuantity.setText(String.valueOf(quantity).replace(".0", "") + " " + itemOrder.UnitName + " x ");
            tvAmount.setText("= " + NumberFormat.getNumberInstance(Locale.US).format((itemOrder.TotalMoney)));
            if (itemOrder.CookingQuantity != null) {
                txtCookingQuantity.setText(itemOrder.CookingQuantity.toString().replace(".0", ""));
                txtCookedQuantity.setText(itemOrder.CookedQuantity.toString().replace(".0", ""));
                txtServedQuantity.setText(itemOrder.ServedQuantity.toString().replace(".0", ""));
            }
            if (itemOrder.OrderDetailStatus == Constants.ORDER_DETAIL_NOTHING) {
                txtSendKitchen.setText("Chưa gửi bếp");
                txtSendKitchen.setTextColor(ContextCompat.getColor(mContext, R.color.color_red));
            } else {
                txtSendKitchen.setText("Đã gửi bếp");
                txtSendKitchen.setTextColor(ContextCompat.getColor(mContext, R.color.color_green));
            }
        }
    }

    public interface ItemInventoryCallBack {
        void onClickDesc(int position);
    }
}
