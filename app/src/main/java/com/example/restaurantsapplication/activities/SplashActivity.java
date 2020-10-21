package com.example.restaurantsapplication.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantsapplication.R;
import com.example.restaurantsapplication.restaurants.RestaurantsActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(() -> {

            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent seeRestaurants = new Intent(getApplicationContext(), RestaurantsActivity.class);
            startActivity(seeRestaurants);

        }).start();
    }
}
