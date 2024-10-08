package com.example.music_app;

import org.junit.Test;

import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.network.DTO.GetAlbumsDto;
import com.example.music_app.network.DTO.TopTrackResponseDto;
import com.example.music_app.network.DTO.TrackDto;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.network.DTO.ArtistDto;
import com.example.music_app.util.Constants;

import static org.junit.Assert.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class SpotifyServiceIntegrationTest {
    private final SpotifyService spotifyService = RetrofitClient.getSpotifyService();


    @Test
    public void artistGettingValid() {
        String artistId = "4Z8W4fKeB5YxbusRsdQVPb";
        Call<ArtistDto> call = spotifyService.getArtist(Constants.SPOTIFY_TOKEN, artistId);

        try {
            Response<ArtistDto> response = call.execute();
            assertNotNull(response.body());
            assertEquals("Radiohead", response.body().getName());
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
            assertNotNull(response.body());
            assertEquals(response.body().getName(), "Eve");
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
            assertNotNull(response.body());
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
            assertNotNull(response.body());
            List<TrackDto> tracks = response.body().getTracks();
            assert tracks.get(1).getName().equals("No Surprises");

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
            assert response.body().getAlbums().getItems().get(0).getName().equals("evermore");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}