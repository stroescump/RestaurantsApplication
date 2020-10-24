package com.example.restaurantsapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemRestaurant implements Parcelable {
    @SerializedName("imagePath")
    private final String url;
    @SerializedName("name")
    private final String title;
    @SerializedName("description")
    private final String subtitle;
    @SerializedName("photos")
    private final List<DishesJsonObject> dishesRestaurant;
    @SerializedName("latitude")
    private final double latitude;
    @SerializedName("longitude")
    private final double longitude;

    public ItemRestaurant(String url, String title, String subtitle, List<DishesJsonObject> dishesRestaurant, double latitude, double longitude) {
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
        dishesRestaurant = in.createTypedArrayList(DishesJsonObject.CREATOR);
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

    public List<DishesJsonObject> getDishesRestaurant() {
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
        dest.writeTypedList(dishesRestaurant);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
