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


        RecyclerView recyclerView = findViewById(R.id.recycler_track);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SpotifyService spotifyService = RetrofitClient.getSpotifyService();
        TrackRepository trackRepository = TrackRepository.getInstance(spotifyService);
        TrackViewModel.Factory factory = new TrackViewModel.Factory(trackRepository);
        TrackViewModel trackViewModel = new ViewModelProvider(this, factory).get(TrackViewModel.class);

        String trackId = getIntent().getStringExtra("trackId");

        trackViewModel.getTrack(trackId).observe(this, track -> {
            trackAdapter = new TrackAdapter(this, track);
            recyclerView.setAdapter(trackAdapter);
        });

        findViewById(R.id.button_home_track).setOnClickListener(v -> {
            Intent intent = new Intent(TrackActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        previewUrl = getIntent().getStringExtra("previewUrl");

        if (previewUrl != null) {
            initializePlayer();
        }
    }

    private void initializePlayer() {
        PlayerView playerView = findViewById(R.id.player_view);
        exoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);


        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(previewUrl));
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            exoPlayer.release(); // TODO silince noluyor?
            exoPlayer = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (exoPlayer != null) {
            exoPlayer.pause();
        }
    }

}
