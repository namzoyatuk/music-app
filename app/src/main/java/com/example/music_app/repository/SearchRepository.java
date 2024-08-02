package com.example.music_app.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.music_app.network.DTO.SearchResponseDto;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRepository {
    private final SpotifyService spotifyService;
    private static SearchRepository instance;

    private SearchRepository(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    public static SearchRepository getInstance(SpotifyService spotifyService) {
        if (instance == null) {
            instance = new SearchRepository(spotifyService);
        }
        return instance;
    }

    public LiveData<SearchResponseDto> search(String query, String type) {
        MutableLiveData<SearchResponseDto> searchData = new MutableLiveData<>();
        spotifyService.search(Constants.SPOTIFY_TOKEN, query, type).enqueue(new Callback<SearchResponseDto>() {
            @Override
            public void onResponse(Call<SearchResponseDto> call, Response<SearchResponseDto> response) {
                if (response.isSuccessful()) {
                    searchData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SearchResponseDto> call, Throwable t) {
                searchData.setValue(null);
            }
        });

        return searchData;
    }
}
