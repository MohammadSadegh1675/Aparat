package com.example.aparat.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.aparat.models.Video;

@Database(entities = {Video.class}, version = 1, exportSchema = true)
public abstract class DatabaseVideo extends RoomDatabase {

    public abstract IDao iDao();

    private static DatabaseVideo instance = null;

    public static synchronized DatabaseVideo getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, DatabaseVideo.class, "videoDB").
                    allowMainThreadQueries().build();
        }
        return instance;
    }

}
