package com.example.music_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music_app.adapter.MainAlbumAdapter;
import com.example.music_app.databinding.ActivityMainBinding;
import com.example.music_app.network.RetrofitClient;
import com.example.music_app.network.SpotifyService;
import com.example.music_app.repository.AlbumRepository;
import com.example.music_app.repository.SearchRepository;
import com.example.music_app.ui.SearchFragment;
import com.example.music_app.viewmodel.AlbumViewModel;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SearchRepository searchRepository;
    private MainAlbumAdapter mainAlbumAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SpotifyService spotifyService = RetrofitClient.getSpotifyService();
        searchRepository = SearchRepository.getInstance(spotifyService);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerViewAlbums = binding.recyclerViewAlbums;
        recyclerViewAlbums.setLayoutManager(gridLayoutManager);

        AlbumRepository albumRepository = AlbumRepository.getInstance(spotifyService);
        AlbumViewModel albumViewModel = new ViewModelProvider(this, new AlbumViewModel.Factory(albumRepository)).get(AlbumViewModel.class);

        mainAlbumAdapter = new MainAlbumAdapter(this);

        String[] albumIds = {"5ZICh7iFpmgreWvpU9Og4G","7cEfK4Fu6GRuo7G8yvOERi",
                            "4IUeKh2mYOX2njthGA8STM",
                            "2v9PjvIkQVnyQdtD1iQD7e","7mNNg5DVEW2SgBr4pGWAW6",
                            "1m07iv4leo4aoqFs60vHCK", "7IKUTIc9UWuVngyGPtqNHS",
                            "6GGkLeS1HTtr45DlF8YHYN"
                            };
        albumViewModel.getAlbums(Arrays.asList(albumIds)).observe(this, albums -> {
            if (albums != null) {
                mainAlbumAdapter.setAlbumList(albums);
                mainAlbumAdapter.notifyDataSetChanged();
            }
        });

        binding.recyclerViewAlbums.setAdapter(mainAlbumAdapter);

        SearchView searchView = binding.searchView; // TODO close on X clicked
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchSpotify(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {return false;
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

                binding.fragmentContainer.setVisibility(View.VISIBLE);
            }
        });
    }

}