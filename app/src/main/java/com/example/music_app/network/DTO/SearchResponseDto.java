package com.example.music_app.network.DTO;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SearchResponseDto implements Serializable {
    SearchArtistDto artists;
    SearchAlbumDto albums;
    SearchTrackDto tracks;
}
