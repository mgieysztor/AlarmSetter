package com.sdacademy.gieysztor.michal.alarmsetter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmActivity extends AppCompatActivity {

    public static final String TAG = AlarmActivity.class.getSimpleName();
    private Ringtone ringtone1;
    private Vibrator vibrator;
    private Animation animation;
    private MediaPlayer ringtone;

    @BindView(R.id.alarmActivityLayout)
    View mAlarmActivityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
        setup();
        startAlarm();
    }

    private void setupRingtone() {

        Uri path =  Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alarm);
        ringtone = MediaPlayer.create(getApplicationContext(), path);
        ringtone.setLooping(true);
        ringtone.setVolume(1.0f, 1.0f);
        setDeviceMaxVolume();



//        RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(),
//                RingtoneManager.TYPE_ALARM, path);
//        ringtone = RingtoneManager.getRingtone(this, path);
////        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
////        ringtone = RingtoneManager.getRingtone(this, alarmUri);
//        ringtone.setStreamType(AudioManager.STREAM_ALARM);

    }

    private void setDeviceMaxVolume() {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
    }

    private void startAlarm() {
        startSound();
        startVibration();
        startAnimation();
    }

    private void startSound() {
        if (ringtone != null) {
            ringtone.start();
//            ringtone.play();
        }
    }

    @OnClick(R.id.alarmActivityLayout)
    public void stopAlarm() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        stopSound();
        stopVibration();
        super.onBackPressed();
    }

    private void stopSound() {
        if (ringtone.isPlaying() && ringtone != null) {
            ringtone.stop();
        } else {

            Log.d(TAG, "Ringtone is null or not playing");
        }
    }

    private void setupVibration() {
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    private void startVibration() {
        long[] pattern = {0, 100, 100};
        vibrator.vibrate(pattern, 0);
    }

    private void stopVibration() {
        vibrator.cancel();
    }

    private void setupAnimation() {
        animation = new AlphaAnimation(1, 0);
        animation.setDuration(500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
    }

    private void startAnimation() {
        mAlarmActivityLayout.startAnimation(animation);
    }

    private void setup() {
        setupRingtone();
        setupVibration();
        setupAnimation();
    }
}
