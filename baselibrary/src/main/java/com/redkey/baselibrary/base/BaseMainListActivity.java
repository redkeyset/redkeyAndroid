package com.redkey.baselibrary.base;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.redkey.baselibrary.R;
import com.redkey.baselibrary.adapter.MainRecycleAdapter;
import com.redkey.baselibrary.bean.classItemBean;
import com.redkey.baselibrary.comment.classDatas;

import java.util.ArrayList;

public abstract class BaseMainListActivity extends BaseActivity {

    private RecyclerView rvMainList;
    private MainRecycleAdapter mainRecycleAdapter;
    public ArrayList<classItemBean> classItemBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<classItemBean> itemBeans = initData();
        if (null == itemBeans){
            finish();
            Toast.makeText(this,"数据异常，不能展示！",Toast.LENGTH_SHORT).show();
        }else {
            classItemBeans.addAll(itemBeans);
        }
        initView();
        initEvent();
    }

    public abstract ArrayList<classItemBean> initData();
    public abstract void initEvent();

    private void initView() {
        rvMainList = (RecyclerView) findViewById(R.id.rv_main_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        //设置布局管理器
        rvMainList.setLayoutManager(layoutManager);

        mainRecycleAdapter = new MainRecycleAdapter(classItemBeans);
        //设置Adapter
        rvMainList.setAdapter(mainRecycleAdapter);
        //设置分隔线
//        rvMainList.addItemDecoration(new DividerGridItemDecoration(this));
        //设置增加或删除条目的动画
        rvMainList.setItemAnimator(new DefaultItemAnimator());
        mainRecycleAdapter.setOnItemClickListener(new MainRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                classItemBean itemBean = classItemBeans.get(position);
                ARouter.getInstance().build(itemBean.getActivityArouter()).navigation();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }
}
