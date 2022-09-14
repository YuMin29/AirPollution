package com.yumin.airpollution.repository;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteApiManager {
    private static Retrofit retrofit;
    private static RemoteApiManager retrofitManager;

    public static RemoteApiManager newInstance() {
        if (retrofitManager == null) {
            synchronized (RemoteApiManager.class) {
                retrofitManager = new RemoteApiManager();
            }
        }
        return retrofitManager;
    }

    private RemoteApiManager() {
        retrofit = getRetrofit();
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
    }

    public <T> T create(Class<T> t) {
        return retrofit.create(t);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(RemoteApiService.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
