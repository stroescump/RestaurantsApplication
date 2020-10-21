package com.example.restaurantsapplication.activities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ItemRestaurant implements Parcelable {
    private final String url;
    private final String title;
    private final String subtitle;
    private final ArrayList<String> dishesRestaurant;
    private final double latitude;
    private final double longitude;

    public ItemRestaurant(String url, String title, String subtitle, ArrayList<String> dishesRestaurant, double latitude, double longitude) {
        this.url = url;
        this.title = title;
        this.subtitle = subtitle;
        this.dishesRestaurant = dishesRestaurant;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected ItemRestaurant(Parcel in) {
        url = in.readString();
        title = in.readString();
        subtitle = in.readString();
        dishesRestaurant = in.createStringArrayList();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<ItemRestaurant> CREATOR = new Creator<ItemRestaurant>() {
        @Override
        public ItemRestaurant createFromParcel(Parcel in) {
            return new ItemRestaurant(in);
        }

        @Override
        public ItemRestaurant[] newArray(int size) {
            return new ItemRestaurant[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public ArrayList<String> getDishesRestaurant() {
        return dishesRestaurant;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(title);
        dest.writeString(subtitle);
        dest.writeStringList(dishesRestaurant);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
