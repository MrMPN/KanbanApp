package com.particular.marc.kanbanapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *Class to build and hold a Retrofit instance.
 * Will interact with ApiRequest class to fetch data from GitHub API
 */
public class RetrofitClient {
    private static final String TAG = "RetrofitClient";
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.github.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
