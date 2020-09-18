package com.redkey.basicandroidjava.intent;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ecs.toolslibrary.LogUtil;
import com.redkey.baselibrary.comment.ArouterPers;
import com.redkey.basicandroidjava.R;

import java.util.Calendar;
import java.util.List;

/**
 * 通用Intent https://www.jianshu.com/p/910108cc837b
 */
@Route(path = ArouterPers.BasicAndroidLibs.intentBasic)
public class intentBasicActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnCreateAlarm;
    private Button mBtnAddEvent;
    private CalendarView mCalendarView;
    private Calendar calendar;
    private Button mBtnPhone;
    private Button mBtnOpenWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_basic);
        initData();
        initView();
    }

    private void initData() {
        List<ResolveInfo> browserList = getBrowserList(this);
        for (ResolveInfo item :
                browserList) {
            LogUtil.e("浏览器：" + item.toString());
        }
    }

    /**
     * 获取浏览器列表
     *
     * @param context
     * @return
     */
    public static List<ResolveInfo> getBrowserList(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://"));
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);

        return activities;
    }

    private void initView() {
        mBtnCreateAlarm = (Button) findViewById(R.id.btn_create_alarm);
        mBtnCreateAlarm.setOnClickListener(this);
        mBtnCreateAlarm.setText("创建闹钟");

        mBtnAddEvent = (Button) findViewById(R.id.btn_add_event);
        mBtnAddEvent.setOnClickListener(this);
        mBtnAddEvent.setText("添加日历事件");

        //Android中关于Calendar的一些用法 https://www.jianshu.com/p/9a9d62c9c926
        calendar = Calendar.getInstance();
        calendar.set(2020, 7, 10, 12, 10);

        mBtnPhone = (Button) findViewById(R.id.btn_phone);
        mBtnPhone.setOnClickListener(this);
        mBtnPhone.setText("拨打电话");

        mBtnOpenWeb = (Button) findViewById(R.id.btn_open_web);
        mBtnOpenWeb.setOnClickListener(this);
        mBtnOpenWeb.setText("打开百度网页");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_phone) {
            // 调用拨打电话，给10010拨打电话
            Uri uri = Uri.parse("tel:10010");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        } else if (view.getId() == R.id.btn_open_web) {
            // 打开百度主页
            Uri uri = Uri.parse("https://www.baidu.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addCategory("com.android.chrome");
            startActivity(intent);
        }
    }

    /**
     * 创建 闹铃 需要配置权限（不需要动态申请）
     * <uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
     *
     * @param message
     * @param hour
     * @param minutes
     */
    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message) //用于标识闹钟的自定义消息
                .putExtra(AlarmClock.EXTRA_HOUR, hour)  //闹钟的小时
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);   //闹钟的分钟
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);    //确认存在对应Activity组件再开启
        }
    }

    /**
     * 添加日历事件
     *
     * @param title
     * @param location
     * @param begin
     * @param end
     */
    public void addEvent(String title, String location, Calendar begin, Calendar end) {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)  //指定内容URI
                .putExtra(CalendarContract.Events.TITLE, title)  //事件标题
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)  //事件地点
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)  //事件的开始时间（从新纪年开始计算的毫秒数）
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);  //事件的结束时间（从新纪年开始计算的毫秒数）
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);  //确认存在对应Activity组件再开启
        }
    }
}