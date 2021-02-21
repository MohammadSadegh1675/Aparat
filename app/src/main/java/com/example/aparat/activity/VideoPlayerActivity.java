package com.example.aparat.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.example.aparat.R;
import com.example.aparat.database.DatabaseVideo;
import com.example.aparat.databinding.ActivityVideoPlayerBinding;
import com.example.aparat.models.Video;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.material.snackbar.Snackbar;

public class VideoPlayerActivity extends AppCompatActivity {
    ActivityVideoPlayerBinding videoPlayerBinding;
    Video video;
    Bundle bundle;
    SimpleExoPlayer player;
    DatabaseVideo databaseVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoPlayerBinding = ActivityVideoPlayerBinding.inflate(getLayoutInflater());
        setContentView(videoPlayerBinding.getRoot());
        bundle = getIntent().getExtras();
        databaseVideo = DatabaseVideo.getInstance(getApplicationContext());
        video = bundle.getParcelable("video");
        videoPlayerBinding.txtShowTime.setText(video.getTime());
        videoPlayerBinding.txtDescription.setText(video.getDescription());
        videoPlayerBinding.txtTitle.setText(video.getTitle());

        videoPlayerBinding.imgBack.setOnClickListener(v -> finish());
        player = new SimpleExoPlayer.Builder(VideoPlayerActivity.this).build();
        videoPlayerBinding.videoExo.setPlayer(player);
        Uri uri = Uri.parse(video.getLink());
        MediaItem mediaItem = MediaItem.fromUri(uri);
        player.setMediaItem(mediaItem);
        player.getPlayWhenReady();
        player.prepare();

        boolean favorite = databaseVideo.iDao().isExists(Integer.parseInt(video.getId()));

        if (favorite) {
            videoPlayerBinding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
        } else {
            videoPlayerBinding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);

        }
        videoPlayerBinding.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean favorite = databaseVideo.iDao().isExists(Integer.parseInt(video.getId()));
                if (favorite) {
                    databaseVideo.iDao().delete(video);
                    videoPlayerBinding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                } else {
                    long result = databaseVideo.iDao().insert(video);
                    videoPlayerBinding.imgFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                    if (result > 0) {
                        Snackbar snackbar = Snackbar.make(v, "Add", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {
                        Snackbar snackbar = Snackbar.make(v, " not Add", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }


                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.stop(true);
        }
    }
}