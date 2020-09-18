package com.imooc.custom_view_library.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.imooc.custom_view_library.R;
import com.redkey.baselibrary.comment.ArouterPers;

/**
 * 自定义View时钟控件
 */
@Route(path = ArouterPers.CustomViewLibs.CustomViewClock)
public class ClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
    }
}