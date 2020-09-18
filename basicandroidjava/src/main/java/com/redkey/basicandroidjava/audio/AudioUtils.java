package com.redkey.basicandroidjava.audio;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;

import com.ecs.toolslibrary.LogUtil;
import com.redkey.basicandroidjava.R;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;

public class AudioUtils {
    private static int lastModel = -10;

    /**
     * 音频外放
     */
    public static void changeToSpeaker(Context context) {
        LogUtil.e("音频外放");
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(context, R.raw.music);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        audioManager.setMode(AudioManager.MODE_IN_CALL);
        //注意此处，蓝牙未断开时使用MODE_IN_COMMUNICATION而不是MODE_NORMAL
//        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.stopBluetoothSco();
        audioManager.setBluetoothScoOn(false);
        audioManager.setSpeakerphoneOn(true);
    }

    /**
     * 切换到蓝牙音箱
     */
    public static void changeToHeadset(Context context) {
        LogUtil.e("切换到蓝牙音箱");
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.startBluetoothSco();
        audioManager.setBluetoothScoOn(true);
        audioManager.setSpeakerphoneOn(false);
    }

    /**
     * 切换到听筒
     */
    public static void changeToReceiver(Context context) {
        LogUtil.e("切换到听筒");
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(context, R.raw.music);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_VOICE_CALL);
        audioManager.setSpeakerphoneOn(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        } else {
            audioManager.setMode(AudioManager.MODE_IN_CALL);
        }
    }


    public static void dispose(Context context, AudioManager.OnAudioFocusChangeListener focusRequest) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(lastModel);
        if (audioManager.isBluetoothScoOn()) {
            audioManager.setBluetoothScoOn(false);
            audioManager.stopBluetoothSco();
        }
        audioManager.unloadSoundEffects();
        if (null != focusRequest) {
            audioManager.abandonAudioFocus(focusRequest);
        }
    }


    public static void getModel(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        lastModel = audioManager.getMode();
    }

    public static void changeToNomal(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_NORMAL);
    }

    public static boolean isWiredHeadsetOn(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.isWiredHeadsetOn();
    }

    public static boolean isBluetoothA2dpOn(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.isBluetoothA2dpOn();
    }

    /**
     * context 传入的是MicroContext.getApplication()
     *
     * @param context
     */
    public static void choiceAudioModel(Context context) {
        if (isWiredHeadsetOn(context)) {
            changeToReceiver(context);
        } else if (isBluetoothA2dpOn(context)) {
            changeToHeadset(context);
        } else {
            changeToSpeaker(context);
        }
    }

    public static void pauseMusic(Context context, AudioManager.OnAudioFocusChangeListener focusRequest) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.requestAudioFocus(focusRequest, AudioManager.STREAM_MUSIC, AUDIOFOCUS_GAIN);
    }


    private static MediaPlayer mediaPlayer;

    public static void startPlay(Context context) {
        try {
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //停止播放声音
    public static void stopVoice(){
        if(null!=mediaPlayer) {
            mediaPlayer.stop();
        }
    }
}

