package com.redkey.uilibrary;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.redkey.baselibrary.base.BaseActivity;
import com.redkey.baselibrary.base.BaseMainListActivity;
import com.redkey.baselibrary.bean.classItemBean;
import com.redkey.baselibrary.comment.ArouterPers;
import com.redkey.uilibrary.comment.basicDatas;

import java.util.ArrayList;

@Route(path = ArouterPers.UiLibs.UIList)
public class UiListActivity extends BaseMainListActivity {
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