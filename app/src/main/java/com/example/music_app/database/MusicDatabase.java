//package com.example.music_app.database;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//import com.example.music_app.database.DAO.*;
//import com.example.music_app.database.entitites.*;
//
//@Database(entities = { Album.class, Artist.class, Track.class}, version = 1, exportSchema = false)
//public abstract class MusicDatabase extends RoomDatabase {
//    public abstract TrackDao trackDao();
//    public abstract AlbumDao albumDao();
//    public abstract ArtistDao artistDao();
//
//    public static volatile MusicDatabase INSTANCE;
//
//    public static MusicDatabase getDatabase(final Context context)  {
//        if (INSTANCE == null) {
//            synchronized (MusicDatabase.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            MusicDatabase.class, "musicDatabase")
//                            .build();
//                }
//            }
//        }
//        return INSTANCE;
//    }
//}
