package com.android.byc.hello.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/15 12:15
 * @description
 */
public class Network {
    public static Network network;
    public Retrofit retrofit;
    public CoinsTaskApi coinsTaskApi;

    public Network() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://staservice.fooww.com/Mobile/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        coinsTaskApi = retrofit.create(CoinsTaskApi.class);
    }

    public static Network getInstance() {
        if (network == null) {
            network = new Network();

        }

        return network;
    }

    public CoinsTaskApi getCoinsTaskApi() {
        return coinsTaskApi;
    }
}

