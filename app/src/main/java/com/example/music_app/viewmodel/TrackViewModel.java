package com.example.music_app.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.music_app.network.DTO.TrackDto;
import com.example.music_app.repository.TrackRepository;

public class TrackViewModel extends ViewModel {
    private final TrackRepository trackRepository;

    public TrackViewModel(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public LiveData<TrackDto> getTrack(String trackId) {
        return trackRepository.getTrack(trackId);
    }


    public static class Factory implements ViewModelProvider.Factory {
        private final TrackRepository repository;

        public Factory(TrackRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            if (modelClass.isAssignableFrom(TrackViewModel.class)) {
                return (T) new TrackViewModel(repository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
