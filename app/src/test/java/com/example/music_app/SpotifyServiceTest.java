package com.example.music_app;

import org.junit.Test;

import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.network.DTO.GetAlbumsDto;
import com.example.music_app.network.DTO.SearchAlbumDto;
import com.example.music_app.network.DTO.SearchResponseDto;
import com.example.music_app.network.DTO.TopTrackResponseDto;
import com.example.music_app.network.DTO.TrackDto;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.network.DTO.ArtistDto;
import com.example.music_app.util.Constants;

import static org.junit.Assert.*;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SpotifyServiceTest {
    private final SpotifyService spotifyService = RetrofitClient.getSpotifyService();


    @Test
    public void artistGettingValid() {

        String artistId = "4Z8W4fKeB5YxbusRsdQVPb";

        Call<ArtistDto> call = spotifyService.getArtist(Constants.SPOTIFY_TOKEN, artistId);

        try {
            Response<ArtistDto> response = call.execute();
            assert response.body() != null;
            System.err.println(response.body().getName());
            System.err.println(response.body().getImages().get(0).getUrl());
            System.err.println(response.body().getGenres().get(0));
            System.err.println(response.body().getPopularity());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void albumGettingValid() {
        String albumId = "5ZICh7iFpmgreWvpU9Og4G";
        Call<AlbumDto> call = spotifyService.getAlbum(Constants.SPOTIFY_TOKEN, albumId);

        try {
            Response<AlbumDto> response = call.execute();
            assert response.body() != null;
            assertEquals(response.body().getName(), "Eve");
            System.err.println(response.body().getName());
            System.err.println(response.body().getImages().get(0).getUrl());
            System.err.println(response.body().getPopularity());
            System.err.println(response.body().getRelease_date());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void trackGettingValid(){
        String trackId = "6G18KE6mtFITAOCFxGjk7P";
        Call<TrackDto> call = spotifyService.getTrack(Constants.SPOTIFY_TOKEN, trackId);
        try {
            Response<TrackDto> response = call.execute();
            assert response.body() != null;
            System.err.println(response.body().getName());
            System.err.println(response.body().getAlbum().getName());
            System.err.println(response.body().getPreviewUrl());
            System.err.println(response.body().getPopularity());
            System.err.println(response.body().getArtists().get(0).getName());
            System.err.println(response.body().getTrackNumber());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void topTracksGettingValid(){
        String artistId = "4Z8W4fKeB5YxbusRsdQVPb";
        Call<TopTrackResponseDto> call = spotifyService.getTopTracks(Constants.SPOTIFY_TOKEN, artistId);
        try {
            Response<TopTrackResponseDto> response = call.execute();
            assert response.body() != null;
            List<TrackDto> tracks = response.body().getTracks();
            assert response.body() != null;
            System.err.println(tracks.size());
            System.err.println(tracks.get(0).getName());
            System.err.println(tracks.get(1).getName());

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Test for search
    @Test
    public void searchValid(){
        String query = "Eve";
        Call<com.example.music_app.network.DTO.SearchResponseDto> call = spotifyService.search(Constants.SPOTIFY_TOKEN, query, "album");
        try {
            Response<com.example.music_app.network.DTO.SearchResponseDto> response = call.execute();
            assert response.body() != null;
            System.err.println(response.body().getAlbums().getItems().get(0).getName());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("AA");
        }
    }

    //Test for new releases
    @Test
    public void newReleasesValid(){
        Call<com.example.music_app.network.DTO.SearchResponseDto> call = spotifyService.getNewReleases(Constants.SPOTIFY_TOKEN);
        try {
            Response<com.example.music_app.network.DTO.SearchResponseDto> response = call.execute();
            assert response.body() != null;
            System.err.println(response.body().getAlbums().getItems());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("AA");
        }
    }

    @Test
    public void getAlbumsValid(){
//        String albumIds = "5ZICh7iFpmgreWvpU9Og4G,7cEfK4Fu6GRuo7G8yvOER,2v9PjvIkQVnyQdtD1iQD7e,1m07iv4leo4aoqFs60vHCK,6GGkLeS1HTtr45DlF8YHYN";
        String albumIds = "5ZICh7iFpmgreWvpU9Og4G,2v9PjvIkQVnyQdtD1iQD7e,1m07iv4leo4aoqFs60vHCK,6GGkLeS1HTtr45DlF8YHYN";

        Call<GetAlbumsDto> call = spotifyService.getAlbums(Constants.SPOTIFY_TOKEN, albumIds);
        try {
            Response<GetAlbumsDto> response = call.execute();
            assert response.body() != null;
            System.err.println(response.body().getAlbums());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("AA");
        }
    }

}