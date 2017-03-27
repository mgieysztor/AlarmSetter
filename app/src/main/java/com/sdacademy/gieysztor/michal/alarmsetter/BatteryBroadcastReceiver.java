package com.sdacademy.gieysztor.michal.alarmsetter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Calendar;

/**
 * Created by RENT on 2017-03-27.
 */

public class BatteryBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = BatteryBroadcastReceiver.class.getSimpleName();
    public static final int HOUR_DEF_VALUE = 22;
    public static final int MINUTES_DEF_VALUE = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        startMainActivity(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SettingsDialogFragment.TAG_SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        int hour = sharedPreferences.getInt(SettingsDialogFragment.HOUR_KEY, HOUR_DEF_VALUE);
        int minutes = sharedPreferences.getInt(SettingsDialogFragment.MINUTES_KEY, MINUTES_DEF_VALUE);

       Calendar settingsTime = getCalendar(hour,minutes);

        if (System.currentTimeMillis()>settingsTime.getTimeInMillis()){
            startMainActivity(context);
        }


    }

    private Calendar getCalendar(int hour, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        return calendar;
    }

    private void startMainActivity(Context context) {
        Intent startMainActivity = new Intent(context, MainActivity.class);
        startMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startMainActivity);
    }
}
