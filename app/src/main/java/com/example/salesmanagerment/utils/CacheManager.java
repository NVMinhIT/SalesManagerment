package com.example.salesmanagerment.utils;


import com.example.salesmanagerment.data.Sharedprf.SharedPrefsImpl;

public class CacheManager {

    public static final String K_TOKEN = "K_TOKEN";
    public static final int ACCOUNT_DISH = 123;
    public static final double ACCOUNT = 123f;

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

}
