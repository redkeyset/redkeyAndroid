package com.imooc.custom_view_library;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.imooc.custom_view_library.comment.basicDatas;
import com.redkey.baselibrary.base.BaseMainListActivity;
import com.redkey.baselibrary.bean.classItemBean;
import com.redkey.baselibrary.comment.ArouterPers;

import java.util.ArrayList;

@Route(path = ArouterPers.CustomViewLibs.CustomViewList)
public class CustomViewListActivity extends BaseMainListActivity {
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