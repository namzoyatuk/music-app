package com.example.music_app.network.DTO;


import java.io.Serializable;
import java.util.List;

import lombok.*;

@Data
public class ArtistDto implements Searchable, Serializable {

    private String id;
    private String name;
    private String type;
    private String href;
    private String uri;
    private List<ImageDto> images;
    private List<String> genres;
    private int popularity;

    @Override
    public String getImageUrl() {
        if (images.isEmpty()) {
            return null;
        }
        return images.get(0).getUrl();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "Artist";
    }

    @Override
    public String getId() {
        return id;
    }
}
