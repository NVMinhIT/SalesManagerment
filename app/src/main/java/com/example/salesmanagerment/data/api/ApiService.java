package com.example.salesmanagerment.data.api;


import com.example.salesmanagerment.data.model.entity.Area;
import com.example.salesmanagerment.data.model.entity.Customer;
import com.example.salesmanagerment.data.model.entity.InventoryItem;
import com.example.salesmanagerment.data.model.entity.OrderEntity;
import com.example.salesmanagerment.data.model.entity.OrderResponse;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;
import com.example.salesmanagerment.data.model.entity.Unit;
import com.example.salesmanagerment.data.model.entity.UserProfile;
import com.example.salesmanagerment.data.model.request.LoginRequest;
import com.example.salesmanagerment.data.model.response.base.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("Login")
    Call<BaseResponse<String>> login(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @POST("Login/getuser")
    Call<BaseResponse<UserProfile>> getUserProfile(@Header("authorization") String token, @Body UserProfile userProfile);

    // lấy list đơn vị món ăn
    @GET("unit/getlist")
    Call<BaseResponse<List<Unit>>> getListUnit(@Header("authorization") String token);

    // lấy danh sách thực đơn
    @GET("InventoryItem/getList")
    Call<BaseResponse<List<InventoryItem>>> getListInventoryItem(@Header("authorization") String token);

    // lấy danh sách khu vực
    @GET("Area/getList")
    Call<BaseResponse<List<Area>>> getListArea(@Header("authorization") String token);

    // lấy danh sách khu bàn theo AreaID
    @GET("TableMapping/GetByAreaID")
    Call<BaseResponse<List<TableMappingCustom>>> getListTableByAreaID(@Header("authorization") String token, @Query("areaID") String areaID, @Query("time") String time);

    // thêm order
    @POST("Order/InsertUpdateOrderEntity")
    Call<BaseResponse<Boolean>> createOrder(@Header("authorization") String token, @Body OrderEntity orderEntity);

    // lấy danh sách  order
    @GET("Order/GetList")
    Call<BaseResponse<List<OrderResponse>>> getListOrder(@Header("authorization") String token);

    // lấy danh sách khách hàng
    @GET("Customer/GetList")
    Call<BaseResponse<List<Customer>>> getListCustomer(@Header("authorization") String token);


    // thêm khách hàng
    @POST("Customer/InsertUpdate")
    Call<BaseResponse<Boolean>> createCustomer(@Header("authorization") String token, @Body Customer customer);
}
