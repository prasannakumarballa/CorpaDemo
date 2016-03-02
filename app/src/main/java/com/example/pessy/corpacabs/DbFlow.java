package com.example.pessy.corpacabs;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by pessy on 3/1/2016.
 */
public class DbFlow extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);

        DbModel dbmodel= new DbModel();
        dbmodel.setName("Hello");
        dbmodel.setEmail("Dabba@dabba.com");
        dbmodel.save();



    }

}
