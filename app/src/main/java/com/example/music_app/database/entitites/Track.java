package com.example.music_app.database.entitites;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "track")
public class Track {
    @PrimaryKey
    @NotNull
    private String id;
    private String name;
    private String albumId;
    private String artistIds; // TODO make these a list
    private int durationMs;
    private String href;
    private String previewUrl;
    private int popularity;
    private int trackNumber;
    private String type;
    private String uri;
    private boolean isLocal;


}
