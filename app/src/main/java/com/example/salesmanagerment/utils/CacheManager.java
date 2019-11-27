package com.example.salesmanagerment.utils;


import com.example.salesmanagerment.data.Sharedprf.SharedPrefsImpl;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.data.model.entity.UserProfile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CacheManager {

    public static final String K_TOKEN = "K_TOKEN";
    public static final int ACCOUNT_DISH = 123;
    public static final double ACCOUNT = 123f;
    public static final String USER_INFO = "USER_INFO";
    public static final String K_LIST_OLD_ORDER_DETAILS_ID = "K_LIST_OLD_ORDER_DETAILS_ID";


    private SharedPrefsImpl sharedPrefs;

    public static CacheManager cacheManager;

    public static CacheManager getInstance() {
        return cacheManager;
    }

    /**
     * @param sharedPrefs
     * @Create_By NvMinh at 04/11/2019
     */
    public CacheManager(SharedPrefsImpl sharedPrefs) {
        this.sharedPrefs = sharedPrefs;
        cacheManager = this;
    }

    public void cacheAccountDish(int account) {
        sharedPrefs.put("ACCOUNT_DISH", account);
    }

    public int getAccountDish() {
        return sharedPrefs.get("ACCOUNT_DISH", Integer.class);

    }


    public void cacheToken(String token) {
        sharedPrefs.put(K_TOKEN, token);
    }

    public String getToken() {
        return sharedPrefs.get(K_TOKEN, String.class);
    }

    public void cacheUser(UserProfile userProfile) {
        sharedPrefs.put(USER_INFO, new Gson().toJson(userProfile));
    }

    public UserProfile getUser() {
        String tracks = sharedPrefs.get(USER_INFO, String.class);
        return new Gson().fromJson(tracks, UserProfile.class);
    }


    public void cacheOldOrderDetail(List<OrderDetail> orderDetails){
       sharedPrefs.put(K_LIST_OLD_ORDER_DETAILS_ID, new Gson().toJson(orderDetails));
    }

    public List<OrderDetail> getOldOrderDetail(){
        String listID = sharedPrefs.get(K_LIST_OLD_ORDER_DETAILS_ID, String.class);
        Type listType = new TypeToken<ArrayList<OrderDetail>>() {
        }.getType();
        return new Gson().fromJson(listID, listType);
    }
}
