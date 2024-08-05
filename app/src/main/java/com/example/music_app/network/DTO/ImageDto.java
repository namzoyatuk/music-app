package com.example.music_app.network.DTO;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ImageDto implements Serializable {
    private int height;
    private int width;
    private String url;
}

