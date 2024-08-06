package com.example.music_app.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music_app.MainActivity;
import com.example.music_app.R;
import com.example.music_app.adapter.AlbumAdapter;
import com.example.music_app.adapter.AlbumTrackAdapter;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.repository.AlbumRepository;
import com.example.music_app.viewmodel.AlbumViewModel;

public class AlbumActivity extends AppCompatActivity {
    private AlbumAdapter albumAdapter;
    private AlbumTrackAdapter albumTrackAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        RecyclerView recyclerAlbum = findViewById(R.id.recycler_album);
        recyclerAlbum.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView recyclerAlbumTrack = findViewById(R.id.album_tracks);
        recyclerAlbumTrack.setLayoutManager(new LinearLayoutManager(this));


        SpotifyService spotifyService = RetrofitClient.getSpotifyService();
        AlbumRepository albumRepository = AlbumRepository.getInstance(spotifyService);
        AlbumViewModel.Factory factory = new AlbumViewModel.Factory(albumRepository);
        AlbumViewModel albumViewModel = new ViewModelProvider(this, factory).get(AlbumViewModel.class);

        String albumId = getIntent().getStringExtra("albumId");

        albumViewModel.getAlbum(albumId).observe(this, album -> {
            albumAdapter = new AlbumAdapter(this, album);
            recyclerAlbum.setAdapter(albumAdapter);

            albumTrackAdapter = new AlbumTrackAdapter(this, album.getTracks().getItems());
            recyclerAlbumTrack.setAdapter(albumTrackAdapter);

        });

        findViewById(R.id.button_home_album).setOnClickListener(v -> {
            Intent intent = new Intent(AlbumActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }


}
