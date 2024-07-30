package com.example.music_app.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.music_app.network.DTO.AlbumDto;
import com.example.music_app.repository.AlbumRepository;

public class AlbumViewModel {
    private final AlbumRepository albumRepository;

    public AlbumViewModel(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public LiveData<AlbumDto> getAlbum(String albumId) {
        return albumRepository.getAlbum(albumId);
    }
}
