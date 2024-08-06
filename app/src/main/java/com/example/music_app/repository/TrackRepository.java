package com.example.music_app.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.music_app.network.DTO.TrackDto;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackRepository {
    private final SpotifyService spotifyService;
    private static TrackRepository instance;

    private TrackRepository(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    public static TrackRepository getInstance(SpotifyService spotifyService) {
        if (instance == null) {
            instance = new TrackRepository(spotifyService);
        }
        return instance;
    }

    public LiveData<TrackDto> getTrack(String trackId) {
        MutableLiveData<TrackDto> trackData = new MutableLiveData<>();
        spotifyService.getTrack(Constants.SPOTIFY_TOKEN, trackId).enqueue(new Callback<TrackDto>() {
            @Override
            public void onResponse(@NonNull Call<TrackDto> call, @NonNull Response<TrackDto> response) {
                if (response.isSuccessful()) {
                    trackData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrackDto> call, @NonNull Throwable t) {
                System.err.println("Could not reach to the api while getting track: " + t.getMessage());
            }
        });
        return trackData;
    }
}
