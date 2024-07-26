package com.example.music_app.network.DTO;

import java.util.List;

import lombok.Getter;

@Getter
public class AlbumDto {
    private String album_type;
    private int total_tracks;
    private List<String> available_markets;
    private String href;
    private String id;
    private List<ImageDto> images;
    private String name;
    private String release_date;
    private String release_date_precision;
    private String type;
    private String uri;
    private List<ArtistDto> artists;
    private TrackDto tracks;
    private List<String> genres;
    private String label;
    private int popularity;
}