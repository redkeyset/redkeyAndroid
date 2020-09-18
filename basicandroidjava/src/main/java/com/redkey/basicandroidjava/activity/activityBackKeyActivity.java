package com.redkey.basicandroidjava.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.redkey.basicandroidjava.R;

public class activityBackKeyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_key);
    }

    //   按返回键 应用退到后台 ------------------------------------------------------------
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //按返回键 应用退到后台
        moveTaskToBack(true);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //按返回键 应用退到后台
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}