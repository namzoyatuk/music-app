package com.example.music_app.network.DTO;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

@Getter
public class AlbumDto implements Searchable, Serializable {
    private String album_type;
    private int total_tracks;
    private String href;
    private String id;
    private List<ImageDto> images;
    private String name;
    private String release_date;
    private String type;
    private String uri;
    private List<ArtistDto> artists;
    private List<String> genres;
    private String label;
    private int popularity;
    private AlbumTracks tracks;

    @Override
    public String getImageUrl() {
        return images != null && !images.isEmpty() ? images.get(0).getUrl() : null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Album";
    }

    @Override
    public String getId() {
        return id;
    }

    @Getter
    public static class AlbumTracks implements Serializable {
        private String href;
        private int limit;
        private String next;
        private int offset;
        private String previous;
        private int total;
        private List<TrackDto> items;
    }


}