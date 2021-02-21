package com.example.aparat.webservice;

import com.example.aparat.models.Category;
import com.example.aparat.models.News;
import com.example.aparat.models.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IService {

    @GET("getNewVideos.php")
    Call<List<Video>> getNewVideos();

    @GET("getBestVideos.php")
    Call<List<Video>> getBestVideos();

    @GET("getCategory.php")
    Call<List<Category>> getCategory();

    @GET("getSpecial.php")
    Call<List<Video>> getSpecial();

    @GET("getNews.php")
    Call<List<News>> getNews();

    @FormUrlEncoded
    @POST("getVideosCategory.php")
    Call <List<Video>> getCategoryVideo(@Field("catId") String catId);


}
