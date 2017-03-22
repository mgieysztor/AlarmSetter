package com.sdacademy.gieysztor.michal.alarmsetter;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmActivity extends AppCompatActivity {

    public static final String TAG = AlarmActivity.class.getSimpleName();
    private Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        ButterKnife.bind(this);
        setupRingtone();
        startSound();
    }

    private void setupRingtone() {
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(this, alarmUri);
    }

    private void startSound() {
        if (ringtone != null) {
            ringtone.play();
        }
    }

    @OnClick(R.id.alarmActivityLayout)
    public void stopSound() {
        if (ringtone.isPlaying() && ringtone!=null) {
            ringtone.stop();
            onBackPressed();
        } else {
            Log.d(TAG, "Ringtone is null or not playing");
        }
    }
}
