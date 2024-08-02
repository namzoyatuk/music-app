package com.example.music_app.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music_app.R;
import com.example.music_app.adapter.TrackAdapter;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.repository.TrackRepository;
import com.example.music_app.viewmodel.TrackViewModel;

public class TrackActivity extends AppCompatActivity {
    private TrackViewModel trackViewModel;
    private TrackAdapter trackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        // TODO binding..
        RecyclerView recyclerView = findViewById(R.id.recycler_track);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SpotifyService spotifyService = RetrofitClient.getSpotifyService();
        TrackRepository trackRepository = TrackRepository.getInstance(spotifyService);
        TrackViewModel.Factory factory = new TrackViewModel.Factory(trackRepository);
        trackViewModel = new ViewModelProvider(this, factory).get(TrackViewModel.class);

        String trackId = getIntent().getStringExtra("trackId");

        trackViewModel.getTrack(trackId).observe(this, track -> {
            trackAdapter = new TrackAdapter(this, track);
            recyclerView.setAdapter(trackAdapter);
        });
    }

    public static Intent createIntent(AppCompatActivity activity) {
        return new Intent(activity, TrackActivity.class);
    }
}
