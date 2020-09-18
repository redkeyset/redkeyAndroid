package com.redkey.baselibrary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.redkey.baselibrary.R;
import com.redkey.baselibrary.comment.classDatas;
import com.redkey.baselibrary.bean.classItemBean;

import java.util.ArrayList;

public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.VH> {

    private ArrayList<classItemBean> classItemBeans = new ArrayList<>();

    public MainRecycleAdapter(ArrayList<classItemBean> classItemBeans) {
        this.classItemBeans.addAll(classItemBeans);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rv_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        classItemBean itemBean = classItemBeans.get(position);
        holder.title.setText("类别：" + itemBean.getDescription());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classItemBeans.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        public final TextView title;

        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tv_title);
        }
    }

    // ① 定义点击回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    // 事件回调监听
    private MainRecycleAdapter.OnItemClickListener onItemClickListener;

    // ② 定义一个设置点击监听器的方法
    public void setOnItemClickListener(MainRecycleAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
