package com.example.music_app.network.DTO;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponseDto implements Serializable {
    SearchArtistDto artists;
    SearchAlbumDto albums;
    SearchTrackDto tracks;
}
