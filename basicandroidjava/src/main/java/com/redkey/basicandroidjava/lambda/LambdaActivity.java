package com.redkey.basicandroidjava.lambda;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.redkey.baselibrary.comment.ArouterPers;
import com.redkey.basicandroidjava.R;

@Route(path = ArouterPers.BasicAndroidLibs.lambdaBasic)
public class LambdaActivity extends AppCompatActivity {

    private Button mBtnTest01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);
        initView();
    }

    private void initView() {
        mBtnTest01 = (Button) findViewById(R.id.btn_test01);
        mBtnTest01.setOnClickListener(event -> {
            Toast.makeText(this, "点击按钮 Lambda表达式", Toast.LENGTH_SHORT).show();
        });
    }
}