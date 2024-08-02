package com.example.music_app.network.DTO;

import java.util.List;

import lombok.Getter;

@Getter
public class TopTrackResponseDto {
    private List<TrackDto> tracks;
}
