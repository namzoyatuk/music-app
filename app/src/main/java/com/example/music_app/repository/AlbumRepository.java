package com.example.music_app.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.network.DTO.GetAlbumsDto;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AlbumRepository {
    private final SpotifyService spotifyService;
    private static AlbumRepository instance;

    private AlbumRepository(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    public static AlbumRepository getInstance(SpotifyService spotifyService) {
        if (instance == null) {
            instance = new AlbumRepository(spotifyService);
        }
        return instance;
    }

    public LiveData<AlbumDto> getAlbum(String albumId) {
        MutableLiveData<AlbumDto> albumData = new MutableLiveData<>();
        spotifyService.getAlbum(Constants.SPOTIFY_TOKEN, albumId).enqueue(new Callback<AlbumDto>() {
            @Override
            public void onResponse(@NonNull Call<AlbumDto> call, @NonNull Response<AlbumDto> response) {
                if (response.isSuccessful()) {
                    albumData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<AlbumDto> call, @NonNull Throwable t) {
                System.err.println("Could not reach to the api while getting track: " + t.getMessage());
            }
        });
        return albumData;
    }


    public LiveData<List<AlbumDto>> getAlbums(List<String> albumIds) {
        MutableLiveData<List<AlbumDto>> albumsLiveData = new MutableLiveData<>();
        String ids = String.join(",", albumIds);
        spotifyService.getAlbums(Constants.SPOTIFY_TOKEN, ids).enqueue(new Callback<GetAlbumsDto>() {
            @Override
            public void onResponse(@NonNull Call<GetAlbumsDto> call, @NonNull Response<GetAlbumsDto> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    albumsLiveData.setValue(response.body().getAlbums());
                } else {
                    albumsLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetAlbumsDto> call, @NonNull Throwable t) {
                albumsLiveData.setValue(null);
            }
        });
        return albumsLiveData;
    }






}
