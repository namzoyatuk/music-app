package com.example.music_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.repository.AlbumRepository;

public class AlbumViewModel extends ViewModel{
    private final AlbumRepository albumRepository;

    public AlbumViewModel(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public LiveData<AlbumDto> getAlbum(String albumId) {
        return albumRepository.getAlbum(albumId);
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final AlbumRepository repository;

        public Factory(AlbumRepository repository) {
            this.repository = repository;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            if (modelClass.isAssignableFrom(AlbumViewModel.class)) {
                return (T) new AlbumViewModel(repository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
