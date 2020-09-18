package com.redkey.mqttlibrary;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ecs.toolslibrary.LogUtil;
import com.redkey.mqttlibrary.service.MyMqttService;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;

public class MqttClientActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mLin1;
    private TextView mRece;
    private Button mBtnConningSuccess;
    private Button mBtnConningFailed;
    private EditText mInput;
    private Button mWarn;
    private Button mWarn02;
    private TextView mTvShow;
    private String ip, port, name, psd, sub, etSub01, etSub02;
    private String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt_client);
        initData();
        initView();
        iniEvent();
    }

    private void initData() {
        psd = getIntent().getStringExtra("psd");
        name = getIntent().getStringExtra("name");
        ip = getIntent().getStringExtra("ip");
        port = getIntent().getStringExtra("port");
        etSub01 = getIntent().getStringExtra("etSub01");
        etSub02 = getIntent().getStringExtra("etSub02");
    }

    private void iniEvent() {
        GetIMEI();
    }

    @SuppressLint("HardwareIds")
    private void GetIMEI() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            LogUtil.d("请求获取手机状态权限");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 2);
        } else {
            LogUtil.d("有手机状态权限，获取IMEI");
            TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            assert mTelephony != null;
            if (mTelephony.getDeviceId() != null) {
                imei = mTelephony.getDeviceId();
            } else {
                imei = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            LogUtil.e("本机IMEI:" + imei);
            if (imei != null) {
                buildEasyMqttService();
                connect();
            }
        }
    }

    private void initView() {
        mLin1 = (LinearLayout) findViewById(R.id.lin1);
        mRece = (TextView) findViewById(R.id.rece);
        mBtnConningSuccess = (Button) findViewById(R.id.btn_conning_success);
        mBtnConningFailed = (Button) findViewById(R.id.btn_conning_failed);
        mInput = (EditText) findViewById(R.id.input);
        mWarn = (Button) findViewById(R.id.warn);
        mWarn.setText(etSub01);
        mWarn02 = (Button) findViewById(R.id.warn02);
        mWarn02.setText(etSub02);
        mTvShow = (TextView) findViewById(R.id.tv_show);

        mBtnConningSuccess.setOnClickListener(this);
        mBtnConningSuccess.setVisibility(View.GONE);
        mBtnConningFailed.setOnClickListener(this);
        mBtnConningFailed.setVisibility(View.GONE);
        mWarn.setOnClickListener(this);
        mWarn02.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_conning_success) {
            //连接成功
            disconnect();
            mBtnConningSuccess.setVisibility(View.GONE);
            mBtnConningFailed.setVisibility(View.VISIBLE);

        } else if (view.getId() == R.id.btn_conning_failed) {
            //链接失败
            GetIMEI();
        } else if (view.getId() == R.id.warn) {
            //向主题1发送
            sub = etSub01;
            sentMesToMqtt();

        } else if (view.getId() == R.id.warn02) {
            //向主题2发送
            sub = etSub02;
            sentMesToMqtt();
        }
    }


    private StringBuffer strContent;

    private void sentMesToMqtt() {
        if (isConnected()) {
//            subscribe();// 订阅主题

            //输入的内容
            String msgContent = mInput.getText().toString().trim();
            if (msgContent.isEmpty()) {
                Toast.makeText(this, "要发送的消息不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }

            //主题
            String topic = this.sub;
            //策略
            int qos = 0;
            //????
            boolean retained = false;
            //????
            publish(msgContent, topic, qos, retained);
//                    input.setText("");
            strContent.append("发送：" + sub + "\n" + msgContent + "\n");
            mTvShow.setText(strContent);
        } else {
            Toast.makeText(MqttClientActivity.this, "连接已断开！", Toast.LENGTH_SHORT).show();
        }
    }

    // 按格式替换 内同 发送 拼接Json 信息
    private void sentMesToMqttBeiFen() {
        if (isConnected()) {
//            subscribe();// 订阅主题

            //输入的内容
            String msgContent = mInput.getText().toString().trim();
            if (msgContent.isEmpty()) {
                Toast.makeText(this, "要发送的消息不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }

            String msg = "{\n" +
                    "  \"userId\":\"32455fc3-ae7d-4362-a07c-5398c5132533\",\n" +
                    "  \"proKey\":\"0027cdb3-2b44-4803-b58e-7af28c5184f8\",\n" +
                    "  \"content\":{\n" +
                    "        \"type\":\"audio\",\n" +
                    "        \"info\":{\n" +
                    "            \"msg_content\": \"" + msgContent + "\",\n" +
                    "            \"msg_id\":\"123456\",\n" +
                    "            \"voice_speed\": \"50\"\n" +
                    "        },\n" +
                    "        \"cirInfo\":{\n" +
                    "            \"cirInfo_content\":\"替换\",\n" +
                    "            \"interTime\":\"5\",\n" +
                    "            \"brodTime\":\"10\",\n" +
                    "            \"voice_speed\":\"50\",\n" +
                    "            \"stop\":\"\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "  \"topicName\":\"abcd\",\n" +
                    "  \"sign\":\"0D95B7BC011A94DE0D0BC0900BD60FC5\"\n" +
                    "}";
            if (msg.length() == 0) {
                Toast.makeText(MqttClientActivity.this, "empty msg!", Toast.LENGTH_SHORT).show();
                return;
            }
            //主题
            String topic = this.sub;
            //策略
            int qos = 0;
            //????
            boolean retained = false;
            //????
            publish(msg, topic, qos, retained);
//                    input.setText("");
            mTvShow.setText("???" + msg);
        } else {
            Toast.makeText(MqttClientActivity.this, "连接已断开！", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2) {
            TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            assert mTelephony != null;
            if (mTelephony.getDeviceId() != null) {
                imei = mTelephony.getDeviceId();
            } else {
                imei = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            buildEasyMqttService();
            connect();
        } else {
            Toast.makeText(this, "??????????", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 判断连接是否断开
     */
    private boolean isConnected() {
        return mqttService.isConnected();
    }

    /**
     * 发布消息
     */
    private void publish(String msg, String topic, int qos, boolean retained) {
        mqttService.publish(msg, topic, qos, retained);
    }

    /**
     * 断开连接
     */
    private void disconnect() {
        mqttService.disconnect();
    }

    /**
     * 关闭客户端
     */
    private void close() {
        mqttService.close();
    }

    private void subscribe(String sub) {
        String[] topics = new String[]{sub};
        int[] qoss = new int[]{0};
        mqttService.subscribe(topics, qoss);
    }

    private void connect() {
        mqttService.connect(new IEasyMqttCallBack() {
            @Override
            public void messageArrived(String topic, String message, int qos) {
                LogUtil.e("主题： " + topic + "---消息：" + message);

                strContent.append("收到:" + message + '\n' + "主题--> " + topic + "\n");
                mTvShow.setText(strContent);
            }

            @Override
            public void connectionLost(Throwable arg0) {
                try {
                    LogUtil.e(arg0.toString());
                    Toast.makeText(MqttClientActivity.this, "连接断开！", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {

                } finally {

                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken arg0) {
                Toast.makeText(MqttClientActivity.this, "传送完成！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void connectSuccess(IMqttToken arg0) {
                Toast.makeText(MqttClientActivity.this, "连接成功！", Toast.LENGTH_LONG).show();
                subscribe("kook01");
                subscribe("kook02");
                mBtnConningSuccess.setVisibility(View.VISIBLE);
                mBtnConningFailed.setVisibility(View.GONE);
                strContent = new StringBuffer();
            }

            @Override
            public void connectFailed(IMqttToken arg0, Throwable arg1) {
                Toast.makeText(MqttClientActivity.this, "连接失败！", Toast.LENGTH_SHORT).show();
                mBtnConningSuccess.setVisibility(View.GONE);
                mBtnConningFailed.setVisibility(View.VISIBLE);
            }
        });
    }

    private MyMqttService mqttService;

    private void buildEasyMqttService() {
        mqttService = new MyMqttService.Builder()
                .autoReconnect(true)
                .cleanSession(true)
                .clientId(imei)
                .userName(name)
                .passWord(psd)
                //  tcp://iot.eclipse.org:1883
                .serverUrl("tcp://" + ip + ":" + port)
                .keepAliveInterval(20)
                .timeOut(10)
                .bulid(this.getApplicationContext());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        close();//关闭客户端
    }
}