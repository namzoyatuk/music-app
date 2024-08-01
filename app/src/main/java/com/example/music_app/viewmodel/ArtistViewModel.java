package com.example.music_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


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

    public static class Factory implements ViewModelProvider.Factory {
        private final ArtistRepository repository;

        public Factory(ArtistRepository repository) {
            this.repository = repository;
        }

        // TODO ?? what does this code do?
        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            if (modelClass.isAssignableFrom(ArtistViewModel.class)) {
                return (T) new ArtistViewModel(repository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
