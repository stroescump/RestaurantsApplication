package com.example.restaurantsapplication.restaurants;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantsapplication.R;
import com.example.restaurantsapplication.activities.ItemRestaurant;
import com.example.restaurantsapplication.adapters.DishesAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ItemRestaurant restaurant;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
       if(getSupportActionBar()!=null){
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           getSupportActionBar().setHomeButtonEnabled(true);
       }
        restaurant = getIntent().getParcelableExtra(RestaurantsActivity.RESTAURANT_DETAILS_KEY);
        initViews();
    }

    private void initViews() {
        AppCompatTextView tv_restaurantTitle = findViewById(R.id.tv_restaurantTitle);
        AppCompatTextView tv_restaurantDetails = findViewById(R.id.tv_restaurantDetails);
        tv_restaurantDetails.setText(restaurant.getSubtitle());
        tv_restaurantTitle.setText(restaurant.getTitle());
        tv_restaurantDetails.setMovementMethod(ScrollingMovementMethod.getInstance());
        RecyclerView recyclerView = findViewById(R.id.recycler_viewDishes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        if (restaurant != null) {
            DishesAdapter dishesAdapter = new DishesAdapter(restaurant.getDishesRestaurant(), this);
            recyclerView.setAdapter(dishesAdapter);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng restaurantCoordinates = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(restaurantCoordinates).title(restaurant.getTitle()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurantCoordinates, 15));
    }
}
