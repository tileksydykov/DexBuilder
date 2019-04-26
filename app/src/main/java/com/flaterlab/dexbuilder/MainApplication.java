package com.flaterlab.dexbuilder;

import android.app.Application;
import android.content.Context;

import com.github.florent37.androidnosql.AndroidNoSql;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        AndroidNoSql.initWithDefault(context);
    }
}
