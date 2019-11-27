package com.example.salesmanagerment.screen.sales.createorder;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.app.ErrorCode;
import com.example.salesmanagerment.data.model.entity.InventoryItemMapping;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateOrderPresenter implements ICreateOrderContact.IPresenter {

    private ICreateOrderContact.IView mView;
    private DataSource mDataSource;
    public Double TotalMoney = 0.0;
    public OrderEntity mOrderEntity;
    public List<ItemOrder> mItemOrders;
    public List<OrderDetail> lsOldOrderDetail;

    public CreateOrderPresenter setOrderEntity(OrderEntity orderEntity) {
        mOrderEntity = orderEntity;
        return this;
    }

    public CreateOrderPresenter() {
        mItemOrders = new ArrayList<>();
        mDataSource = DataSource.getInstance();
        TotalMoney = calculateMoney(mItemOrders);
    }

    @Override
    public void saveOrder(final int type, final Boolean isClose) {
        mView.showLoading(true);
        if (type == Constants.TYPE_ADD) {
            mOrderEntity.orderDetails = CommonFunc.newItemOrderToOrderDetails(mItemOrders, mOrderEntity.order);
            saveOrder(isClose);
        } else {
            //so sánh list ban đầu và list sau khi thay đổi, nếu trong list cũ k có trong list mới thì cập nhật -> hủy
            List<String> deleteList = new ArrayList<>();
            int oldSize = lsOldOrderDetail.size();
            int newSize = mItemOrders.size();
            for (int i = 0; i < oldSize; i++) {
                boolean isExist = false;
                for (int j = 0; j < newSize; j++) {
                    //nếu còn trong danh sách mới cập nhật thì k làm gì
                    if (lsOldOrderDetail.get(i).OrderDetailID.equalsIgnoreCase(mItemOrders.get(j).OrderDetailID)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) { //nếu k còn trong ds mới nữa -> add vào danh sách phải hủy
                    deleteList.add(lsOldOrderDetail.get(i).InventoryItemID);
                }
            }

            if (deleteList.size() == 0) {//gán các item mới và item cũ ntn?
                CommonFunc.oldItemOrderToOrderDetails(mItemOrders, mOrderEntity.order, new IDataCallBack<List<OrderDetail>, Void>() {
                    @Override
                    public void onDataSuccess(List<OrderDetail> data) {
                        mOrderEntity.orderDetails = data;
                        saveOrder(isClose);
                    }

                    @Override
                    public void onDataFailed(Void error) {
                        mView.showLoading(false);
                    }
                });

            } else {
                //hủy đi các món đã bị huy -> cập nhật order
                CommonFunc.showToastError("Có item bị xóa");
            }


        }
    }

    @Override
    public void getOrderNo() {
        mView.showLoading(true);
        mDataSource.getOrderNo(new IDataCallBack<String, String>() {
            @Override
            public void onDataSuccess(String data) {
                mView.showLoading(false);
                mView.getOrderNoSuccess(data);
            }

            @Override
            public void onDataFailed(String error) {
                mView.showLoading(false);
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    @Override
    public void addItemOrders(List<ItemOrder> itemOrders) {
        mItemOrders.addAll(itemOrders);
        TotalMoney += calculateMoney(itemOrders);
    }

    @Override
    public void getOrderDetailsByOrderID(String orderID) {
        mView.showLoading(true);
        mDataSource.GetOrderDetailsByOrderID(orderID, new IDataCallBack<List<OrderDetail>, String>() {
            @Override
            public void onDataSuccess(List<OrderDetail> data) {
                mView.showLoading(true);
                if (data != null) {
                    lsOldOrderDetail = data;
                    mView.getListOrderDetailSuccess(data);
                } else {
                    CommonFunc.showToastError(R.string.somthing_went_wrong);
                }
            }

            @Override
            public void onDataFailed(String error) {
                mView.showLoading(true);
                CommonFunc.showToastError(R.string.somthing_went_wrong);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void setItemOrders(final List<OrderDetail> orderDetails) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                mItemOrders.clear();
                for (OrderDetail item : orderDetails) {
                    InventoryItemMapping itemMapping = mDataSource.getInventoryItemMapping(item.InventoryItemID);
                    mItemOrders.add(new ItemOrder.Builder()
                            .setID(item.InventoryItemID)
                            .setQuantity(item.Quantity)
                            .setOrderDetailID(item.OrderDetailID)
                            .setCookedQuantity(item.CookedQuantity)
                            .setServedQuantity(item.ServedQuantity)
                            .setCookingQuantity(item.CookingQuantity)
                            .setOrderDetailStatus(item.OrderDetailStatus)
                            .setCancelEmployeeID(item.CancelEmployeeID)
                            .setName(itemMapping.InventoryName)
                            .setPrice(itemMapping.UnitPrice)
                            .setUnitName(itemMapping.UnitName)
                            .setTotalMoney(itemMapping.UnitPrice * item.Quantity)
                            .setDescription(item.Description)
                            .build());
                }
                TotalMoney = calculateMoney(mItemOrders);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mView.setItemOderByOrderDetailSuccess();
            }
        }.execute();
    }

    @Override
    public void sendKitchen(final int type) {
        if (mItemOrders.size() == 0) {
            CommonFunc.showToastInfo("Bạn chưa chọn món ăn nào");
        } else {
            for (ItemOrder item : mItemOrders) {
                if (item.OrderDetailStatus == Constants.ORDER_DETAIL_NOTHING) {
                    item.OrderDetailStatus = Constants.ORDER_DETAIL_SENT_KITCHEN;
                }
            }
            saveOrder(type, false);
        }
    }

    private Double calculateMoney(List<ItemOrder> itemOrders) {
        Double sum = 0.0;
        for (ItemOrder item : itemOrders) {
            sum += item.TotalMoney;
        }
        return sum;
    }

    private void saveOrder(final Boolean isClose) {

        mDataSource.createOrder(mOrderEntity, new IDataCallBack<Boolean, String>() {
            @Override
            public void onDataSuccess(Boolean data) {
                mView.showLoading(false);
                if (data) {
                    if (isClose) {
                        mView.gotoOrdersScreen();
                    } else {
                        getOrderDetailsByOrderID(mOrderEntity.order.OrderID);
                        CommonFunc.showToastSuccess("Đã gửi bếp");
                    }

                } else {
                    CommonFunc.showToastSuccess("Thất bại, vui lòng thử lại!");
                }
            }

            @Override
            public void onDataFailed(String error) {
                mView.showLoading(false);
                if (error.equals(ErrorCode.DUPLICATE)) {
                    CommonFunc.showToastInfo("Trùng mã order, đang khởi tạo lại");
                    String[] temp = mOrderEntity.order.OrderNo.split(Pattern.quote("."));
                    int no = Integer.parseInt(temp[1]) + 1;
                    mOrderEntity.order.OrderNo = temp[0] + "." + no;
                    saveOrder(true);
                } else {
                    CommonFunc.showToastError(error);
                }

            }
        });
    }


    @Override
    public void setView(ICreateOrderContact.IView view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
