package com.example.salesmanagerment.screen.sales.createorder;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.app.ErrorCode;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CacheManager;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateOrderPresenter implements ICreateOrderContact.IPresenter {

    private ICreateOrderContact.IView mView;
    private DataSource mDataSource;
    public OrderEntity mOrderEntity;
    public List<ItemOrder> mItemOrders;

    public CreateOrderPresenter() {
        mDataSource = DataSource.getInstance();
    }

    @Override
    public void saveOrder(final int type) {
        mView.showLoading(true);
        if(type == Constants.TYPE_ADD) {
            saveOrder();
        } else {
            List<OrderDetail> list = CacheManager.cacheManager.getOldOrderDetail();
            List<String> deleteList = new ArrayList<>();
            int size = list.size();
            int sizeItemorder = mItemOrders.size();
            for (int i = 0; i < size; i++) {
                boolean isExist = false;
                for (int j = 0; j < sizeItemorder; j++) {
                    //nếu còn trong danh sách mới cập nhật thì k làm gì
                    if(list.get(i).InventoryItemID.equalsIgnoreCase(mItemOrders.get(j).ID)) {
                        isExist = true;
                        break;
                    }
                }
                if(!isExist) { //nếu k còn trong ds mới nữa -> add vào danh sách phải xóa đi/hủy
                    deleteList.add(list.get(i).InventoryItemID);
                }
            }

            if(deleteList.size() == 0) {
                saveOrder();
            } else {
                //hủy đi các món đã bị huy -> cập nhật order
            }


        }









    }

    private void saveOrder(){
        mDataSource.createOrder(mOrderEntity, new IDataCallBack<Boolean, String>() {
            @Override
            public void onDataSuccess(Boolean data) {
                mView.showLoading(false);
                if (data) {
                    mView.gotoOrdersScreen();
                    CommonFunc.showToastSuccess("Thành công");
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
                    saveOrder();
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
