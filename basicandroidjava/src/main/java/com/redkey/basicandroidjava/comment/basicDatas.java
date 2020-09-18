package com.redkey.basicandroidjava.comment;

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
        BasicList.setActivityArouter(ArouterPers.BasicAndroidLibs.activityBasic);
        BasicList.setClassName("Activity");
        BasicList.setDescription("Activity及屏幕");
        BasicList.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        BasicList.setSelect(false);
        classItemBeans.add(BasicList);

        classItemBean intentBasic = new classItemBean();
        intentBasic.setActivityArouter(ArouterPers.BasicAndroidLibs.intentBasic);
        intentBasic.setClassName("intentBasic");
        intentBasic.setDescription("intent知识");
        intentBasic.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        intentBasic.setSelect(false);
        classItemBeans.add(intentBasic);

        classItemBean serverBasic = new classItemBean();
        serverBasic.setActivityArouter(ArouterPers.BasicAndroidLibs.serverBasic);
        serverBasic.setClassName("serverBasic");
        serverBasic.setDescription("Server知识");
        serverBasic.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        serverBasic.setSelect(false);
        classItemBeans.add(serverBasic);

        classItemBean lambdaBasic = new classItemBean();
        lambdaBasic.setActivityArouter(ArouterPers.BasicAndroidLibs.lambdaBasic);
        lambdaBasic.setClassName("Lambda表达式");
        lambdaBasic.setDescription("Lambda表达式");
        lambdaBasic.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        lambdaBasic.setSelect(false);
        classItemBeans.add(lambdaBasic);

        classItemBean audioBasic = new classItemBean();
        audioBasic.setActivityArouter(ArouterPers.BasicAndroidLibs.audioBasic);
        audioBasic.setClassName("Audio模式切换");
        audioBasic.setDescription("Audio模式切换");
        audioBasic.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        audioBasic.setSelect(false);
        classItemBeans.add(audioBasic);

        classItemBean ttsBasic = new classItemBean();
        ttsBasic.setActivityArouter(ArouterPers.BasicAndroidLibs.ttsBasic);
        ttsBasic.setClassName("TTS演示");
        ttsBasic.setDescription("TTS演示");
        ttsBasic.setIconUrl("https://static.oschina.net/uploads/img/201908/24215908_nWir.png");
        ttsBasic.setSelect(false);
        classItemBeans.add(ttsBasic);
    }
}
