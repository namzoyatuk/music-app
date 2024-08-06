package com.example.music_app.network.DTO;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class TopTrackResponseDto implements Serializable {
    private List<TrackDto> tracks;
}
