package com.sdacademy.gieysztor.michal.alarmsetter;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.mainActivityTimePicker)
    TimePicker timePicker;

    @BindView(R.id.mainActivityButton)
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        timePicker.setIs24HourView(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.mainActivityButton)
    public void confirmAlarm() {
        Calendar calendar = Calendar.getInstance();
        int hour;
        int minutes;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
            minutes = timePicker.getMinute();
        } else {
            hour = timePicker.getCurrentHour();
            minutes = timePicker.getCurrentMinute();
        }

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);

        Log.d(TAG, String.valueOf(calendar.getTimeInMillis()));
    }

    @OnClick(R.id.mainActivityTestButton)
    public void openAlarm(){
        Intent alarmIntent = new Intent(this,AlarmActivity.class);
        startActivity(alarmIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_settings:
                openSettingsActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettingsActivity() {
        Intent openSettings = new Intent(this, SettingsActivity.class);
        startActivity(openSettings);

    }
}
