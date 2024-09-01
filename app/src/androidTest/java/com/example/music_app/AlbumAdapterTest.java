package com.example.music_app;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.music_app.adapter.AlbumAdapter;
import com.example.music_app.databinding.ItemAlbumBinding;
import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.network.DTO.ArtistDto;
import com.example.music_app.network.DTO.ImageDto;
import com.example.music_app.ui.ArtistActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class) // Use AndroidJUnit4 runner
public class AlbumAdapterTest {

    @Mock
    private Context contextMock;

    @Mock
    private RequestManager glideMock;

    private AlbumAdapter adapter;
    private AlbumDto mockAlbum;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockAlbum = createMockAlbum();
        Context realContext = ApplicationProvider.getApplicationContext(); // Get real context
        adapter = new AlbumAdapter(realContext, mockAlbum);
    }



    @Test
    public void testOnBindViewHolder_createsCorrectIntent() {
        // Call the method under test (passing a dummy position)
        adapter.onBindViewHolder(null, 0); // Passing null for ViewHolder is fine here

        // Verify intent creation (without starting activity)
        ArgumentCaptor<Intent> intentCaptor = ArgumentCaptor.forClass(Intent.class);
        Mockito.verify(contextMock).startActivity(intentCaptor.capture());
        Intent capturedIntent = intentCaptor.getValue();

        assertEquals(ArtistActivity.class.getName(), capturedIntent.getComponent().getClassName());
        assertEquals("artist123",capturedIntent.getStringExtra("artistId"));
    }


    // Helper method to create a mock AlbumDto
    private AlbumDto createMockAlbum() {
        AlbumDto album = new AlbumDto();
        album.setName("Sample Album");
        album.setRelease_date("1981-12");List<ArtistDto> artists = new ArrayList<>();
        ArtistDto artist = new ArtistDto();
        artist.setId("artist123");
        artist.setName("Sample Artist");
        artists.add(artist);
        album.setArtists(artists);

        List<ImageDto> images = new ArrayList<>();
        ImageDto image = new ImageDto();
        image.setUrl("https://sample.image.url");
        images.add(image);
        album.setImages(images);

        return album;
    }
}
