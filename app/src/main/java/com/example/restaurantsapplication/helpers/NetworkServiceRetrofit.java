package com.example.restaurantsapplication.helpers;

import com.example.restaurantsapplication.interfaces.NetworkREST;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceRetrofit {
    private final String baseUrl;

    public NetworkREST createNetworkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(NetworkREST.class);
    }

    public NetworkServiceRetrofit(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
