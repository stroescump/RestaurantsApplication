package com.example.restaurantsapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantsapplication.R;

public class SplashActivity extends AppCompatActivity {
    private Button btn_findFood;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initViews();
    }

    private void initViews() {
        btn_findFood = findViewById(R.id.btn_findFood);
        btn_findFood.setOnClickListener(v -> {
            Intent seeRestaurants = new Intent(this, RestaurantsActivity.class);
            startActivity(seeRestaurants);
        });
    }
}
