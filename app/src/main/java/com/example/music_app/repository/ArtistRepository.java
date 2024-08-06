package com.example.music_app.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.music_app.network.DTO.ArtistDto;
import com.example.music_app.network.DTO.TopTrackResponseDto;
import com.example.music_app.network.DTO.TrackDto;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ArtistRepository {
    private final SpotifyService spotifyService;
    private static ArtistRepository instance;

    private ArtistRepository(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    public static ArtistRepository getInstance(SpotifyService spotifyService) {
        if (instance == null) {
            instance = new ArtistRepository(spotifyService);
        }
        return instance;
    }

    public LiveData<ArtistDto> getArtist(String artistId) {
        MutableLiveData<ArtistDto> artistData = new MutableLiveData<>();
        spotifyService.getArtist(Constants.SPOTIFY_TOKEN, artistId).enqueue(new Callback<ArtistDto>() {
            @Override
            public void onResponse(@NonNull Call<ArtistDto> call, @NonNull Response<ArtistDto> response) {
                if (response.isSuccessful()) {
                    artistData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArtistDto> call, @NonNull Throwable t) {
                artistData.setValue(null);
            }
        });
        return artistData;
    }

    public LiveData<List<TrackDto>> getTopTracks(String artistId) {
        MutableLiveData<List<TrackDto>> topTracksData = new MutableLiveData<>();


        spotifyService.getTopTracks(Constants.SPOTIFY_TOKEN, artistId).enqueue(new Callback<TopTrackResponseDto>() {
            @Override
            public void onResponse(@NonNull Call<TopTrackResponseDto> call, @NonNull Response<TopTrackResponseDto> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    topTracksData.setValue(response.body().getTracks());
                    Log.e("ArtistRepository", "getTopTracks: called " + artistId);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TopTrackResponseDto> call, @NonNull Throwable t) {
                Log.e("ArtistRepository", "getTopTracks: failed", t);
                topTracksData.setValue(null);
            }
        });
        return topTracksData;
    }
}
