package com.example.music_app;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.network.DTO.ArtistDto;
import com.example.music_app.network.DTO.ImageDto;
import com.example.music_app.network.DTO.TrackDto;
import com.example.music_app.repository.AlbumRepository;
import com.example.music_app.viewmodel.AlbumViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AlbumViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private AlbumRepository albumRepository;

    private AlbumViewModel albumViewModel;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        albumViewModel = new AlbumViewModel(albumRepository);
    }

    @Test
    public void testGetAlbum() {
        String albumId = "123";
        AlbumDto mockAlbum = new AlbumDto();
        mockAlbum.setAlbum_type("compilation");
        mockAlbum.setTotal_tracks(9);
        mockAlbum.setHref("string");
        mockAlbum.setId("2up3OPMp9Tb4dAKM2erWXQ");

        List<ImageDto> images = new ArrayList<>();
        ImageDto imageDto = new ImageDto();
        imageDto.setUrl("https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228");
        imageDto.setHeight(300);
        imageDto.setWidth(300);
        images.add(imageDto);
        mockAlbum.setImages(images);

        mockAlbum.setName("Sample Album Name");
        mockAlbum.setRelease_date("1981-12");
        mockAlbum.setType("album");
        mockAlbum.setUri("spotify:album:2up3OPMp9Tb4dAKM2erWXQ");

        List<ArtistDto> artists = new ArrayList<>();
        ArtistDto artistDto = new ArtistDto();
        artistDto.setHref("string");
        artistDto.setId("string");
        artistDto.setName("Sample Artist Name");
        artistDto.setType("artist");
        artistDto.setUri("string");
        artists.add(artistDto);
        mockAlbum.setArtists(artists);

        List<String> genres = new ArrayList<>();
        genres.add("Egg punk");
        genres.add("Noise rock");
        mockAlbum.setGenres(genres);

        mockAlbum.setLabel("Sample Label");
        mockAlbum.setPopularity(80); // Example popularity

        AlbumDto.AlbumTracks tracks = new AlbumDto.AlbumTracks();

        TrackDto trackDto = new TrackDto();
        trackDto.setName("Sample Track Name");

        mockAlbum.setTracks(tracks);

        LiveData<AlbumDto> mockLiveData = new MutableLiveData<>(mockAlbum);
        Mockito.when(albumRepository.getAlbum(albumId)).thenReturn(mockLiveData);

        LiveData<AlbumDto> result = albumViewModel.getAlbum(albumId);
        assertEquals(mockAlbum, result.getValue());
    }


}