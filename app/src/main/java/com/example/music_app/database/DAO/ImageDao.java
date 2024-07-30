package com.example.music_app.database.DAO;

import com.example.music_app.database.entitites.Image;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface ImageDao {
    @Insert
    void insertImage(Image image);

    @Query("SELECT * FROM image WHERE id = :id")
    Image getImageById(String id);

    @Query("SELECT * FROM image")
    List<Image> getAllImages();

    @Update
    void updateImage(Image image);

    @Delete
    void deleteImage(Image image);
}
