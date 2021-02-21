package com.example.aparat.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aparat.R;
import com.example.aparat.adapter.VideoAdapter;
import com.example.aparat.database.DatabaseVideo;
import com.example.aparat.databinding.FragmentFavoriteBinding;


public class FavoriteFragment extends Fragment {

    FragmentFavoriteBinding favoriteBinding;
    DatabaseVideo databaseVideo;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        favoriteBinding = FragmentFavoriteBinding.inflate(getLayoutInflater());
        databaseVideo = DatabaseVideo.getInstance(getActivity());

        favoriteBinding.recyclerFavorite.setAdapter(new VideoAdapter(getActivity(),
                databaseVideo.iDao().getVideoList()));
        favoriteBinding.recyclerFavorite.setLayoutManager(new GridLayoutManager(getActivity(),2));

//        favoriteBinding.imgNotFound.setVisibility(View.VISIBLE);
//        favoriteBinding.txtNotFound.setVisibility(View.VISIBLE);
        return favoriteBinding.getRoot();

    }
}