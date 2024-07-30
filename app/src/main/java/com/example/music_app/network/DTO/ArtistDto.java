package com.example.music_app.network.DTO;

import androidx.room.Dao;

import java.util.Dictionary;
import java.util.List;

import lombok.*;

@Getter
public class ArtistDto {

    private String id;
    private String name;
    private String type;
    private String href;
    private String uri;
    private List<ImageDto> images;
    private List<String> genres;
    private int popularity;

}
