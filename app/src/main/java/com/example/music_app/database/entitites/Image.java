package com.example.music_app.database.entitites;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "image")
public class Image {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    private String id;
    private int height;
    private int width;
    private String url;
}
