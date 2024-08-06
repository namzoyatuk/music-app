package com.example.music_app.network.DTO;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;

@Data
public class ImageDto implements Serializable {
    private int height;
    private int width;
    private String url;
}

