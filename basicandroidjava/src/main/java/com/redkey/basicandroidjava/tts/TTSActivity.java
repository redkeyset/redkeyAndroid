package com.redkey.basicandroidjava.tts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.redkey.baselibrary.comment.ArouterPers;
import com.redkey.basicandroidjava.R;

import java.util.Locale;
@Route(path = ArouterPers.BasicAndroidLibs.ttsBasic)
public class TTSActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnTtsStop;
    private Button mBtnTtsPlay;
    private Button mBtnTtsMode;
    private EditText mTvTtsContent;
    private TextToSpeech mTTS;

    private static final String TAG = "TTSActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_t_s);
        initView();

        initTextToSpeech();
    }

    private void initTextToSpeech() {

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    //设置播放的音频类型
                    int result = mTTS.setLanguage(Locale.CHINA);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e(TAG, "onInit: language is not available.");
                        Uri uri = Uri.parse("http://acj2.pc6.com/pc6_soure/2017-6/com.iflytek.vflynote_208.apk");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    } else {
                        Log.i(TAG, "onInit: init success.");
                    }
                } else {
                    Log.e(TAG, "onInit: error");
                }
            }
        });

        // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
        mTTS.setPitch(1.0f);
        // 设置语速
        mTTS.setSpeechRate(0.5f);
    }
    private void initView() {
        mBtnTtsStop = (Button) findViewById(R.id.btn_tts_stop);
        mBtnTtsPlay = (Button) findViewById(R.id.btn_tts_play);
        mBtnTtsMode = (Button) findViewById(R.id.btn_tts_mode);
        mTvTtsContent = (EditText) findViewById(R.id.tv_tts_content);

        mBtnTtsPlay.setOnClickListener(this);
        mBtnTtsStop.setOnClickListener(this);
        mBtnTtsMode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_tts_play) {
            String ttsContent = mTvTtsContent.getText().toString();
            mTTS.speak(ttsContent, TextToSpeech.QUEUE_FLUSH, null);
//            public int speak(CharSequence text, int queueMode, Bundle params, String utteranceId) {
            mTTS.speak(ttsContent,TextToSpeech.QUEUE_FLUSH,null,"");
        } else if (id == R.id.btn_tts_stop) {
            mTTS.stop();
        } else if (id == R.id.btn_tts_mode) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTTS != null) {
            //不管是不是在阅读,都打断
            mTTS.stop();
            //关闭,释放资源
            mTTS.shutdown();
            mTTS = null;
        }
    }
}