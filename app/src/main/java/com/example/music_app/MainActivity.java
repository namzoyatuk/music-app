package com.example.music_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.music_app.databinding.ActivityMainBinding;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.repository.SearchRepository;
import com.example.music_app.ui.AlbumActivity;
import com.example.music_app.ui.ArtistActivity;
import com.example.music_app.ui.SearchFragment;
import com.example.music_app.ui.TrackActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SearchRepository searchRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonAlbum.setOnClickListener(v -> {
            Intent intent = new Intent(this, AlbumActivity.class);
            intent.putExtra("albumId", "5ZICh7iFpmgreWvpU9Og4G");
            this.startActivity(intent);
        });

        binding.buttonArtist.setOnClickListener(v -> {
            Intent intent = new Intent(this, ArtistActivity.class);
            intent.putExtra("artistId", "4Z8W4fKeB5YxbusRsdQVPb");
            this.startActivity(intent);
        });

        binding.buttonTrack.setOnClickListener(v -> {
            Intent intent = new Intent(this, TrackActivity.class);
            intent.putExtra("trackId", "3gDwQ7TtXVOhtTy83H2ysl");
            this.startActivity(intent);
        });


        SpotifyService spotifyService = RetrofitClient.getSpotifyService();
        searchRepository = SearchRepository.getInstance(spotifyService);

        SearchView searchView = binding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchSpotify(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void searchSpotify(String query) {
        Log.e("MainActivity", "searchSpotify: " + query);
        searchRepository.search(query, "album,track,artist").observe(this, searchResponse -> {
            if (searchResponse != null) {
                Log.e("MainActivity", "searchSpotify: " + searchResponse.getAlbums().getItems().size());
                Bundle bundle = new Bundle();
                bundle.putSerializable("searchResults", searchResponse);

                SearchFragment searchFragment = new SearchFragment();
                searchFragment.setArguments(bundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, searchFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                hideButtons();
            }
        });
    }

    private void hideButtons() {
        binding.buttonAlbum.setVisibility(View.GONE);
        binding.buttonArtist.setVisibility(View.GONE);
        binding.buttonTrack.setVisibility(View.GONE);
    }
}