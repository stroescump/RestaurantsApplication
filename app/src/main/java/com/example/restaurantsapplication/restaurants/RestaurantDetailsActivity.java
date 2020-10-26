package com.example.restaurantsapplication.restaurants;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantsapplication.R;
import com.example.restaurantsapplication.adapters.DishesAdapter;
import com.example.restaurantsapplication.helpers.SharedPrefsUtil;
import com.example.restaurantsapplication.models.DishesJsonObject;
import com.example.restaurantsapplication.models.ItemRestaurant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ItemRestaurant restaurant;
    private SharedPreferences prefs;
    private boolean isFavorite;
    private SharedPrefsUtil prefsUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        prefs = getSharedPreferences("favorite_restaurant", MODE_PRIVATE);
        restaurant = getIntent().getParcelableExtra(RestaurantsActivity.RESTAURANT_DETAILS_KEY);
        initViews();

    }

    private void initViews() {
        initToolbar();
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
            ArrayList<String> dishesURLs = new ArrayList<>();
            List<DishesJsonObject> dishesRaw = restaurant.getDishesRestaurant();
            for (DishesJsonObject dish : dishesRaw) {
                dishesURLs.add(dish.getImagePath());
            }
            DishesAdapter dishesAdapter = new DishesAdapter(dishesURLs, this);
            recyclerView.setAdapter(dishesAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        prefsUtil = new SharedPrefsUtil(prefs);
        isFavorite = prefsUtil.isRestaurantFavorite(restaurant.getTitle());
        menu.findItem(R.id.favoriteRestaurant)
                .setIcon(isFavorite ? R.drawable.favorite_true_foreground : R.drawable.favorite_false_foreground);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.favoriteRestaurant) {
            if (isFavorite) {
                item.setIcon(R.drawable.favorite_false_foreground);
            } else {
                item.setIcon(R.drawable.favorite_true_foreground);
            }
            isFavorite=!isFavorite;
            prefsUtil.changeRestaurantFavorite(restaurant.getTitle(), isFavorite);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(restaurant.getTitle());
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng restaurantCoordinates = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(restaurantCoordinates).title(restaurant.getTitle()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurantCoordinates, 15));
    }
}
