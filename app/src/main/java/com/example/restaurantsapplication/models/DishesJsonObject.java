package com.example.restaurantsapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DishesJsonObject implements Parcelable {
    @SerializedName("imagePath")
    private final String imagePath;

    public DishesJsonObject(String imagePath) {
        this.imagePath = imagePath;
    }

    protected DishesJsonObject(Parcel in) {
        imagePath = in.readString();
    }

    public static final Creator<DishesJsonObject> CREATOR = new Creator<DishesJsonObject>() {
        @Override
        public DishesJsonObject createFromParcel(Parcel in) {
            return new DishesJsonObject(in);
        }

        @Override
        public DishesJsonObject[] newArray(int size) {
            return new DishesJsonObject[size];
        }
    };

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imagePath);
    }
}
