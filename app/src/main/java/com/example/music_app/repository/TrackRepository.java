package com.example.music_app.repository;

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

    public TrackRepository(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    public LiveData<TrackDto> getTrack(String trackId) {
        MutableLiveData<TrackDto> trackData = new MutableLiveData<>();
        spotifyService.getTrack(Constants.SPOTIFY_TOKEN, trackId).enqueue(new Callback<TrackDto>() {
            @Override
            public void onResponse(Call<TrackDto> call, Response<TrackDto> response) {
                if (response.isSuccessful()) {
                    trackData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TrackDto> call, Throwable t) {
                // Handle failure
            }
        });
        return trackData;
    }
}
