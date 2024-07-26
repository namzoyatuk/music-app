package com.example.music_app;

import org.junit.Test;

import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.network.DTO.TrackDto;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.network.DTO.ArtistDto;

import static org.junit.Assert.*;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SpotifyServiceTest {
    private final SpotifyService spotifyService = RetrofitClient.getSpotifyService();
    private final String accessToken = "BQDHuZTJM7aqOAct-KUoWjjn2ccZLDtvB734pUpPe0sWuH66HqtF9vgw9RuFjrNIwBVxDcdcazh0McaldfN3ZqJ0Ja6KPGxPdPlyzLPGAkJbnw2mKHU";

    @Test
    public void artistGettingValid() {

        String artistId = "4Z8W4fKeB5YxbusRsdQVPb";

        Call<ArtistDto> call = spotifyService.getArtist("Bearer " + accessToken, artistId);

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

        Call<AlbumDto> call = spotifyService.getAlbum("Bearer " + accessToken, albumId);

        try {
            Response<AlbumDto> response = call.execute();
            assert response.body() != null;
            System.err.println(response.body().getName());
            System.err.println(response.body().getImages().get(0).getUrl());
            System.err.println(response.body().getPopularity());
            System.err.println(response.body().getGenres().get(0));

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void trackGettingValid(){
        String trackId = "6G18KE6mtFITAOCFxGjk7P";
        Call<TrackDto> call = spotifyService.getTrack("Bearer " + accessToken, trackId);
        try {
            Response<TrackDto> response = call.execute();
            assert response.body() != null;
            System.err.println(response.body().getName());
            System.err.println(response.body().getAlbum().getName());
            System.err.println(response.body().getPreview_url());
            System.err.println(response.body().getPopularity());
            System.err.println(response.body().getArtists().get(0).getName());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}