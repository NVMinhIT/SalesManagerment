package com.example.salesmanagerment.data.Sharedprf;

public interface SharedPrefsApi {

    <T> T get(String key, Class<T> clazz);

    <T> void put(String key, T data);

    void clear();
}
