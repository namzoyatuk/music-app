package com.example.music_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.music_app.network.DTO.ArtistDto;
import com.example.music_app.repository.ArtistRepository;

public class ArtistViewModel extends ViewModel {
    private final ArtistRepository artistRepository;

    public ArtistViewModel(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public LiveData<ArtistDto> getArtist(String artistId) {
        return artistRepository.getArtist(artistId);
    }
}
