package com.example.music_app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.repository.AlbumRepository;

import java.util.List;

public class AlbumViewModel extends ViewModel{
    private final AlbumRepository albumRepository;

    public AlbumViewModel(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public LiveData<AlbumDto> getAlbum(String albumId) {
        return albumRepository.getAlbum(albumId);
    }

    public LiveData<List<AlbumDto>> getAlbums(List<String> albumIds) {
        return albumRepository.getAlbums(albumIds);
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final AlbumRepository repository;

        public Factory(AlbumRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            if (modelClass.isAssignableFrom(AlbumViewModel.class)) {
                return (T) new AlbumViewModel(repository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
