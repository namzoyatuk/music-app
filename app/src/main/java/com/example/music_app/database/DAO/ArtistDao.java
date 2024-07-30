package com.example.music_app.database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.music_app.database.entitites.Artist;

import java.util.List;

@Dao
public interface ArtistDao {
    @Insert
    void insert(Artist artist);

    @Query("SELECT * FROM artist WHERE id = :id")
    Artist getArtistById(String id);

    @Query("SELECT * FROM artist")
    List<Artist> getAllArtists();
}
