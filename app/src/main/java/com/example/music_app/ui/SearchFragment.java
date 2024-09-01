package com.example.music_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.music_app.adapter.SearchAdapter;
import com.example.music_app.databinding.FragmentSearchBinding;
import com.example.music_app.network.DTO.SearchResponseDto;
import com.example.music_app.network.DTO.Searchable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class SearchFragment extends Fragment {
    private FragmentSearchBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment using view binding
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();// Return the root view of the binding
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize a list to hold search results
        List<Searchable> searchResults = new ArrayList<>();

        // Check if there are arguments passed to the fragment
        if (getArguments() != null) {
            // Retrieve the search response from the arguments
            SearchResponseDto searchResponse = (SearchResponseDto) getArguments().getSerializable("searchResults");
            if (searchResponse != null) {
                // Add track items to the search results if available
                if (searchResponse.getTracks() != null && searchResponse.getTracks().getItems() != null)
                    searchResults.addAll(searchResponse.getTracks().getItems());
                // Add album items to the search results if available
                if (searchResponse.getAlbums() != null && searchResponse.getAlbums().getItems() != null)
                    searchResults.addAll(searchResponse.getAlbums().getItems());
                // Add artist items to the search results if available
                if (searchResponse.getArtists() != null && searchResponse.getArtists().getItems() != null)
                    searchResults.addAll(searchResponse.getArtists().getItems());


            }
        }
        // Create an adapter for the search results and set it to the RecyclerView
        SearchAdapter searchAdapter = new SearchAdapter(getContext(), searchResults);
        binding.searchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.searchResults.setAdapter(searchAdapter);
    }
}
