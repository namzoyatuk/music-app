package com.example.music_app.network.DTO;

import java.util.List;

import lombok.Getter;

@Getter
public class TrackDto {
    private AlbumDto album;
    private List<ArtistDto> artists;
    private List<String> available_markets;
    private int disc_number;
    private int duration_ms;
    private boolean explicit;
    private String href;
    private String id;
    private boolean is_playable;
    private String name;
    private int popularity;
    private String preview_url;
    private int track_number;
    private String type;
    private String uri;
    private boolean is_local;

}
