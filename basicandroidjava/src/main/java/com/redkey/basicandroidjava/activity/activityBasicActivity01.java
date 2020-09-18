package com.redkey.basicandroidjava.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ecs.toolslibrary.LogUtil;
import com.redkey.baselibrary.comment.ArouterPers;
import com.redkey.basicandroidjava.R;

@Route(path = ArouterPers.BasicAndroidLibs.activityBasic)
public class activityBasicActivity01 extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 100;
    private Button mButton01, mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic01);
        initView();
    }

    private void initView() {
        mButton01 = (Button) findViewById(R.id.button01);
        mButton01.setOnClickListener(this);
        mButton01.setText("Activity使用及屏幕相关");
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(this);
        mBtnBack.setText("按返回键退到后台");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button01) {
            Intent intent = new Intent(this, activityDemonstrationActivity.class);
            intent.putExtra(activityDemonstrationActivity.INTENT_DATA, "Intent传递数据");
//            startActivity(intent);//不带返回值的跳转
            startActivityForResult(intent, REQUEST_CODE);
        } else if (view.getId() == R.id.btn_back) {
            Intent intent = new Intent(this, activityBackKeyActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.e("-----接收：" + data.getStringExtra(activityDemonstrationActivity.RESULT_CODE) + "RESULT_OK:" + resultCode + "---REQUEST_CODE:" + requestCode);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            String extra = data.getStringExtra(activityDemonstrationActivity.RESULT_CODE);
            Toast.makeText(this, "接收返回：" + extra, Toast.LENGTH_SHORT).show();
        }
    }
}