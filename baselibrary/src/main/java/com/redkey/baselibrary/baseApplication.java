package com.redkey.baselibrary;

import android.app.Application;

import com.ecs.toolslibrary.LogUtil;

public class baseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        initLog();
    }

    private void initLog() {
        LogUtil.setAppName("kook");
        LogUtil.setPrintLine(true);
        LogUtil.setLogLevel(LogUtil.LOG_LEVEL_VERBOSE);
//        LogUtil.setLogLevel(LogUtil.LOG_LEVEL_NOLOG);//不打印log
    }
}
