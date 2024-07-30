//package com.example.music_app;
//
//import android.content.Context;
//
//import androidx.room.Room;
//import androidx.test.core.app.ApplicationProvider;
//import androidx.test.platform.app.InstrumentationRegistry;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static org.junit.Assert.*;
//
//import com.example.music_app.database.DAO.TrackDao;
//import com.example.music_app.database.MusicDatabase;
//import com.example.music_app.database.entitites.Track;
//
///**
// * Instrumented test, which will execute on an Android device.
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//@RunWith(AndroidJUnit4.class)
//public class DatabaseTest {
//    private MusicDatabase db;
//    private TrackDao trackDao;
//
//    @Before
//    public void createDb(){
//        db = Room.inMemoryDatabaseBuilder(
//                ApplicationProvider.getApplicationContext(),
//                MusicDatabase.class).build();
//        trackDao = db.trackDao();
//
//    }
//
//    @After
//    public void closeDb(){
//        db.close();
//    }
//
//
//    @Test
//    public void insertAndGetTrack(){
//        Track track = new Track();
//        track.setId("1");
//        track.setName("Test Track");
//        track.setHref("test.com");
//        track.setUri("testuri");
//        track.setPopularity(100);
//        track.setAlbumId("testAlbum");
//        track.setArtistIds("testArtist");
//        track.setDurationMs(1000);
//        track.setPreviewUrl("test.com");
//        track.setTrackNumber(1);
//        trackDao.insertTrack(track);
//
//        Track track2 = trackDao.getTrackById("1");
//        assertEquals(track2.getName(), "Test Track");
//    }
//
//
//}