package com.example.aparat.webservice;

import com.example.aparat.models.Category;
import com.example.aparat.models.IMessageListener;
import com.example.aparat.models.News;
import com.example.aparat.models.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebserviceCaller {

    IService iService;


    public WebserviceCaller() {

        iService = ApiClient.getClient().create(IService.class);
    }

    public void getNews(IMessageListener iMessageListener) {
        Call<List<News>> listCall = iService.getNews();
        listCall.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                if (response.body() != null) {
                    iMessageListener.onSuccess(response.body());
                } else {
                    iMessageListener.onFailure("Error");
                }


            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                iMessageListener.onFailure(t.getMessage().toString());

            }
        });

    }

    public void getBestVideos(IMessageListener iMessageListener) {
        Call<List<Video>> listCall = iService.getBestVideos();
        listCall.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.body() != null) {
                    iMessageListener.onSuccess(response.body());
                } else {
                    iMessageListener.onFailure("Error");
                }

            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                iMessageListener.onFailure(t.getMessage().toString());

            }
        });

    }

    public void getNewVideos(IMessageListener iMessageListener) {
        Call<List<Video>> listCall = iService.getNewVideos();
        listCall.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.body() != null) {
                    iMessageListener.onSuccess(response.body());
                } else {
                    iMessageListener.onFailure("Error");
                }

            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                iMessageListener.onFailure(t.getMessage().toString());

            }
        });

    }

    public void getSpecial(IMessageListener iMessageListener) {
        Call<List<Video>> listCall = iService.getSpecial();
        listCall.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.body() != null) {
                    iMessageListener.onSuccess(response.body());
                } else {
                    iMessageListener.onFailure("Error");
                }

            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                iMessageListener.onFailure(t.getMessage().toString());

            }
        });

    }

    public void getAllCategory(IMessageListener iMessageListener) {

        Call<List<Category>> listCategory = iService.getCategory();
        listCategory.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.body() != null) {
                    iMessageListener.onSuccess(response.body());
                } else {
                    iMessageListener.onFailure("Error");
                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                iMessageListener.onFailure(t.getMessage().toString());

            }
        });
    }


    public void getCategoryVideo(String catId,IMessageListener iMessageListener) {
        Call<List<Video>> listCall = iService.getCategoryVideo(catId);
        listCall.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.body() != null) {
                    iMessageListener.onSuccess(response.body());
                } else {
                    iMessageListener.onFailure("Error");
                }

            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                iMessageListener.onFailure(t.getMessage().toString());

            }
        });

    }
}


