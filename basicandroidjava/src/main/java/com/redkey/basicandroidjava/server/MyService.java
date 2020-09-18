package com.redkey.basicandroidjava.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ecs.toolslibrary.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.e("onBind---------");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.e("onUnbind---------");
        return super.onUnbind(intent);
    }

    private Timer mTimer;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e("onCreate---------");
        mTimer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.e("onStartCommand---------");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("onDestroy---------");
    }

    private DownloadBinder mBinder = new DownloadBinder();
    private int progress = 0;

    //绑定的服务有
    public class DownloadBinder extends Binder {
        public void startDowndload() {
            Log.e("MyService", "startDownload executed");
            mTimer.schedule(timerTask, 1000);
        }

        public void stopDowndload() {
            Log.e("MyService", "stopDowndload executed");
            mTimer.cancel();
        }

        public int getProgress() {
            Log.e("MyService", "getProgress executed");
            return progress;
        }
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            progress = progress + 1;
            LogUtil.e("--定时器：" + progress);
        }
    };
}
