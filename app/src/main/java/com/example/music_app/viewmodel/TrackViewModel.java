package com.example.music_app.viewmodel;

import androidx.lifecycle.LiveData;

import com.example.music_app.network.DTO.TrackDto;
import com.example.music_app.repository.TrackRepository;

public class TrackViewModel {
    private final TrackRepository trackRepository;

    public TrackViewModel(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public LiveData<TrackDto> getTrack(String trackId) {
        return trackRepository.getTrack(trackId);
    }
}
