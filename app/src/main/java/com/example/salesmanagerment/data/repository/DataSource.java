package com.example.salesmanagerment.data.repository;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.api.ApiService;
import com.example.salesmanagerment.data.api.ServiceGenerator;
import com.example.salesmanagerment.data.model.entity.Area;
import com.example.salesmanagerment.data.model.entity.Customer;
import com.example.salesmanagerment.data.model.entity.InventoryItem;
import com.example.salesmanagerment.data.model.entity.InventoryItemMapping;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;
import com.example.salesmanagerment.data.model.entity.Unit;
import com.example.salesmanagerment.data.model.entity.UserProfile;
import com.example.salesmanagerment.data.model.request.CancelOrderRequest;
import com.example.salesmanagerment.data.model.request.ChangePassRequest;
import com.example.salesmanagerment.data.model.request.LoginRequest;
import com.example.salesmanagerment.data.model.response.base.BaseResponse;
import com.example.salesmanagerment.screen.main.IInitDataCallback;
import com.example.salesmanagerment.utils.CacheManager;
import com.example.salesmanagerment.utils.CommonFunc;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSource {
    private static final String TAG = "SalesRepository";
    private static DataSource instance;
    private String token;
    private HashMap<String, InventoryItemMapping> mItemMappingHashMap;

    public List<Area> getAreaList() {
        return mAreaList;
    }

    private List<Area> mAreaList;

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    private ApiService apiService;

    @SuppressLint("StaticFieldLeak")
    public void MappingInventoryItem(final List<Unit> units, final List<InventoryItem> inventoryItems, final IInitDataCallback callback) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                InventoryItemMapping inventoryItemMapping;
                for (InventoryItem inventoryItem : inventoryItems) {
                    String inventoryItemID = inventoryItem.getInventoryItemID();
                    String unitName = "";
                    for (Unit unit : units) {
                        if (unit.UnitID.equalsIgnoreCase(inventoryItem.getUnitID())) {
                            unitName = unit.UnitName;
                            break;
                        }
                    }
                    inventoryItemMapping = new InventoryItemMapping.Builder()
                            .setInventoryItemID(inventoryItemID)
                            .setInventoryName(inventoryItem.getInventoryItemName())
                            .setUnitName(unitName)
                            .setUnitPrice(inventoryItem.getUnitPrice())
                            .build();
                    mItemMappingHashMap.put(inventoryItemID, inventoryItemMapping);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                callback.success();
            }
        }.execute();
    }

    public String getUnitName(String inventoryItemID) {
        String unitName = "";
        InventoryItemMapping item = mItemMappingHashMap.get(inventoryItemID);
        if (item != null) {
            unitName = item.UnitName;
        }
        return unitName;
    }

    public InventoryItemMapping getInventoryItemMapping(String inventoryItemID) {
        InventoryItemMapping item = mItemMappingHashMap.get(inventoryItemID);
        return item;
    }

    private DataSource() {
        apiService = ServiceGenerator.createService(ApiService.class);
        token = CacheManager.cacheManager.getToken();
        mItemMappingHashMap = new HashMap<>();

    }


    // đăng nhập
    public void login(LoginRequest loginRequest, final IDataCallBack<String, String> callBack) {
        apiService.login(loginRequest).enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<String>> call, @NotNull Response<BaseResponse<String>> response) {
                if (response.isSuccessful()) {
                    String tokens = response.body() != null ? response.body().getData() : null;
                    CacheManager.cacheManager.cacheToken(tokens);
                    if (callBack != null) {
                        callBack.onDataSuccess(tokens);
                    }


                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<String>> call, @NotNull Throwable t) {

                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.login_error);
            }
        });
    }


    //lấy danh sách món ăn
    public void getListInventoryItem(final IDataCallBack<List<InventoryItem>, String> callBack) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        apiService.getListInventoryItem(token).enqueue(new Callback<BaseResponse<List<InventoryItem>>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<List<InventoryItem>>> call, @NotNull Response<BaseResponse<List<InventoryItem>>> response) {
                if (response.isSuccessful()) {
                    List<InventoryItem> data = response.body().getData();
                    if (data != null) {
                        CacheManager.cacheManager.cacheInventoryItems(data);
                    }
                    if (callBack != null) {
                        callBack.onDataSuccess(data);
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<List<InventoryItem>>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    //lấy danh sách đơn vị
    public void getListUnit(final IDataCallBack<List<Unit>, String> callBack) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        apiService.getListUnit(token).enqueue(new Callback<BaseResponse<List<Unit>>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<List<Unit>>> call, @NotNull Response<BaseResponse<List<Unit>>> response) {
                if (response.isSuccessful()) {
                    List<Unit> data = response.body().getData();
                    CacheManager.cacheManager.cacheUnits(data);
                    if (callBack != null) {
                        callBack.onDataSuccess(data);
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<List<Unit>>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    //lấy danh sách khu vực
    public void getListArea(final IDataCallBack<List<Area>, String> callBack) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        apiService.getListArea(token).enqueue(new Callback<BaseResponse<List<Area>>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<List<Area>>> call, @NotNull Response<BaseResponse<List<Area>>> response) {
                if (response.isSuccessful()) {
                    List<Area> data = response.body().getData();
                    mAreaList = data;
                    if (callBack != null) {
                        callBack.onDataSuccess(data);
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<List<Area>>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    //lấy danh sách bàn theo khu vực
    public void getListTableByAreaID(String areaID, final IDataCallBack<List<TableMappingCustom>, String> callBack) {
        apiService.getListTableByAreaID(token, areaID, CommonFunc.getStringCurrentDateTime(new Date())).enqueue(new Callback<BaseResponse<List<TableMappingCustom>>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<List<TableMappingCustom>>> call, @NotNull Response<BaseResponse<List<TableMappingCustom>>> response) {
                if (response.isSuccessful()) {
                    List<TableMappingCustom> data = response.body().getData();
                    Log.d(TAG, "onResponse: ");
                    if (callBack != null) {
                        callBack.onDataSuccess(data);
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<List<TableMappingCustom>>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    public void init(final IInitDataCallback callback) {
        final List<Unit> units = new ArrayList<>();
        final List<InventoryItem> inventoryItems = new ArrayList<>();
        getListArea(new IDataCallBack<List<Area>, String>() {
            @Override
            public void onDataSuccess(List<Area> data) {
                getListUnit(new IDataCallBack<List<Unit>, String>() {
                    @Override
                    public void onDataSuccess(List<Unit> data) {
                        units.addAll(data);
                        CacheManager.cacheManager.cacheUnits(data);
                        getListInventoryItem(new IDataCallBack<List<InventoryItem>, String>() {
                            @Override
                            public void onDataSuccess(List<InventoryItem> data) {
                                inventoryItems.addAll(data);
                                CacheManager.cacheManager.cacheInventoryItems(data);
                                MappingInventoryItem(units, inventoryItems, callback);
                            }

                            @Override
                            public void onDataFailed(String error) {
                                callback.failed();
                            }
                        });
                    }

                    @Override
                    public void onDataFailed(String error) {
                        callback.failed();
                    }
                });
            }

            @Override
            public void onDataFailed(String error) {
                callback.failed();
            }
        });
    }


    //lưu order
    public void createOrder(OrderEntity orderEntity, final IDataCallBack<Boolean, String> callBack) {
        Log.d(TAG, "createOrder: \n" + new Gson().toJson(orderEntity));
        apiService.createOrder(token, orderEntity).enqueue(new Callback<BaseResponse<Boolean>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<Boolean>> call, @NotNull Response<BaseResponse<Boolean>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: ");
                    if (callBack != null) {
                        if (response.body() != null) {
                            String errCode = response.body().getErrorCode();
                            if (CommonFunc.isNullOrEmpty(errCode)) {
                                callBack.onDataSuccess(true);
                            } else {
                                callBack.onDataFailed(errCode);
                            }
                        } else {
                            callBack.onDataSuccess(false);
                        }
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<Boolean>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    //lấy danh sách khu vực

    // lấy thông tin user
    public void getUserInfo(LoginRequest loginRequest, final IDataCallBack<UserProfile, String> callBack) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserName(loginRequest.getUserName());
        userProfile.setPassword(loginRequest.getPassword());
        apiService.getUserProfile(token, userProfile).enqueue(new Callback<BaseResponse<UserProfile>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserProfile>> call, Response<BaseResponse<UserProfile>> response) {
                if (response.isSuccessful()) {

                    if (callBack != null) {
                        callBack.onDataSuccess(response.body().getData());
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<UserProfile>> call, Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.login_error);
            }
        });
    }

    //lấy danh sách order
    public void getListOrder(int orderStatus, final IDataCallBack<List<OrderResponse>, String> dataCallBack) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        apiService.GetOrdersByOrderStatus(token, orderStatus).enqueue(new Callback<BaseResponse<List<OrderResponse>>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<List<OrderResponse>>> call, @NotNull Response<BaseResponse<List<OrderResponse>>> response) {
                if (response.isSuccessful()) {
                    List<OrderResponse> list = null;
                    if (response.body() != null) {
                        list = response.body().getData();
                    }
                    if (dataCallBack != null) {
                        dataCallBack.onDataSuccess(list);
                    }
                } else {
                    if (dataCallBack != null) {
                        dataCallBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<List<OrderResponse>>> call, @NotNull Throwable t) {
                if (dataCallBack != null) {
                    dataCallBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    // lọc order status
    public void RequestPayOrder(String orderID, final IDataCallBack<Boolean, String> callBack) {
        apiService.RequestPayOrder(token, orderID).enqueue(new Callback<BaseResponse<Boolean>>() {
            @Override
            public void onResponse(Call<BaseResponse<Boolean>> call, Response<BaseResponse<Boolean>> response) {
                if (response.isSuccessful()) {

                    if (callBack != null) {
                        callBack.onDataSuccess(response.body().getSuccess());
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Boolean>> call, Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                // CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }


    //lấy danh sách khách hàng
    public void getListCustomer(final IDataCallBack<List<Customer>, String> callBack) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        apiService.getListCustomer(token).enqueue(new Callback<BaseResponse<List<Customer>>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<List<Customer>>> call, @NotNull Response<BaseResponse<List<Customer>>> response) {
                if (response.isSuccessful()) {
                    List<Customer> data = null;
                    if (response.body() != null) {
                        data = response.body().getData();
                    }
                    if (callBack != null) {
                        callBack.onDataSuccess(data);
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<List<Customer>>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    //thêm khách hàng
    public void createCustomer(Customer customer, final IDataCallBack<Boolean, String> callBack) {
        Log.d(TAG, "createOrder: \n" + new Gson().toJson(customer));
        apiService.createCustomer(token, customer).enqueue(new Callback<BaseResponse<Boolean>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<Boolean>> call, @NotNull Response<BaseResponse<Boolean>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: ");
                    if (callBack != null) {
                        if (response.body() != null) {
                            callBack.onDataSuccess(response.body().getSuccess());
                        }
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<Boolean>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    //lấy mã order
    public void getOrderNo(final IDataCallBack<String, String> callBack) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        apiService.getOrderNo(token).enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<String>> call, @NotNull Response<BaseResponse<String>> response) {
                if (response.isSuccessful()) {
                    String orderNo = response.body().getData();
                    if (callBack != null) {
                        callBack.onDataSuccess(orderNo);
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<String>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }


    //kiểm tra order đã gửi bếp hay chưa
    public void checkOrderIsSendKitchen(String orderID, final IDataCallBack<Boolean, String> callBack) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        apiService.checkOrderBeforeCancel(token, orderID).enqueue(new Callback<BaseResponse<Boolean>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<Boolean>> call, @NotNull Response<BaseResponse<Boolean>> response) {
                if (response.isSuccessful()) {
                    Boolean data = response.body().getSuccess();
                    if (callBack != null) {
                        callBack.onDataSuccess(data);
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<Boolean>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }


    //hủy order
    public void cancelOrder(CancelOrderRequest cancelOrderRequest, final IDataCallBack<Boolean, String> callBack) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        apiService.cancelOrder(token, cancelOrderRequest).enqueue(new Callback<BaseResponse<Boolean>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<Boolean>> call, @NotNull Response<BaseResponse<Boolean>> response) {
                if (response.isSuccessful()) {
                    Boolean data = response.body().getSuccess();
                    if (callBack != null) {
                        callBack.onDataSuccess(data);
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<Boolean>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    //lấy danh sách chi tiết order với orderID
    public void GetOrderDetailsByOrderID(String orderID, final IDataCallBack<List<OrderDetail>, String> callBack) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        apiService.GetOrderDetailsByOrderID(token, orderID).enqueue(new Callback<BaseResponse<List<OrderDetail>>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<List<OrderDetail>>> call, @NotNull Response<BaseResponse<List<OrderDetail>>> response) {
                if (response.isSuccessful()) {
                    List<OrderDetail> data = null;
                    if (response.body() != null) {
                        data = response.body().getData();
                    }
                    if (callBack != null) {
                        callBack.onDataSuccess(data);
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<List<OrderDetail>>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    public void ChangePassword(ChangePassRequest changePasswordRequest, final IDataCallBack<Boolean, String> callBack) {
        if (!CommonFunc.isNetworkAvailable()) {
            CommonFunc.showToastError(R.string.internet_not_available);
            return;
        }
        Log.d(TAG, "createOrder: \n" + new Gson().toJson(changePasswordRequest));
        apiService.changePassWord(token, changePasswordRequest).enqueue(new Callback<BaseResponse<Boolean>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<Boolean>> call, @NotNull Response<BaseResponse<Boolean>> response) {
                if (response.isSuccessful()) {
                    Boolean data = response.body().getSuccess();
                    if (callBack != null) {
                        //nếu true thì thông báo thành công. đóng màn hình
                        //failed thì báo sai mật khẩu cũ
                        callBack.onDataSuccess(data);
                    }
                } else {
                    if (callBack != null) {
                        callBack.onDataFailed(response.message());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<Boolean>> call, @NotNull Throwable t) {
                if (callBack != null) {
                    callBack.onDataFailed(t.getMessage());
                }
                CommonFunc.showToastWarning(R.string.somthing_went_wrong);
            }
        });
    }

    public HashMap<String, InventoryItemMapping> getItemMappingHashMap() {
        return mItemMappingHashMap;
    }
}