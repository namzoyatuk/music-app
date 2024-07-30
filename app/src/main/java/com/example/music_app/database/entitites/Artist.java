package com.example.music_app.database.entitites;

import androidx.core.app.NotificationCompat;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "artist")
public class Artist {
    @PrimaryKey
    @NotNull
    private String id;
    private String name;
    private String type;
    private String href;
    private String uri;
    private String image; // TODO make these a list
    private String genres; // TODO make these a list
    private int popularity;
}
