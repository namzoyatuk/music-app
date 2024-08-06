package com.example.music_app.network.DTO;

import java.util.List;

import lombok.Data;
import lombok.Getter;


@Data
public class GetAlbumsDto {
    private List<AlbumDto> albums;
}
