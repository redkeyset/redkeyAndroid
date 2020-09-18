package com.redkey.systemandroidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.redkey.baselibrary.base.BaseMainListActivity;
import com.redkey.baselibrary.bean.classItemBean;
import com.redkey.baselibrary.comment.ArouterPers;
import com.redkey.systemandroidjava.comment.basicDatas;

import java.util.ArrayList;

/**
 * 罗列Android 系统部分
 */
@Route(path = ArouterPers.SystemAndroidLibs.SystemList)
public class SystemListActivity extends BaseMainListActivity {
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