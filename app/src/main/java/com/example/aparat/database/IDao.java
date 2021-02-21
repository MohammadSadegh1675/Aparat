package com.example.aparat.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.aparat.models.Video;

import java.util.List;

@Dao
public interface IDao {

    @Insert
    long insert(Video video);

    @Update
    void update(Video video);

    @Delete
    void delete(Video video);

    @Query("select * from tbl_video")
    List<Video> getVideoList();

    @Query("SELECT EXISTS(SELECT * FROM tbl_video WHERE id = :id)")
    boolean isExists(int id);

}
