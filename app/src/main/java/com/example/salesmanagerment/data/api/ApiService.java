package com.example.salesmanagerment.data.api;



import com.example.salesmanagerment.data.model.request.LoginRequest;
import com.example.salesmanagerment.data.model.response.base.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("Login")
    Call<BaseResponse<String>> login(@Body LoginRequest loginRequest);

//    @Headers("Content-Type: application/json")
//    @GET("user")
//    Call<UserResponse> getUser();
//
//    // lấy list đơn vị món ăn
//    @GET("unit/getlist")
//    Call<BaseResponse<List<Units>>> getUnit(@Header("authorization") String token);
//
//    // thêm đơn vị tính
//    @POST("unit/InsertUpdate")
//    Call<BaseResponse<Units>> getAddUnit(@Header("authorization") String token, @Body Units units);
//
//    //cập nhật đơn vị tính
//    @POST("unit/InsertUpdate")
//    Call<BaseResponse<Units>> getUpdateUnit(@Header("authorization") String token, @Body Units units);
//
//    //  xóa đơn vị tính
//    @POST("unit/delete")
//    Call<BaseResponse<String>> getDeleteUnit(@Header("authorization") String token, @Body String unitID);
//
//    //lấy danh sách  nhóm thực đơn
//    @GET("inventoryItemCategory/getList")
//    Call<BaseResponse<List<InventoryItemCategory>>> getListItemCategory(@Header("authorization") String token);
//
//    // thêm nhóm thực đơn
//    @POST("inventoryItemCategory/InsertUpdate")
//    Call<BaseResponse<InventoryItemCategory>> getAddGroupMenu(@Header("authorization") String token, @Body InventoryItemCategory inventoryItemCategory);
//
//    //xóa nhóm thực đơn
//    @POST("inventoryItemCategory/delete")
//    Call<BaseResponse<String>> getDeleteItemCategory(@Header("authorization") String token, @Body String itemID);
//
//    //cập nhật nhóm thực đơn
//    @POST("inventoryItemCategory/InsertUpdate")
//    Call<BaseResponse<InventoryItemCategory>> getUpdateItemCategory(@Header("authorization") String token, @Body InventoryItemCategory inventoryItemCategory);
//
//    // lấy danh sách thực đơn
//    @GET("InventoryItem/getList")
//    Call<BaseResponse<List<InventoryItem>>> getListInventoryItem(@Header("authorization") String token);
//
//    //thêm thực đơn
//    @POST("InventoryItem/InsertUpdate")
//    Call<BaseResponse<InventoryItem>> getAddInventoryItem(@Header("authorization") String token, @Body InventoryItem inventoryItem);
//
//    //thêm thực đơn
//    @POST("InventoryItem/Delete")
//    Call<BaseResponse<String>> getDeleteInventoryItem(@Header("authorization") String token, @Body String itemInventory);
}
