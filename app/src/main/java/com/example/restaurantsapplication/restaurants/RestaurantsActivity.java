package com.example.restaurantsapplication.restaurants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantsapplication.R;
import com.example.restaurantsapplication.adapters.ItemAdapter;
import com.example.restaurantsapplication.helpers.NetworkServiceRetrofit;
import com.example.restaurantsapplication.models.ItemRestaurant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantsActivity extends AppCompatActivity {
    public static final String RESTAURANT_DETAILS_KEY = "100";
    private static final String TAG = RestaurantsActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        initViews();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        getMockupData();
    }

    private void getMockupData() {
        String URL = getResources().getString(R.string.restaurant_details_JSONRepo);

        NetworkServiceRetrofit retrofit = new NetworkServiceRetrofit(URL);
        retrofit.createNetworkRequest()
                .getRestaurants()
                .enqueue(new Callback<List<ItemRestaurant>>() {
            @Override
            public void onResponse(Call<List<ItemRestaurant>> call, Response<List<ItemRestaurant>> response) {
                if (response.body() != null) {
                    List<ItemRestaurant> restaurants = response.body();
                    adapter = new ItemAdapter((ArrayList<ItemRestaurant>) restaurants, RestaurantsActivity.this, (position) -> {
                        Intent detailsRestaurant = new Intent(RestaurantsActivity.this, RestaurantDetailsActivity.class);
                        detailsRestaurant.putExtra(RestaurantsActivity.RESTAURANT_DETAILS_KEY, restaurants.get(position));
                        startActivity(detailsRestaurant);
                    });
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<ItemRestaurant>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
