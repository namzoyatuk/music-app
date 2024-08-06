package com.example.music_app.network.DTO;


import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Data
public class SearchAlbumDto implements Serializable {
    private List<AlbumDto> items;
}
