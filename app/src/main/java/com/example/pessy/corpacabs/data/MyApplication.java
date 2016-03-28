package com.example.pessy.corpacabs.data;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by pessy on 3/26/2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
