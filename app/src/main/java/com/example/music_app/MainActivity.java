package com.example.music_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.music_app.databinding.ActivityMainBinding;
import com.example.music_app.ui.AlbumActivity;
import com.example.music_app.ui.ArtistActivity;
import com.example.music_app.ui.TrackActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonAlbum.setOnClickListener(v -> {
            startActivity(AlbumActivity.createIntent(this));
        });

        binding.buttonArtist.setOnClickListener(v -> {
            startActivity(ArtistActivity.createIntent(this));
        });

        binding.buttonTrack.setOnClickListener(v -> {
            startActivity(TrackActivity.createIntent(this));
        });
    }
}