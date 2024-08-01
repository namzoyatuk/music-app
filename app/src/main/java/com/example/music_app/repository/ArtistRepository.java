package com.example.music_app.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.music_app.network.DTO.ArtistDto;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.util.Constants;

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
            public void onResponse(Call<ArtistDto> call, Response<ArtistDto> response) {
                if (response.isSuccessful()) {
                    artistData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArtistDto> call, Throwable t) {
                artistData.setValue(null);
            }
        });
        return artistData;
    }
}
