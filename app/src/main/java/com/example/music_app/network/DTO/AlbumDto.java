package com.example.music_app.network.DTO;

import java.util.List;

import lombok.Getter;

@Getter
public class AlbumDto implements Searchable{
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
        return images.get(0).getUrl();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Album";
    }

    @Getter
    public static class AlbumTracks {
        private String href;
        private int limit;
        private String next;
        private int offset;
        private String previous;
        private int total;
        private List<TrackDto> items;
    }


}