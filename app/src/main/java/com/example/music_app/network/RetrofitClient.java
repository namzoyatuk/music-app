package com.example.music_app.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class RetrofitClient {
    private static final String BASE_URL = "https://api.spotify.com/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static SpotifyService getSpotifyService() {
        return retrofit.create(SpotifyService.class);
    }
}
