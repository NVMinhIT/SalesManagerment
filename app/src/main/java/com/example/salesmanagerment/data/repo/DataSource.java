package com.example.salesmanagerment.data.repo;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.api.ApiService;
import com.example.salesmanagerment.data.api.ServiceGenerator;
import com.example.salesmanagerment.data.model.app.ErrorCode;
import com.example.salesmanagerment.data.model.request.LoginRequest;
import com.example.salesmanagerment.data.model.response.base.BaseResponse;
import com.example.salesmanagerment.screen.main.MainActivity;
import com.example.salesmanagerment.utils.CacheManager;
import com.example.salesmanagerment.utils.CommonFunc;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataSource {
    private static final String TAG = "SalesRepository";
    private static DataSource instance;
    private String token;

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    private ApiService apiService;

    private DataSource() {
        apiService = ServiceGenerator.createService(ApiService.class);
        token = CacheManager.cacheManager.getToken();
    }

    // màn login
    public void login(LoginRequest loginRequest, final IDataCallBack<String, String> callBack) {
        apiService.login(loginRequest).enqueue(new Callback<BaseResponse<String>>() {

            @Override
            public void onResponse(@NotNull Call<BaseResponse<String>> call, @NotNull Response<BaseResponse<String>> response) {
                if (response.isSuccessful()) {
                    String token = response.body() != null ? response.body().getData() : null;
                        callBack.onDataSuccess(token);

                } else {
                    callBack.onDataFailed(response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<String>> call, @NotNull Throwable t) {
                CommonFunc.showToastWarning(R.string.login_error);
            }
        });
    }
}
