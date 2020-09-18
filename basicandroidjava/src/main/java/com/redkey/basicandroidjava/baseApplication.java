package com.redkey.basicandroidjava;

import android.app.Application;
import android.content.Context;
public class baseApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = baseApplication.this;
    }


    public static Context getContext(){
        return mContext;
    }
}
