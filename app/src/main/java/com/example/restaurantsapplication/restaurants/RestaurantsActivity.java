package com.example.restaurantsapplication.restaurants;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantsapplication.R;
import com.example.restaurantsapplication.activities.ItemRestaurant;
import com.example.restaurantsapplication.adapters.ItemAdapter;
import com.example.restaurantsapplication.helpers.JsonFields;
import com.example.restaurantsapplication.helpers.NetworkService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity {
    public static final String RESTAURANT_DETAILS_KEY="100";
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        initViews();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        getMockupData();

    }

    private void getMockupData() {
        JSONParser jsonParser = new JSONParser(this);
        String URL = getResources().getString(R.string.restaurant_details_JSONRepo);
        jsonParser.execute(URL);
    }

    private static class JSONParser extends AsyncTask<String, Void, ArrayList<ItemRestaurant>> {
        private final ArrayList<ItemRestaurant> restaurants = new ArrayList<>();
        private final NetworkService httpService = new NetworkService();
        // Using a weak reference to avoid leak static fields
        private final WeakReference<RestaurantsActivity> weakReference;


        public JSONParser(RestaurantsActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        protected ArrayList<ItemRestaurant> doInBackground(String... requestUrl) {

            try {
                JSONArray payload = new JSONArray(httpService.getDataFromAPI(requestUrl[0]));
                for (int i = 0; i < payload.length(); i++) {
                    JSONObject restaurantJSON = payload.getJSONObject(i);
                    ArrayList<String> restaurantPhotosURLs = new ArrayList<>();

                    // Getting restaurant details
                    String title = restaurantJSON.getString(JsonFields.name.toString());
                    String description = restaurantJSON.getString(JsonFields.description.toString());
                    String urlMainPhoto = restaurantJSON.getString(JsonFields.imagePath.toString());
                    JSONArray photosArray = restaurantJSON.getJSONArray(JsonFields.photos.toString());
                    double latitude = restaurantJSON.getDouble(JsonFields.latitude.toString());
                    double longitude = restaurantJSON.getDouble(JsonFields.longitude.toString());

                    // Iterating thru the array of images and adding them to a list of urls
                    for (int j = 0; j < photosArray.length(); j++) {
                        restaurantPhotosURLs.add(photosArray.getJSONObject(j).getString(JsonFields.imagePath.toString()));
                    }

                    // Creating and adding a restaurant to the main restaurant list
                    ItemRestaurant restaurant = new ItemRestaurant(urlMainPhoto, title, description, restaurantPhotosURLs, latitude, longitude);
                    restaurants.add(restaurant);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return restaurants;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemRestaurant> restaurants) {
            super.onPostExecute(restaurants);
            RestaurantsActivity activityRef = weakReference.get();
            if (activityRef != null) {
                activityRef.adapter = new ItemAdapter(restaurants, activityRef.getApplicationContext(), (position)->{
                    Intent detailsRestaurant = new Intent(activityRef, RestaurantDetailsActivity.class);
                    detailsRestaurant.putExtra(RestaurantsActivity.RESTAURANT_DETAILS_KEY,restaurants.get(position));
                    activityRef.startActivity(detailsRestaurant);
                });
                activityRef.recyclerView.setAdapter(activityRef.adapter);
                activityRef.progressBar.setVisibility(View.INVISIBLE);
            }

        }
    }
}
