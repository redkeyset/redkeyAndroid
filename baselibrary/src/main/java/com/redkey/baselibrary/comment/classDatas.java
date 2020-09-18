package com.redkey.baselibrary.comment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.redkey.baselibrary.bean.classItemBean;

import java.util.ArrayList;

/**
 * 初始化分类知识
 */
public class classDatas {
    public ArrayList<classItemBean> classItemBeans = new ArrayList<>();

    private static classDatas mSingleton = null;

    private classDatas() {
        initData();
    }

    public static classDatas getInstance() {
        if (mSingleton == null) {
            synchronized (classDatas.class) {
                if (mSingleton == null) {
                    mSingleton = new classDatas();
                }
            }
        }
        return mSingleton;
    }

    private void initData() {
        classItemBean BasicList = new classItemBean();
        BasicList.setActivityArouter(ArouterPers.BasicAndroidLibs.BasicList);
        BasicList.setClassName("BasicListActivity");
        BasicList.setDescription("Android基础知识区域");
        BasicList.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        BasicList.setSelect(false);
        classItemBeans.add(BasicList);

        classItemBean systemList = new classItemBean();
        systemList.setActivityArouter(ArouterPers.SystemAndroidLibs.SystemList);
        systemList.setClassName("SystemListActivity");
        systemList.setDescription("Android系统知识区域");
        systemList.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        systemList.setSelect(false);
        classItemBeans.add(systemList);

        classItemBean UiListItem = new classItemBean();
        UiListItem.setActivityArouter(ArouterPers.UiLibs.UIList);
        UiListItem.setClassName("UiListActivity");
        UiListItem.setDescription("UI展示区域");
        UiListItem.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        UiListItem.setSelect(false);
        classItemBeans.add(UiListItem);

        classItemBean MqttItem = new classItemBean();
        MqttItem.setActivityArouter(ArouterPers.MqttLibs.MqttItem);
        MqttItem.setClassName("MQtt");
        MqttItem.setDescription("MQtt客户端");
        MqttItem.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        MqttItem.setSelect(false);
        classItemBeans.add(MqttItem);

        classItemBean CustomViewItem = new classItemBean();
        CustomViewItem.setActivityArouter(ArouterPers.CustomViewLibs.CustomViewList);
        CustomViewItem.setClassName("自定义View");
        CustomViewItem.setDescription("自定义View");
        CustomViewItem.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        CustomViewItem.setSelect(false);
        classItemBeans.add(CustomViewItem);
    }
}
