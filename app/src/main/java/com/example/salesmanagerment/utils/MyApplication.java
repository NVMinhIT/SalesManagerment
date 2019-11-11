package com.example.salesmanagerment.utils;

import android.app.Application;

import com.example.salesmanagerment.utils.Sharedprf.SharedPrefsImpl;


public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        new CacheManager(new SharedPrefsImpl(this));
    }

    public static MyApplication getInstance(){
        return myApplication;
    }
}
