package com.flaterlab.dexbuilder;

import android.app.Application;
import android.content.Context;

import io.paperdb.Paper;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        Paper.init(context);
    }
}
