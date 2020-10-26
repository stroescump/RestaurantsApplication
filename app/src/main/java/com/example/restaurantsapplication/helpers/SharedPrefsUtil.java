package com.example.restaurantsapplication.helpers;

import android.content.SharedPreferences;

public class SharedPrefsUtil {
    private final SharedPreferences prefs;

    public SharedPrefsUtil(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    public boolean isRestaurantFavorite(String restaurantTitle) {
        return prefs.getBoolean(restaurantTitle, false);
    }

    public void changeRestaurantFavorite(String restaurantTitle, boolean isFavorite) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(restaurantTitle, isFavorite);
        editor.apply();
    }
}
