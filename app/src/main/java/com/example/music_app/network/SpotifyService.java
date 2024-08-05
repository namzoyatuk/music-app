package com.example.music_app.network;

import com.example.music_app.network.DTO.AlbumDto;
import  com.example.music_app.network.DTO.ArtistDto;
import com.example.music_app.network.DTO.GetAlbumsDto;
import com.example.music_app.network.DTO.SearchAlbumDto;
import com.example.music_app.network.DTO.SearchResponseDto;
import com.example.music_app.network.DTO.TopTrackResponseDto;
import com.example.music_app.network.DTO.TrackDto;

import java.util.List;

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

    @GET("v1/artists/{id}/top-tracks")
    Call<TopTrackResponseDto> getTopTracks(
            @Header("Authorization") String token,
            @Path("id") String id
    );

    @GET("v1/search")
    Call<SearchResponseDto> search(
            @Header("Authorization") String token,
            @Query("q") String query,
            @Query("type") String type
    );

    @GET("v1/new-releases")
    Call<SearchResponseDto> getNewReleases(
            @Header("Authorization") String token
    );

    @GET("v1/albums")
    Call<GetAlbumsDto> getAlbums(@Header("Authorization") String token,
                                 @Query("ids") String albumIds);

}
