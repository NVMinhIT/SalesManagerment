package com.example.salesmanagerment.data.api;


import com.example.salesmanagerment.data.model.entity.Area;
import com.example.salesmanagerment.data.model.entity.InventoryItem;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;
import com.example.salesmanagerment.data.model.entity.Unit;
import com.example.salesmanagerment.data.model.request.LoginRequest;
import com.example.salesmanagerment.data.model.response.base.BaseResponse;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("Login")
    Call<BaseResponse<String>> login(@Body LoginRequest loginRequest);

//    @Headers("Content-Type: application/json")
//    @GET("user")
//    Call<UserResponse> getUser();
//
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
    Call<BaseResponse<List<TableMappingCustom>>> getListTableByAreaID(@Header("authorization") String token, @Path("objectID") String areaID, @Path("time") Date time );

}
