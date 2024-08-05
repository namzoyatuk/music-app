package com.example.music_app.network.DTO;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

@Getter
public class SearchArtistDto implements Serializable {
    private List<ArtistDto> items;
}
