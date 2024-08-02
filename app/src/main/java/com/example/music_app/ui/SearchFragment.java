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
    private SearchAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Searchable> searchResults = new ArrayList<>();
        if (getArguments() != null) {
            SearchResponseDto searchResponse = (SearchResponseDto) getArguments().getSerializable("searchResults");
            if (searchResponse != null) {
                if (searchResponse.getTracks() != null && searchResponse.getTracks().getItems() != null)
                    searchResults.addAll(searchResponse.getTracks().getItems());
                if (searchResponse.getAlbums() != null && searchResponse.getAlbums().getItems() != null)
                    searchResults.addAll(searchResponse.getAlbums().getItems());
                if (searchResponse.getArtists() != null && searchResponse.getArtists().getItems() != null)
                    searchResults.addAll(searchResponse.getArtists().getItems());


            }
        }

        searchAdapter = new SearchAdapter(getContext(), searchResults);
        binding.searchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.searchResults.setAdapter(searchAdapter);
    }
}
