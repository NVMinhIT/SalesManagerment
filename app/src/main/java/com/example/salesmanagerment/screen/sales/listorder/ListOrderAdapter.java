package com.example.salesmanagerment.screen.sales.listorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.adapters.ListAdapter;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.utils.Constants;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ListOrderAdapter extends ListAdapter<OrderResponse, OrderResponse> {

    private IOrderRequest mOrderCallBack;
    private List<OrderDetail> rootList;


    private int size;

    public ListOrderAdapter setOrderCallBack(IOrderRequest orderCallBack) {
        mOrderCallBack = orderCallBack;
        return this;
    }

    /**
     * Là phương thức khởi tạo cho ListAdapter
     *
     * @param context là được truyền tới từ context nơi khởi tạo thể hiện của lớp
     */
    public ListOrderAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        final OrderResponse orderResponse = mListData.get(i);
        myViewHolder.bind(orderResponse);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClickListener.onItemClick(orderResponse);
            }
        });
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order, viewGroup, false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvPerson, tvAreaName, tvTotalAmount, tvTableName, tvOrderNo;
        RelativeLayout rlStatus;
        LinearLayout lnPayDish, lnCancel, lnPreview, lnPay;
        OrderResponse mOrderResponse;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
            initEvent();
        }

        private void initEvent() {
            lnPayDish.setOnClickListener(this);
            lnPreview.setOnClickListener(this);
            lnCancel.setOnClickListener(this);
            lnPay.setOnClickListener(this);
        }

        private void initView(View itemView) {
            tvPerson = itemView.findViewById(R.id.tvPerson);
            tvAreaName = itemView.findViewById(R.id.tvAreaName);
            tvTotalAmount = itemView.findViewById(R.id.tvTotalAmount);
            tvTableName = itemView.findViewById(R.id.tvTableName);
            tvOrderNo = itemView.findViewById(R.id.tvOrderNo);
            rlStatus = itemView.findViewById(R.id.rlStatus);
            lnPayDish = itemView.findViewById(R.id.lnPayDish);
            lnCancel = itemView.findViewById(R.id.lnCancel);
            lnPreview = itemView.findViewById(R.id.lnPreview);
            lnPay = itemView.findViewById(R.id.lnPay);
        }

        public void bind(OrderResponse orderResponse) {
            mOrderResponse = orderResponse;
            tvPerson.setText(String.valueOf(orderResponse.NumberOfPeople));
            tvAreaName.setText(String.valueOf(orderResponse.AreaName));
            tvTotalAmount.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format((orderResponse.TotalAmount))));
            tvTableName.setText(String.valueOf(orderResponse.TableName));
            tvOrderNo.setText(String.valueOf(orderResponse.OrderNo));
            if (orderResponse.OrderStatus == Constants.ORDER_SERVING) {
                rlStatus.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
                lnPay.setVisibility(View.VISIBLE);
                lnPayDish.setVisibility(View.VISIBLE);
                lnCancel.setVisibility(View.VISIBLE);
            } else {
                lnPay.setVisibility(View.GONE);
                lnPayDish.setVisibility(View.GONE);
                lnCancel.setVisibility(View.GONE);
                rlStatus.setBackgroundColor(mContext.getResources().getColor(R.color.color_orange_active));
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.lnPayDish:
                    mOrderCallBack.onReceiveKitchen(mOrderResponse);
                    break;
                case R.id.lnCancel:
                    //show dialog confirm
                    mOrderCallBack.onCancelOrder(mOrderResponse.OrderID);
                    break;
                case R.id.lnPreview:
                    //hiển thị phiếu tạm tính
                    break;
                case R.id.lnPay:
                    mOrderCallBack.onRequestPay(mOrderResponse.OrderID);
                    //gửi yêu cầu thanh toán cho thu ngân
                    break;
                default:
                    break;
            }
        }
    }


    public interface IOrderRequest {
        void onRequestPay(String orderID);

        void onCancelOrder(String orderID);

        void onReceiveKitchen(OrderResponse orderResponse);

        void onPreview(String orderID, OrderEntity orderEntity, List<ItemOrder> itemOrder);
    }
}
