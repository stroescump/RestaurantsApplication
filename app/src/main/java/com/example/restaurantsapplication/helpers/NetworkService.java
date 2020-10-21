package com.example.restaurantsapplication.helpers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkService {
    public NetworkService() {
    }

    public String getDataFromAPI(String requestURL) {
        String response = null;

        try {
            URL url = new URL(requestURL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder builder = new StringBuilder();

            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append('\n');
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                in.close();
            }

            response = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
