package com.redkey.mqttlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.redkey.baselibrary.comment.ArouterPers;

@Route(path = ArouterPers.MqttLibs.MqttItem)
public class IpHostActivity extends AppCompatActivity {

    private TextView mTvMqttTitle;
    private LinearLayout mLine1;
    private EditText mIp;
    private EditText mPort;
    private EditText mSub;
    private EditText mName;
    private EditText mPsd;
    private EditText mEtSub01, mEtSub02;
    private Button mConnect;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_host);
        mContext = IpHostActivity.this;
        initView();
    }

    private void initView() {
        mTvMqttTitle = (TextView) findViewById(R.id.tv_mqtt_title);
        mLine1 = (LinearLayout) findViewById(R.id.line1);
        mIp = (EditText) findViewById(R.id.ip);
        mPort = (EditText) findViewById(R.id.port);
        mSub = (EditText) findViewById(R.id.sub);
        mName = (EditText) findViewById(R.id.name);
        mPsd = (EditText) findViewById(R.id.psd);
        mEtSub01 = (EditText) findViewById(R.id.et_sub01);
        mEtSub02 = (EditText) findViewById(R.id.et_sub02);
        mConnect = (Button) findViewById(R.id.connect);

        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentPars();
            }
        });

//        sentPars();
    }

    private void sentPars() {
        if (mIp.getText().toString().length() != 0 && mPort.getText().toString().length() != 0 &&
                mName.getText().toString().length() != 0 &&
                mPsd.getText().toString().length() != 0 &&
                mEtSub01.getText().toString().length() != 0 &&
                mEtSub02.getText().toString().length() != 0
        ) {
            Toast.makeText(mContext, "开始连接..", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(IpHostActivity.this, MqttClientActivity.class);
            intent.putExtra("ip", mIp.getText().toString());
            intent.putExtra("port", mPort.getText().toString());
            intent.putExtra("name", mName.getText().toString());
            intent.putExtra("psd", mPsd.getText().toString());
            intent.putExtra("etSub01", mEtSub01.getText().toString());
            intent.putExtra("etSub02", mEtSub02.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}