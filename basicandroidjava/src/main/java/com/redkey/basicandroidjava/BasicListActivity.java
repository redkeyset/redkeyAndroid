package com.redkey.basicandroidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.redkey.baselibrary.base.BaseMainListActivity;
import com.redkey.baselibrary.bean.classItemBean;
import com.redkey.baselibrary.comment.ArouterPers;
import com.redkey.baselibrary.comment.classDatas;
import com.redkey.basicandroidjava.comment.basicDatas;

import java.util.ArrayList;

/**
 * 罗列Android 基础部分 java版本
 */
@Route(path = ArouterPers.BasicAndroidLibs.BasicList)
public class BasicListActivity extends BaseMainListActivity {
    private basicDatas basicData;
    @Override
    public ArrayList<classItemBean> initData() {
        basicData = basicDatas.getInstance();
        return basicData.classItemBeans;
    }

    @Override
    public void initEvent() {

    }
}