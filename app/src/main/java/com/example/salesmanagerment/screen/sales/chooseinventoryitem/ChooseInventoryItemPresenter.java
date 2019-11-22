package com.example.salesmanagerment.screen.sales.chooseinventoryitem;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.InventoryItem;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.data.model.entity.Unit;
import com.example.salesmanagerment.data.repository.DataSource;
import com.example.salesmanagerment.utils.CommonFunc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

        public class ChooseInventoryItemPresenter implements IInventoryItemContact.IPresenter {

    private static final String TAG = "ChooseInventoryItemPres";
    private DataSource mDataSource;
    private IInventoryItemContact.IView mView;
    private List<Unit> mUnits;

    public OrderEntity getOrderEntity() {
        return mOrderEntity;
    }

    public OrderEntity mOrderEntity;

    public ChooseInventoryItemPresenter() {
        mDataSource = DataSource.getInstance();
        mUnits = mDataSource.getUnitList();
        if (mUnits == null) {
            mDataSource.getListUnit(new IDataCallBack<List<Unit>, String>() {
                @Override
                public void onDataSuccess(List<Unit> data) {
                    mUnits = data;
                }

                @Override
                public void onDataFailed(String error) {

                }
            });
        }
    }

    public void setOrderDetails(List<ItemOrder> itemOrders) {
        List<OrderDetail> list = new ArrayList<>();
        int sortOrder = 0;
        //String date = CommonFunc.getStringCurrentDateTime();
        for (ItemOrder item : itemOrders) {
            list.add(new OrderDetail.Builder()
                    .setOrderDetailID(UUID.randomUUID().toString())
                    .setOrderID(mOrderEntity.order.OrderID)
                    .setInventoryItemID(item.ID)
                    .setQuantity((double) item.Quantity).
                            setSortOrder(sortOrder).
                            build());
            sortOrder++;
        }
        mOrderEntity.orderDetails = list;
    }

    @Override
    public void getInventoryItem(Boolean isLoadNewData, Boolean isShowLoading) {
        mView.showLoading(false);
        if (!isLoadNewData) {
            List<InventoryItem> itemList = mDataSource.getInventoryItemList();
            if (itemList != null) {
                mView.getListSuccess(convertData(itemList));
            } else {
                getInventoryItemList(isShowLoading);
            }
            return;
        }
        getInventoryItemList(isShowLoading);
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

    public List<ItemOrder> convertData(List<InventoryItem> itemList) {
        List<ItemOrder> list = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            InventoryItem item = itemList.get(i);
            list.add(new ItemOrder(item.getInventoryItemID(), 0, item.getInventoryItemName(), item.getFileResource(), item.getUnitPrice(), getUnitName(item.getUnitID()), null));
        }
        return list;
    }

    private String getUnitName(String unitID) {
        String unitName = "";
        for (Unit item : mUnits) {
            if (item.UnitID.equals(unitID)) {
                unitName = item.UnitName;
                break;
            }
        }
        return unitName;
    }

//    private void getListUnit(final Boolean isShowLoading) {
//        mDataSource.getListUnit(new IDataCallBack<List<Unit>, String>() {
//            @Override
//            public void onDataSuccess(List<Unit> data) {
//                if (data != null) {
//                    mUnits = mDataSource.getUnitList();
//                    getInventoryItemList(isShowLoading);
//                } else {
//                    CommonFunc.showToastWarning(R.string.somthing_went_wrong);
//                }
//            }
//
//            @Override
//            public void onDataFailed(String error) {
//                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
//            }
//        });
//    }

//    private void getListInventoryItem(Boolean isShowLoading) {
//     //   List<Unit> unit = mDataSource.getUnitList();
////        if (unit == null) {
////            getListUnit(isShowLoading);
////        } else {
//            mUnits = mDataSource.getUnitList();
//            getInventoryItemList(isShowLoading);
////        }

    //   }

    private void getInventoryItemList(Boolean isLoading) {
        mView.showLoading(isLoading);
        mDataSource.getListInventoryItem(new IDataCallBack<List<InventoryItem>, String>() {
            @Override
            public void onDataSuccess(List<InventoryItem> data) {
                if (data != null) {
                    mView.getListSuccess(convertData(data));
                } else {
                    CommonFunc.showToastWarning(R.string.somthing_went_wrong);
                }
                mView.showLoading(false);
            }

            @Override
            public void onDataFailed(String error) {
                mView.showLoading(false);
                CommonFunc.showToastWarning(error);
            }
        });
    }

    @Override
    public void setView(IInventoryItemContact.IView view) {
        mView = view;
    }

    @Override
    public void onStart() {
        getInventoryItem(true, true);
    }

    @Override
    public void onStop() {

    }

    public ChooseInventoryItemPresenter setOrderEntity(OrderEntity orderEntity) {
        mOrderEntity = orderEntity;
        return this;
    }
}
