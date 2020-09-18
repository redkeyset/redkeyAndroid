package com.redkey.uilibrary.customcurve;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.redkey.baselibrary.comment.ArouterPers;
import com.redkey.uilibrary.R;
import com.redkey.uilibrary.customcurve.view.TableView;

import java.util.ArrayList;

@Route(path = ArouterPers.UiLibs.UICustomCurve)
public class CustomCurveActivity extends AppCompatActivity {

    private TableView mTabCurve;
    private ArrayList<Integer> timesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_curve);
        initData();
        initView();
    }

    private void initData() {
        setTimesPar(120);
    }

    private void setTimesPar(int time) {
        if (timesList == null) {
            timesList = new ArrayList<>();
        }

        timesList.clear();

        for (int i = 0; i <= time; i = i + 10) {
            timesList.add(i);
        }
    }

    private void initView() {
        mTabCurve = (TableView) findViewById(R.id.tab_curve);
        mTabCurve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTabCurve.showAnimator();
            }
        });
        // 初始化数据表格相关
        // 配置坐标系
        mTabCurve.setupCoordinator("时间", "浓度", /*这里是横坐标的值*/timesList);
        // 添加曲线, 确保纵坐标的数值位数相等
        mTabCurve.addWave(ContextCompat.getColor(this, R.color.colorPink), false,
                0f, 30f, 20f, 10f, 46f, 30f, 30f, 20f, 60f);
    }
}