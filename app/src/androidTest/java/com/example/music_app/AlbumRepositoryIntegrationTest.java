package com.example.music_app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.repository.AlbumRepository;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AlbumRepositoryIntegrationTest {
    private final SpotifyService spotifyService = RetrofitClient.getSpotifyService();
    AlbumRepository albumRepository =  AlbumRepository.getInstance(spotifyService);


    @Test
    public void testGetAlbum() throws InterruptedException {
        // Arrange
        String albumId = "5ZICh7iFpmgreWvpU9Og4G";
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean onChangedCalled = new AtomicBoolean(false);

        // Act
        LiveData<AlbumDto> liveData = albumRepository.getAlbum(albumId);
        new Handler(Looper.getMainLooper()).post(() -> liveData.observeForever(new Observer<AlbumDto>() {
            @Override
            public void onChanged(AlbumDto albumDto) {
                // Assert
                onChangedCalled.set(true);
                assertNotNull(albumDto);
                assertEquals("Eve", albumDto.getName());
                latch.countDown();
            }
        }));

        // Wait for the response
        latch.await(10, TimeUnit.SECONDS);
        assertTrue(onChangedCalled.get());
    }


}