package com.ecs.redkeyandroid;

import android.os.Bundle;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.redkey.baselibrary.adapter.MainRecycleAdapter;
import com.redkey.baselibrary.base.BaseMainListActivity;
import com.redkey.baselibrary.comment.classDatas;
import com.redkey.baselibrary.base.BaseActivity;
import com.redkey.baselibrary.bean.classItemBean;

import java.util.ArrayList;

/**
 * 罗列大的知识类目
 */
public class MainActivity extends BaseMainListActivity {
    private classDatas classData;

    @Override
    public ArrayList<classItemBean> initData() {
        classData = classDatas.getInstance();
        return classData.classItemBeans;
    }

    @Override
    public void initEvent() {

    }

}