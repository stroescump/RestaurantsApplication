package com.example.restaurantsapplication.interfaces;

import com.example.restaurantsapplication.models.ItemRestaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkREST {

    @GET("restaurant/list")
    Call<List<ItemRestaurant>> getRestaurants();
}
