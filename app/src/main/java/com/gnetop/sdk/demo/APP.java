package com.gnetop.sdk.demo;

import android.app.Application;
import android.support.multidex.MultiDex;


public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
