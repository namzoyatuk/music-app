package com.example.music_app.network.DTO;

import com.example.music_app.database.entitites.Image;

import java.util.List;

import lombok.Getter;

@Getter
public class SearchAlbumDto {
    private List<AlbumDto> items;
}
