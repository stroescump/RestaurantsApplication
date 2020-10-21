package com.example.restaurantsapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantsapplication.R;

import java.util.ArrayList;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.DishesViewHolder> {
    private final ArrayList<String> dishesURLs;
    private final Context ctx;

    public DishesAdapter(ArrayList<String> dishesURLs, Context ctx) {
        this.dishesURLs = dishesURLs;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public DishesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_restaurant_details, parent, false);
        return new DishesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DishesViewHolder holder, int position) {
        Glide.with(ctx).load(dishesURLs.get(position)).into(holder.dishImage);
    }

    @Override
    public int getItemCount() {
        return dishesURLs.size();
    }

    static class DishesViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatImageView dishImage;

        public DishesViewHolder(@NonNull View itemView) {
            super(itemView);
            dishImage = itemView.findViewById(R.id.img_dish);
        }
    }
}
