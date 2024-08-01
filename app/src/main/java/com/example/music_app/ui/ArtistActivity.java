package com.example.music_app.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music_app.R;
import com.example.music_app.adapter.ArtistAdapter;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.repository.ArtistRepository;
import com.example.music_app.viewmodel.ArtistViewModel;

public class ArtistActivity extends AppCompatActivity {
    private ArtistViewModel artistViewModel;
    private ArtistAdapter artistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        RecyclerView recyclerView = findViewById(R.id.recycler_artist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SpotifyService spotifyService = RetrofitClient.getSpotifyService();
        ArtistRepository artistRepository = ArtistRepository.getInstance(spotifyService);
        ArtistViewModel.Factory factory = new ArtistViewModel.Factory(artistRepository);
        artistViewModel = new ViewModelProvider(this, factory).get(ArtistViewModel.class);

        artistViewModel.getArtist("4Z8W4fKeB5YxbusRsdQVPb").observe(this, artist -> {
            artistAdapter = new ArtistAdapter(this, artist);
            recyclerView.setAdapter(artistAdapter);
        });
    }

    public static Intent createIntent(AppCompatActivity activity) {
        return new Intent(activity, ArtistActivity.class);
    }
}
