package com.example.aparat.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aparat.R;
import com.example.aparat.adapter.NewsAdapter;
import com.example.aparat.adapter.VideoAdapter;
import com.example.aparat.databinding.FragmentHomeBinding;
import com.example.aparat.models.IMessageListener;
import com.example.aparat.models.News;
import com.example.aparat.models.Video;
import com.example.aparat.webservice.ApiClient;
import com.example.aparat.webservice.IService;
import com.example.aparat.webservice.WebserviceCaller;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    FragmentHomeBinding homeBinding;
    WebserviceCaller webserviceCaller;
    private static int PAGES = 0;
    private static int THIS_PAGE = 0;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        homeBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        webserviceCaller = new WebserviceCaller();
        homeBinding.progress.setVisibility(View.VISIBLE);
        webserviceCaller.getBestVideos(new IMessageListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                VideoAdapter videoAdapter = new VideoAdapter(getActivity(), (List<Video>) responseMessage);
                homeBinding.recyclerBest.setAdapter(videoAdapter);
                homeBinding.recyclerBest.setLayoutManager(new LinearLayoutManager(getActivity(),
                        RecyclerView.HORIZONTAL, false));
                homeBinding.progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Object errorResponseMessage) {
                homeBinding.progress.setVisibility(View.VISIBLE);

            }
        });
        webserviceCaller.getNewVideos(new IMessageListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                VideoAdapter videoAdapter = new VideoAdapter(getActivity(), (List<Video>) responseMessage);
                homeBinding.recyclerNewVideo.setAdapter(videoAdapter);
                homeBinding.recyclerNewVideo.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                homeBinding.progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Object errorResponseMessage) {
                homeBinding.progress.setVisibility(View.VISIBLE);

            }
        });
        webserviceCaller.getSpecial(new IMessageListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                VideoAdapter videoAdapter = new VideoAdapter(getActivity(), (List<Video>) responseMessage);
                homeBinding.recyclerSpecial.setAdapter(videoAdapter);
                homeBinding.recyclerSpecial.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                homeBinding.progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Object errorResponseMessage) {
                homeBinding.progress.setVisibility(View.VISIBLE);

            }
        });


        webserviceCaller.getNews(new IMessageListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                List<News> newsList = (List<News>) responseMessage;
                homeBinding.pagerNews.setAdapter(new NewsAdapter(getActivity(), newsList));
                homeBinding.progress.setVisibility(View.GONE);
                final Handler handler = new Handler();
                PAGES = newsList.size();
                final Runnable Update = new Runnable() {
                    public void run() {
                        if (THIS_PAGE == PAGES) {
                            THIS_PAGE = 0;
                        }
                        homeBinding.pagerNews.setCurrentItem(THIS_PAGE++, true);
                    }
                };
                Timer swipeTimer = new Timer();
                swipeTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(Update);
                    }
                }, 7000, 7000);

            }

            @Override
            public void onFailure(Object errorResponseMessage) {
                homeBinding.progress.setVisibility(View.VISIBLE);

            }
        });

        return homeBinding.getRoot();
    }
}