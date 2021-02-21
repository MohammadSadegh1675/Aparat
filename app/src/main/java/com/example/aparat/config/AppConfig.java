package com.example.aparat.config;

import android.app.Application;

import androidx.multidex.MultiDex;

public class AppConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(getApplicationContext());
    }
}
