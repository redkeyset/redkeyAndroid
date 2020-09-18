package com.redkey.basicandroidjava.server;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.redkey.baselibrary.comment.ArouterPers;
import com.redkey.basicandroidjava.R;

/**
 * Server基础知识
 */
@Route(path = ArouterPers.BasicAndroidLibs.serverBasic)
public class serverBasicActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStartServer;
    private Button mBtnBingServer;
    private Button mBtnStopServer;
    private Button mBtnUnbingServer;
    private Switch mSwServer;
    private Button mBtnStartDown;
    private Button mBtnProgressServer;

    private boolean isConnection;
    private Button mBtnStopDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_basic);
        initView();
        initEvent();
    }

    private void initEvent() {
        boolean checked = mSwServer.isChecked();
        isShowBind(checked);
        mSwServer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isShowBind(b);
            }
        });
    }

    private void initView() {
        mSwServer = (Switch) findViewById(R.id.sw_server);

        mBtnStartServer = (Button) findViewById(R.id.btn_start_server);
        mBtnStartServer.setOnClickListener(this);
        mBtnStartServer.setText("启动Server");

        mBtnBingServer = (Button) findViewById(R.id.btn_bing_server);
        mBtnBingServer.setOnClickListener(this);
        mBtnBingServer.setText("绑定Server");

        mBtnUnbingServer = (Button) findViewById(R.id.btn_unbing_server);
        mBtnUnbingServer.setOnClickListener(this);
        mBtnUnbingServer.setText("解绑Server");

        mBtnStopServer = (Button) findViewById(R.id.btn_stop_server);
        mBtnStopServer.setOnClickListener(this);
        mBtnStopServer.setText("停止Server");

        mBtnStartDown = (Button) findViewById(R.id.btn_start_down);
        mBtnStartDown.setOnClickListener(this);
        mBtnStartDown.setText("开始计数");

        mBtnStopDown = (Button) findViewById(R.id.btn_stop_down);
        mBtnStopDown.setOnClickListener(this);
        mBtnStopDown.setText("取消计数");

        mBtnProgressServer = (Button) findViewById(R.id.btn_progress_server);
        mBtnProgressServer.setOnClickListener(this);
        mBtnProgressServer.setText("查询进度");
    }

    private void isShowBind(boolean b) {
        if (!b) {
            mBtnStartServer.setVisibility(View.VISIBLE);
            mBtnStopServer.setVisibility(View.VISIBLE);

            mBtnBingServer.setVisibility(View.GONE);
            mBtnUnbingServer.setVisibility(View.GONE);
            mBtnStartDown.setVisibility(View.GONE);
            mBtnProgressServer.setVisibility(View.GONE);
            mBtnStopDown.setVisibility(View.GONE);
        } else {
            mBtnStartServer.setVisibility(View.GONE);
            mBtnStopServer.setVisibility(View.GONE);

            mBtnBingServer.setVisibility(View.VISIBLE);
            mBtnUnbingServer.setVisibility(View.VISIBLE);
            mBtnStartDown.setVisibility(View.VISIBLE);
            mBtnProgressServer.setVisibility(View.VISIBLE);
            mBtnStopDown.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isConnection) {
            unbindService(connection);
            isConnection = false;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_start_server) {
            Intent startIntent = new Intent(this, MyService.class);
            startService(startIntent);
        } else if (view.getId() == R.id.btn_bing_server) {
            if (!isConnection) {
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
            } else {
                Toast.makeText(this, "已经启动绑定服务！", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.btn_unbing_server) {
            //多次解除绑定会报错
            if (isConnection) {
                unbindService(connection);
                isConnection = false;
            }
        } else if (view.getId() == R.id.btn_stop_server) {
            Intent stopIntent = new Intent(this, MyService.class);
            stopService(stopIntent);
            isConnection = false;
        } else if (view.getId() == R.id.btn_start_down) {
            mDownloadBinder.startDowndload();
        } else if (view.getId() == R.id.btn_stop_down) {
            mDownloadBinder.stopDowndload();
        } else if (view.getId() == R.id.btn_progress_server) {
            int progress = mDownloadBinder.getProgress();
            Toast.makeText(this, "计数！" + progress, Toast.LENGTH_SHORT).show();
        }
    }

    private MyService.DownloadBinder mDownloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnection = true;
            mDownloadBinder = (MyService.DownloadBinder) service;
            mDownloadBinder.startDowndload();
            mDownloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}