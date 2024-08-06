package com.example.music_app.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music_app.MainActivity;
import com.example.music_app.R;
import com.example.music_app.adapter.ArtistAdapter;
import com.example.music_app.adapter.ArtistTrackAdapter;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.repository.ArtistRepository;
import com.example.music_app.viewmodel.ArtistViewModel;

public class ArtistActivity extends AppCompatActivity {
    private ArtistAdapter artistAdapter;
    private ArtistTrackAdapter artistTrackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        RecyclerView recyclerView = findViewById(R.id.recycler_artist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView recyclerArtistTrack = findViewById(R.id.artist_tracks);
        recyclerArtistTrack.setLayoutManager(new LinearLayoutManager(this));

        SpotifyService spotifyService = RetrofitClient.getSpotifyService();
        ArtistRepository artistRepository = ArtistRepository.getInstance(spotifyService);
        ArtistViewModel.Factory factory = new ArtistViewModel.Factory(artistRepository);
        ArtistViewModel artistViewModel = new ViewModelProvider(this, factory).get(ArtistViewModel.class);

        String artistId = getIntent().getStringExtra("artistId");

        artistViewModel.getArtist(artistId).observe(this, artist -> {
            artistAdapter = new ArtistAdapter(this, artist);
            recyclerView.setAdapter(artistAdapter);

        });

        artistViewModel.getTopTracks(artistId).observe(this, tracks -> {
            artistTrackAdapter = new ArtistTrackAdapter(this, tracks);
            recyclerArtistTrack.setAdapter(artistTrackAdapter);
        });

        findViewById(R.id.button_home_artist).setOnClickListener(v -> {
            Intent intent = new Intent(ArtistActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

}
