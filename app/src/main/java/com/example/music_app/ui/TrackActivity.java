package com.example.music_app.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music_app.MainActivity;
import com.example.music_app.R;
import com.example.music_app.adapter.TrackAdapter;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.repository.TrackRepository;
import com.example.music_app.viewmodel.TrackViewModel;
import androidx.media3.common.MediaItem;
import androidx.media3.ui.PlayerView;


public class TrackActivity extends AppCompatActivity {
    private TrackAdapter trackAdapter;
    private String previewUrl;
    private ExoPlayer exoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        // Set up RecyclerView with a LinearLayoutManager
        RecyclerView recyclerView = findViewById(R.id.recycler_track);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Spotify service and repositories
        SpotifyService spotifyService = RetrofitClient.getSpotifyService();
        TrackRepository trackRepository = TrackRepository.getInstance(spotifyService);
        TrackViewModel.Factory factory = new TrackViewModel.Factory(trackRepository);
        TrackViewModel trackViewModel = new ViewModelProvider(this, factory).get(TrackViewModel.class);

        // Get track ID from the intent
        String trackId = getIntent().getStringExtra("trackId");

        // Observe the track LiveData and update the adapter
        trackViewModel.getTrack(trackId).observe(this, track -> {
            trackAdapter = new TrackAdapter(this, track);
            recyclerView.setAdapter(trackAdapter);
        });

        // Set up the home button click listener
        findViewById(R.id.button_home_track).setOnClickListener(v -> {
            Intent intent = new Intent(TrackActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Get preview URL from the intent
        previewUrl = getIntent().getStringExtra("previewUrl");

        if (previewUrl != null) {
            initializePlayer();
        }
    }

    // Method to initialize the ExoPlayer and start playback
    private void initializePlayer() {
        PlayerView playerView = findViewById(R.id.player_view);
        exoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);


        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(previewUrl));
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play();
    }

    // Release the ExoPlayer when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            exoPlayer.release(); // TODO silince noluyor?
            exoPlayer = null;
        }
    }

    // Pause the ExoPlayer when the activity is stopped
    @Override
    public void onStop() {
        super.onStop();
        if (exoPlayer != null) {
            exoPlayer.pause();
        }
    }

}
