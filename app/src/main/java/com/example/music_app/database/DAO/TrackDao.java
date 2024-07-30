package com.example.music_app.database.DAO;


import androidx.room.*;
import com.example.music_app.database.entitites.Track;
import java.util.List;

@Dao
public interface TrackDao {
    @Insert
    void insertTrack(Track track);

    @Query("SELECT * FROM track WHERE id = :id")
    Track getTrackById(String id);

    @Query("SELECT * FROM track")
    List<Track> getAllTracks();

}
