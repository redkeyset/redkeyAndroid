package com.redkey.baselibrary.base;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }


    /**
     * 跳转Activity
     * @param packageName 应用程序包名
     * @param activityName Activity的 完整路径
     */
    protected void startMyActivity(String packageName, String activityName) {
        Intent intent = new Intent();
        intent.setClassName(packageName,activityName);
        startActivity(intent);
    }
}
