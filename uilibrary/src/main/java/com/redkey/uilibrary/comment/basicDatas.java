package com.redkey.uilibrary.comment;

import com.redkey.baselibrary.bean.classItemBean;
import com.redkey.baselibrary.comment.ArouterPers;

import java.util.ArrayList;

/**
 * 初始化分类知识
 */
public class basicDatas {
    public ArrayList<classItemBean> classItemBeans = new ArrayList<>();

    private static basicDatas mSingleton = null;

    private basicDatas() {
        initData();
    }

    public static basicDatas getInstance() {
        if (mSingleton == null) {
            synchronized (basicDatas.class) {
                if (mSingleton == null) {
                    mSingleton = new basicDatas();
                }
            }
        }
        return mSingleton;
    }

    private void initData() {
        classItemBean BasicList = new classItemBean();
        BasicList.setActivityArouter(ArouterPers.UiLibs.UICustomCurve);
        BasicList.setClassName("自定义曲线");
        BasicList.setDescription("自定义曲线");
        BasicList.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        BasicList.setSelect(false);
        classItemBeans.add(BasicList);
    }
}
