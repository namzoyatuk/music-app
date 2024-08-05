package com.example.music_app.network.DTO;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

@Getter
public class TrackDto implements Searchable, Serializable {
    private AlbumDto album;
    private List<ArtistDto> artists;
    private int durationMs;
    private String href;
    private String id;
    private boolean isPlayable;
    private String name;
    private int popularity;
    private String previewUrl;
    private int trackNumber;
    private String type;
    private String uri;
    private boolean isLocal;

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getImageUrl() {
        if (album == null || album.getImages().isEmpty()) {
            return null;
        }
        return album.getImages().get(0).getUrl();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Track";
    }

    @Override
    public String getId() {
        return id;
    }
}
