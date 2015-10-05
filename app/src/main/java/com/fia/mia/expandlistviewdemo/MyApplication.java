package com.fia.mia.expandlistviewdemo;

import android.app.Application;
import android.content.Context;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class MyApplication extends Application {

    private static MyApplication appInstance;
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();

        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}