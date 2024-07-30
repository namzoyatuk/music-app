package com.example.music_app.database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.music_app.database.entitites.Album;

import java.util.List;

@Dao
public interface AlbumDao {
    @Insert
    void insertAlbum(Album album);

    @Query("SELECT * FROM album WHERE id = :id")
    Album getAlbumById(String id);

    @Query("SELECT * FROM album")
    List<Album> getAllAlbums();

}
