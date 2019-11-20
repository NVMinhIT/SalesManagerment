package com.example.salesmanagerment.screen.sales.customer.choosecustomer;

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
import com.example.salesmanagerment.data.model.entity.Customer;

public class ListCustomerAdapter extends ListAdapter<Customer, Customer> {
    private int mLastSelectedPosition = 1000;
    public String idCustomer;

    /**
     * Là phương thức khởi tạo cho ListAdapter
     *
     * @param context là được truyền tới từ context nơi khởi tạo thể hiện của lớp
     */

    public ListCustomerAdapter(Context context) {
        super(context);

    }

    private void setSelectedIndex() {
    }

    String getCustomerId() {
        return mListData.get(mLastSelectedPosition).getCustomerID();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_customer, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        if (i == mLastSelectedPosition) {
            itemViewHolder.btnTick.setImageResource(R.drawable.ic_tick);
        } else {
            itemViewHolder.btnTick.setImageResource(0);
        }
        itemViewHolder.bind(mListData.get(i));

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameCustomer, tvNumberPhone;
        ImageButton btnTick;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);

        }

        private void initView(View itemView) {
            btnTick = itemView.findViewById(R.id.btnTick);
            tvNameCustomer = itemView.findViewById(R.id.tvNameCustomer);
            tvNumberPhone = itemView.findViewById(R.id.tvNumberPhone);
        }

        public void bind(Customer customer) {
            if (customer != null) {

                tvNameCustomer.setText(customer.getCustomerName());
                tvNumberPhone.setText(customer.getMobile());

                this.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int newPosition = mLastSelectedPosition;
                        mLastSelectedPosition = getAdapterPosition();
                        notifyItemChanged(newPosition);
                        notifyItemChanged(mLastSelectedPosition);
                    }
                });
            }

        }
    }
}
