package com.example.aparat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;


import com.example.aparat.adapter.VideoAdapter;
import com.example.aparat.databinding.ActivityVideoCategoryBinding;
import com.example.aparat.models.Category;
import com.example.aparat.models.IMessageListener;

import com.example.aparat.models.Video;
import com.example.aparat.webservice.WebserviceCaller;

import java.util.List;

public class VideoCategoryActivity extends AppCompatActivity {
    ActivityVideoCategoryBinding categoryBinding;
    Bundle bundle;
    Category category;
    WebserviceCaller webserviceCaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryBinding = ActivityVideoCategoryBinding.inflate(getLayoutInflater());
        setContentView(categoryBinding.getRoot());
        bundle = getIntent().getExtras();
        category = bundle.getParcelable("category");

        webserviceCaller = new WebserviceCaller();

        categoryBinding.txtTitle.setText(category.getTitle());

        categoryBinding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        categoryBinding.progress.setVisibility(View.VISIBLE);
        webserviceCaller.getCategoryVideo(category.getId(), new IMessageListener() {
            @Override
            public void onSuccess(Object responseMessage) {

                VideoAdapter videoAdapter = new VideoAdapter(getApplicationContext(), (List<Video>) responseMessage);
                categoryBinding.recyclerView.setAdapter(videoAdapter);
                categoryBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
                categoryBinding.progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Object errorResponseMessage) {
                categoryBinding.progress.setVisibility(View.VISIBLE);

            }
        });
    }
}