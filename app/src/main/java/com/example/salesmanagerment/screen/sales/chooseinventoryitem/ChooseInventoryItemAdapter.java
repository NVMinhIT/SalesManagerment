package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.adapters.ListAdapter;
import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.data.model.entity.InventoryItem;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.utils.CommonFunc;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChooseInventoryItemAdapter extends ListAdapter<ItemOrder, IOnItemClickListener<InventoryItem>> {
    Double aDouble;
    private int size;
    private IOnItemClickListener<Integer> mOnItemClickListener;

    public void setRootList(List<ItemOrder> rootList) {
        this.rootList = rootList;
        size = rootList.size();
    }

    private List<ItemOrder> rootList;

    public void setOnItemClickListener(IOnItemClickListener<Integer> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * Là phương thức khởi tạo cho ListAdapter
     *
     * @param context là được truyền tới từ context nơi khởi tạo thể hiện của lớp
     */
    public ChooseInventoryItemAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_inventory, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        itemViewHolder.bind(mListData.get(i));
    }


    //lọc
    public void filter(String name) {
        if (CommonFunc.isNullOrEmpty(name)) {
            setListData(rootList);
        } else {
            List<ItemOrder> orderList = new ArrayList<>();
            for (ItemOrder item : rootList) {
                if (item.Name.toLowerCase().contains(name.toLowerCase())) {
                    orderList.add(item);
                }
            }
            setListData(orderList);
        }
    }

    /**
     * Tính tổng tiền hóa đơn
     */
    private void totalMoney() {
        try {
            int totalMoney = 0;
            for (int i = 0; i < mListData.size(); i++) {
                totalMoney += mListData.get(i).TotalMoney;
                Log.d("HAHA", "" + totalMoney);
            }
            mOnItemClickListener.onItemClick(totalMoney);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ItemOrder> getTotalItemSelected() {
        List<ItemOrder> itemOrders = new ArrayList<>();
        for (ItemOrder item : rootList) {
            if (item.Quantity > 0) {
                itemOrders.add(item);
            }
        }
        return itemOrders;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivICon, ivDefault;
        private TextView tvDishName, tvPrice, tvQuantity;
        private LinearLayout lnQuantity;
        private Button btnMinus, btnPlus;
        private ConstraintLayout clDishOrder;
        private ItemOrder mItemOrder;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            // mDishDataSource = DishDataSource.getInstance();
            initViews(itemView);
            initEvents();
        }

        /**
         * Phương thức gắn sự kiện cho view
         */
        private void initEvents() {
            clDishOrder.setOnClickListener(this);
            btnMinus.setOnClickListener(this);
            btnPlus.setOnClickListener(this);
            tvQuantity.setOnClickListener(this);
            ivDefault.setOnClickListener(this);
        }

        /**
         * Phương thức tham chiếu, khởi tạo view
         */
        private void initViews(View view) {
            ivICon = view.findViewById(R.id.ivIcon);
            ivDefault = view.findViewById(R.id.ivDefault);
            tvDishName = view.findViewById(R.id.tvDishName);
            tvQuantity = view.findViewById(R.id.tvQuantity);
            lnQuantity = view.findViewById(R.id.lnQuantity);
            tvPrice = view.findViewById(R.id.tvPrice);
            btnMinus = view.findViewById(R.id.btnMinus);
            btnPlus = view.findViewById(R.id.btnPlus);
            clDishOrder = view.findViewById(R.id.clDishOrder);
        }

        /**
         * Phương thức xử lý các sự kiện click cho các view được gắn sự kiện OnClick
         * *
         *
         * @param v - view xảy ra sự kiện
         */
        @Override
        public void onClick(View v) {
            //lấy số lượng món ăn từ text view
            Double quantity = Double.parseDouble(tvQuantity.getText().toString());
            switch (v.getId()) {
                case R.id.clDishOrder:
                    try {
                        if (quantity == 0) {
                            //khi click vào item, nếu hiện tại số lượng là 0 thì thay đổi background
                            clDishOrder.setBackground(mContext.getResources().getDrawable(R.drawable.selector_button_gray));
                            lnQuantity.setVisibility(View.VISIBLE);
                            ivDefault.setVisibility(View.VISIBLE);
                            // ivICon.setVisibility(View.INVISIBLE);
                        }
                        //tăng số lượng lên 1
                        tvQuantity.setText(String.valueOf(++quantity));
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.tvQuantity:
//                    try {
//                    showDialogNumber(InputNumberDialog.FLAG_QUANTITY, String.valueOf(quantity), new InputNumberDialog.DialogCallBack() {
//                        @Override
//                        public void setAmount(String amount) {
//                            try {
//                                if (TextUtils.isEmpty(amount)) {
//                                    amount = "0";
//                                }
//                                int quantity = Integer.parseInt(amount);
//                                mBillDetail.setQuantity(quantity);
//                                mBillDetail.setTotalMoney(quantity * mPrice);
//                                mListData.set(getAdapterPosition(), mBillDetail);
//                                tvQuantity.setText(amount);
//                                if (quantity == 0) {
//                                    ivICon.setVisibility(View.VISIBLE);
//                                    ivDefault.setVisibility(View.GONE);
//                                    lnQuantity.setVisibility(View.GONE);
//                                    clDishOrder.setBackground(mContext.getResources().getDrawable(R.drawable.selector_dish));
//                                }
//                                totalMoney();
//                            } catch (NumberFormatException e) {
//                                e.printStackTrace();
//                            } catch (Resources.NotFoundException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                    break;
                case R.id.ivDefault:
                    try {
                        ivDefault.setVisibility(View.GONE);
                        quantity = 0.0;
                        tvQuantity.setText(String.valueOf(0));
                        clDishOrder.setBackground(mContext.getResources().getDrawable(R.drawable.selector_dish));
                        lnQuantity.setVisibility(View.GONE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btnPlus:
                    try {
                        //tăng số lượng lên 1
                        tvQuantity.setText(String.valueOf(++quantity));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btnMinus:
                    try {
                        --quantity;
                        if (quantity == 0) {
                            //giảm số lượng nếu bằng 0 thì thay đổi background và hiện icon phủ xanh
                            clDishOrder.setBackground(mContext.getResources().getDrawable(R.drawable.selector_dish));
                            lnQuantity.setVisibility(View.GONE);
                            ivDefault.setVisibility(View.INVISIBLE);
                        } else if (quantity < 0) {
                            quantity = 0.0;
                        }
                        tvQuantity.setText(String.valueOf(quantity));
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            mItemOrder.Quantity = quantity;
            setDataToRoot(quantity);

        }

        void setDataToRoot(Double quantity) {
            ItemOrder itemOrder = rootList.get(getAdapterPosition());
            itemOrder.Quantity = quantity;
            itemOrder.TotalMoney = quantity * itemOrder.Price;
        }

        void bind(ItemOrder itemOrder) {
            mItemOrder = itemOrder;
            Double quantity = itemOrder.Quantity;
            tvDishName.setText(itemOrder.Name);
            ivICon.setImageBitmap(CommonFunc.StringToBitMap(itemOrder.Image));
            if (quantity > 0) {
                tvQuantity.setText(String.valueOf(itemOrder.Quantity));
                ivDefault.setVisibility(View.VISIBLE);
                clDishOrder.setBackground(mContext.getResources().getDrawable(R.drawable.selector_button_gray));
                lnQuantity.setVisibility(View.VISIBLE);
            } else {
                ivDefault.setVisibility(View.GONE);
                lnQuantity.setVisibility(View.GONE);
                tvQuantity.setText(R.string.price_default);
                clDishOrder.setBackground(mContext.getResources().getDrawable(R.drawable.selector_dish));
            }
            tvPrice.setText(NumberFormat.getNumberInstance(Locale.US).format(itemOrder.Price));
        }

    }
}
