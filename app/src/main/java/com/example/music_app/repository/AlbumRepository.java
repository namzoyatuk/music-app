package com.example.music_app.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AlbumRepository {
    private final SpotifyService spotifyService;

    public AlbumRepository(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    public LiveData<AlbumDto> getAlbum(String albumId) {
        MutableLiveData<AlbumDto> albumData = new MutableLiveData<>();
        spotifyService.getAlbum(Constants.SPOTIFY_TOKEN, albumId).enqueue(new Callback<AlbumDto>() {
            @Override
            public void onResponse(Call<AlbumDto> call, Response<AlbumDto> response) {
                if (response.isSuccessful()) {
                    albumData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AlbumDto> call, Throwable t) {
                // Handle failure
            }
        });
        return albumData;
    }
}
