package com.sdacademy.gieysztor.michal.alarmsetter;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    public static final String MINUTES_KEY = "minute";
    private static final String TAG_SHARED_PREFERENCES = "alarm_shared_preferences";
    public static final String HOUR_KEY = "hour";
    SharedPreferences sharedPreferences;

    @BindView(R.id.activitySettingsTimePicker)
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        timePicker.setIs24HourView(true);
        sharedPreferences = getSharedPreferences(TAG_SHARED_PREFERENCES, MODE_PRIVATE);
    }

    @OnClick(R.id.activitySettingsSet)
    public void setSettings() {
        int hour = getHour();
        int minutes = getMinutes();
        saveDataToSharedPreferences(hour, minutes);
    }

    private int getHour() {
        int hour;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
        } else {
            hour = timePicker.getCurrentHour();
        }
        return hour;
    }

    private int getMinutes() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? timePicker.getMinute() : timePicker.getCurrentMinute();
    }

    private void saveDataToSharedPreferences(int hour, int minutes) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(HOUR_KEY, hour);
        editor.putInt(MINUTES_KEY, minutes);
        editor.apply();
    }
}
