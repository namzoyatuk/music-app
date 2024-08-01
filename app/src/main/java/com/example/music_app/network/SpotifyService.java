package com.example.music_app.network;

import com.example.music_app.network.DTO.AlbumDto;
import  com.example.music_app.network.DTO.ArtistDto;
import com.example.music_app.network.DTO.TrackDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface SpotifyService {
    @GET("v1/artists/{id}")
    Call<ArtistDto> getArtist(
            @Header("Authorization") String token,
            @Path("id") String id
    );

    @GET("v1/albums/{id}")
    Call<AlbumDto> getAlbum(
            @Header("Authorization") String token,
            @Path("id") String id
    );

    @GET("v1/tracks/{id}")
    Call<TrackDto> getTrack(
            @Header("Authorization") String token,
            @Path("id") String id
    );

}
