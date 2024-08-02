package com.example.music_app.repository;

import androidx.lifecycle.LiveData;

import com.example.music_app.network.DTO.Searchable;

public interface BaseRepository {
    public LiveData<Searchable> get (String id);
}
