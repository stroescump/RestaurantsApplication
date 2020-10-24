package com.example.restaurantsapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantsapplication.R;
import com.example.restaurantsapplication.models.ItemRestaurant;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private final ArrayList<ItemRestaurant> restaurants;
    private final Context ctx;
    private final ItemClickListener listener;

    public ItemAdapter(ArrayList<ItemRestaurant> restaurants, Context ctx, ItemClickListener listener) {
        this.restaurants = restaurants;
        this.ctx = ctx;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_restaurant_recyclerview, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        v.setOnClickListener(l -> listener.onItemClick(viewHolder.getAdapterPosition()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Glide.with(ctx)
                .load(restaurants.get(position).getUrl())
                .into(holder.image);
        holder.tv_title.setText(restaurants.get(position).getTitle());
        holder.tv_subtitle.setText(restaurants.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final AppCompatImageView image;
        private final AppCompatTextView tv_title;
        private final AppCompatTextView tv_subtitle;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imgview_restaurant);
            tv_title = itemView.findViewById(R.id.title);
            tv_subtitle = itemView.findViewById(R.id.restaurantDetails);
        }

    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
