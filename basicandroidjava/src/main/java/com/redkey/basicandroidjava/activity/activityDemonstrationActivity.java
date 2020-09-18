package com.redkey.basicandroidjava.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ecs.toolslibrary.LogUtil;
import com.redkey.basicandroidjava.R;

import java.lang.reflect.Method;

/**
 * 演示 activity 各个知识点
 * <p>
 * android开发---实现屏幕旋转的两种方法 https://blog.csdn.net/alearningWu/article/details/83751441?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param
 */
public class activityDemonstrationActivity extends AppCompatActivity {

    public static final String INTENT_DATA = "intent_data";
    public static final String RESULT_CODE = "result_code";
    private static final int PICK_CONTACT_REQUEST = 100;
    private static final String SAVE_STATE = "saveState";
    private RadioGroup mRgOrientation;
    private RadioButton mRbtn0;
    private RadioButton mRbtn90;
    private RadioButton mRbtn180;
    private RadioButton mRbtn270;
    private Button mBtnGetDirection;
    private TextView mTvShowDirection;
    private Button mBtnIntentData;

    /**
     * 保存Activity数据
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(SAVE_STATE, "要保存的数据");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demonstration);
        //恢复数据
        if (savedInstanceState != null) {
            Toast.makeText(this, "恢复：" + savedInstanceState.getString(SAVE_STATE), Toast.LENGTH_SHORT).show();
        }
        initView();
        initEvent();
    }


    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }

    /**
     * 接收返回值
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_CONTACT_REQUEST) {

        }
    }

    private void initView() {
        mRgOrientation = (RadioGroup) findViewById(R.id.rg_orientation);
        mRbtn0 = (RadioButton) findViewById(R.id.rbtn_0);
        mRbtn90 = (RadioButton) findViewById(R.id.rbtn_90);
        mRbtn180 = (RadioButton) findViewById(R.id.rbtn_180);
        mRbtn270 = (RadioButton) findViewById(R.id.rbtn_270);
        mBtnGetDirection = (Button) findViewById(R.id.btn_get_direction);
        mBtnGetDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int screenOrientation = getScreenOrientation();
                mTvShowDirection.setText("屏幕方向：" + screenOrientation);
            }
        });
        mTvShowDirection = (TextView) findViewById(R.id.tv_show_direction);
        mBtnIntentData = (Button) findViewById(R.id.btn_intent_data);

        mBtnIntentData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置返回的数据
                Intent intent = new Intent();
                intent.putExtra(RESULT_CODE, SystemClock.currentThreadTimeMillis() + "时间戳");
                LogUtil.e("-------------------" + SystemClock.currentThreadTimeMillis() + "时间戳");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initEvent() {
        // RadioGroup 设置选择监听
        mRgOrientation.setOnCheckedChangeListener(radioGrouplisten);

        //接收Intent 传递数值
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(INTENT_DATA);
        mBtnIntentData.setText("接收Intent传值：" + stringExtra);
    }

    /**
     * RadioGroup 监听
     */
    private RadioGroup.OnCheckedChangeListener radioGrouplisten = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int id = radioGroup.getCheckedRadioButtonId();
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.rbtn_0) {
            } else if (checkedRadioButtonId == R.id.rbtn_90) {
            } else if (checkedRadioButtonId == R.id.rbtn_180) {
            } else if (checkedRadioButtonId == R.id.rbtn_270) {
            } else if (checkedRadioButtonId == R.id.rbtn_landscape) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏设置
                LogUtil.e("横屏设置");
            } else if (checkedRadioButtonId == R.id.rbtn_portrait) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏设置
                LogUtil.e("竖屏设置");
            } else if (checkedRadioButtonId == R.id.rbtn_unspecified) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);//默认设置
                LogUtil.e("默认屏幕方向");
            }
        }
    };


    /**
     * 屏幕旋转变化时，进行监听处理
     * 在屏幕旋转之后，不想activity重新启动走生命周期方法，
     * 需要配置： android:configChanges="orientation|keyboardHidden|navigation"
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏
        } else {
            //竖屏
        }
    }


    /**
     * 获取屏幕方向
     *
     * @return
     */
    private int getScreenOrientation() {
        switch (getScreenRotation()) {
            case Surface.ROTATION_0:
                return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
            case Surface.ROTATION_90:
                return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            case Surface.ROTATION_180:
                return (Build.VERSION.SDK_INT >= 8 ? ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
                        : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            case Surface.ROTATION_270:
                return (Build.VERSION.SDK_INT >= 8 ? ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
                        : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            default:
                return 0;
        }
    }

    private int getScreenRotation() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        try {
            Method m = display.getClass().getDeclaredMethod("getRotation");
            return (Integer) m.invoke(display);
        } catch (Exception e) {
            return Surface.ROTATION_0;
        }
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