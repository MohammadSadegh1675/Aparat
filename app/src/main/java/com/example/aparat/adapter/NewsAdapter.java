package com.example.aparat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.aparat.R;
import com.example.aparat.models.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends PagerAdapter {
    Context context;
    List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;

    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.pager_layout, null);
        AppCompatImageView img_pager = view.findViewById(R.id.img_pager);
        container.addView(view, 0);
        News news = newsList.get(position);
        Picasso.get().load(news.getIcon()).
                placeholder(R.drawable.loading).
                into(img_pager);
        return view;
    }
}
