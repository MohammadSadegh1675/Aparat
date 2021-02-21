package com.example.aparat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aparat.R;
import com.example.aparat.activity.VideoPlayerActivity;
import com.example.aparat.models.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoVH> {
    List<Video> videoList;
    Context context;

    public VideoAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;

    }

    @NonNull
    @Override
    public VideoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_row, null);
        return new VideoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoVH holder, int position) {
        Video video = videoList.get(position);
        holder.txt_nameVideo.setText(video.getTitle());
        Picasso.get().load(video.getIcon()).placeholder(R.drawable.loading).into(holder.img_video);
        holder.card_videoRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_videoActivity = new Intent(context, VideoPlayerActivity.class);
                intent_videoActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent_videoActivity.putExtra("video", video);
                context.startActivity(intent_videoActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class VideoVH extends RecyclerView.ViewHolder {
        AppCompatTextView txt_nameVideo;
        AppCompatImageView img_video;
        CardView card_videoRow;

        public VideoVH(@NonNull View itemView) {
            super(itemView);
            txt_nameVideo = itemView.findViewById(R.id.txt_nameVideo);
            img_video = itemView.findViewById(R.id.img_video);
            card_videoRow = itemView.findViewById(R.id.card_videoRow);
        }
    }
}
