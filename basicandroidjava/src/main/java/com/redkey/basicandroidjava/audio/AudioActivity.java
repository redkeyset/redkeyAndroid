package com.redkey.basicandroidjava.audio;

import android.content.Context;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.redkey.baselibrary.comment.ArouterPers;
import com.redkey.basicandroidjava.R;

import static com.redkey.basicandroidjava.audio.AudioUtils.stopVoice;

/**
 * 音频通路切换 及 声音大小控制
 */
@Route(path = ArouterPers.BasicAndroidLibs.audioBasic)
public class AudioActivity extends AppCompatActivity {

    private Context mContext;

    enum AudioType{
        TELEPHONE, TRUMPET, BLUETOOTH, WIRED_HEADSET
    }

    private RadioGroup mRgAudioType;
    private RadioGroup.OnCheckedChangeListener audioCheckType = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        stopVoice();

            if (radioGroup.getCheckedRadioButtonId() == R.id.rb_telephone) {
                checkAudioType(AudioType.TELEPHONE);
            }else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_trumpet){
                checkAudioType(AudioType.TRUMPET);
            }else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_bluetooth){
                checkAudioType(AudioType.BLUETOOTH);
            }else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_wired_headset){
                checkAudioType(AudioType.WIRED_HEADSET);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = AudioActivity.this;
        setContentView(R.layout.activity_audio);
        initView();
    }

    private void initView() {
        mRgAudioType = (RadioGroup) findViewById(R.id.rg_audio_type);
        mRgAudioType.setOnCheckedChangeListener(audioCheckType);
    }

    private void checkAudioType(AudioType type) {
        if (type.equals(AudioType.TELEPHONE)){
            AudioUtils.changeToReceiver(mContext);
            Toast.makeText(mContext,"11",Toast.LENGTH_SHORT).show();
        }else if (type.equals(AudioType.TRUMPET)){
            Toast.makeText(mContext,"22",Toast.LENGTH_SHORT).show();
            AudioUtils.changeToSpeaker(mContext);
        }else if (type.equals(AudioType.BLUETOOTH)){
            Toast.makeText(mContext,"33",Toast.LENGTH_SHORT).show();
            AudioUtils.changeToHeadset(mContext);
        } else if (type.equals(AudioType.WIRED_HEADSET)) {
            Toast.makeText(mContext,"44",Toast.LENGTH_SHORT).show();
            AudioUtils.changeToSpeaker(mContext);
        }

        AudioUtils.startPlay(mContext);

    }

}