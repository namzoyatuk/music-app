package com.example.music_app.database.entitites;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "album")
public class Album {
    @PrimaryKey
    @NotNull
    private String id;
    private String albumType;
    private int totalTracks;
    private String href;
    private String imageId; // TODO image class may be required
    private String name;
    private String releaseDate;
    private String type;
    private String uri;
    private String artists; // TODO make these a list
    private String tracks;  // TODO make these a list
    private String genres;  // TODO make these a list
    private String label;
    private int popularity;
}
