package com.example.music_app.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music_app.R;
import com.example.music_app.adapter.AlbumAdapter;
import com.example.music_app.adapter.AlbumTrackAdapter;
import com.example.music_app.databinding.ActivityAlbumBinding;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.repository.AlbumRepository;
import com.example.music_app.viewmodel.AlbumViewModel;

public class AlbumActivity extends AppCompatActivity {
    private AlbumViewModel albumViewModel;
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
        albumViewModel = new ViewModelProvider(this, factory).get(AlbumViewModel.class);

        albumViewModel.getAlbum("5ZICh7iFpmgreWvpU9Og4G").observe(this, album -> {
            albumAdapter = new AlbumAdapter(this, album);
            recyclerAlbum.setAdapter(albumAdapter);

            albumTrackAdapter = new AlbumTrackAdapter(this, album.getTracks().getItems());
            recyclerAlbumTrack.setAdapter(albumTrackAdapter);

        });
    }


    public static Intent createIntent(AppCompatActivity activity) {
        return new Intent(activity, AlbumActivity.class);
    }
}
