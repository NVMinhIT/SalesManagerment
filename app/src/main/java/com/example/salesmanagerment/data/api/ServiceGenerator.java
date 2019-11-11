package com.example.salesmanagerment.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static final String BASE_URL = "http://khanhjm.com/api/";
    private static Gson gson = new
            GsonBuilder().setLenient().create();
    private static Retrofit retrofit;
    private static OkHttpClient.Builder okHttpClientBuilder =
            new OkHttpClient.Builder();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson));
    private static OkHttpClient okHttpClient = okHttpClientBuilder.build();

    public static <T> T createService(Class<T> serviceClass) {
        if (retrofit == null) {
            retrofit = builder.client(okHttpClient).
                    build();
        }
        return retrofit.create(serviceClass);
    }
}

