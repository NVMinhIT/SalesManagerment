package com.example.salesmanagerment.utils.Sharedprf;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp thao tác với SharePreferences
 *
 */
public class SharedPrefsImpl implements SharedPrefsApi {

    private static final String PREFS_NAME = "SellManagement";

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    private SharedPreferences mSharedPreferences;

    public SharedPrefsImpl(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    /**
     * Phương thức lấy dữ liệu
     * @param key   - Key cho dữ liệu
     * @param <T>   - Kiểu dữ liệu nguyên thủy
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> clazz) {
        if (clazz == String.class) {
            return (T) mSharedPreferences.getString(key, "");
        } else if (clazz == Boolean.class) {
            return (T) Boolean.valueOf(mSharedPreferences.getBoolean(key, false));
        } else if (clazz == Float.class) {
            return (T) Float.valueOf(mSharedPreferences.getFloat(key, 0));
        } else if (clazz == Integer.class) {
            return (T) Integer.valueOf(mSharedPreferences.getInt(key, 0));
        } else if (clazz == Long.class) {
            return (T) Long.valueOf(mSharedPreferences.getLong(key, 0));
        }
        return null;
    }

    /**
     * Phương thức thêm/gán dữ liệu
     * @param key   - Key cho dữ liệu
     * @param data  - Dữ liệu
     * @param <T>   - Kiểu dữ liệu nguyên thủy
     */
    @Override
    public <T> void put(String key, T data) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        }
        editor.apply();
    }

    /**
     * Phương thức xóa toàn bộ dữ liệu
     * @Created_by nblinh on 16/09/2019
     */
    @Override
    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }

    /**
     * Phương thức lấy danh sách đối tượng từ string json từ SharePref
     * @param <T>   - Kiểu đối tượng
     */
    public <T> List<T> getListObject() {
        String tracks = mSharedPreferences.getString(SharedPrefsKey.KEY_LIST_OBJECT, null);
        Type listType = new TypeToken<ArrayList<T>>() {
        }.getType();
        return new Gson().fromJson(tracks, listType);
    }

    /**
     * Phương thức thêm/gán danh sách đối tượng sang json string vào SharePref
     * @param <T>   - Kiểu đối tượng
     */
    public <T> void putListMsg(List<T> objects) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(SharedPrefsKey.KEY_LIST_OBJECT, new Gson().toJson(objects)).apply();
    }
}
