package com.example.salesmanagerment;

import android.app.Application;

import com.example.salesmanagerment.data.Sharedprf.SharedPrefsImpl;
import com.example.salesmanagerment.utils.CacheManager;
import com.example.salesmanagerment.utils.CommonFunc;

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        new CacheManager(new SharedPrefsImpl(this));
        new CommonFunc(this);
    }

    public static MyApplication getInstance(){
        return myApplication;
    }
}
