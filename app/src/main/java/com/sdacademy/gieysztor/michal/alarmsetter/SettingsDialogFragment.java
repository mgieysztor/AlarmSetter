package com.sdacademy.gieysztor.michal.alarmsetter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TimePicker;

import butterknife.OnClick;

public class SettingsDialogFragment extends DialogFragment {
    public static final String TAG = SettingsDialogFragment.class.getSimpleName();
    public static final String MINUTES_KEY = "minute";
    public static final String TAG_SHARED_PREFERENCES = "alarm_shared_preferences";
    public static final String HOUR_KEY = "hour";
    private TimePicker timePicker;
    SharedPreferences sharedPreferences;

    //    @BindView(R.id.fragmentSettingsTimePicker)
//    TimePicker timePicker;

    public SettingsDialogFragment() {
        // Required empty public constructor
    }

    public static SettingsDialogFragment newInstance() {
        return new SettingsDialogFragment();

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_settings_dialog, container, false);
//        ButterKnife.bind(this, view);
//        timePicker.setIs24HourView(true);
//        return view;
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity activity = getActivity();
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_settings_dialog, null);
        timePicker = (TimePicker) view.findViewById(R.id.fragmentSettingsTimePicker);
        timePicker.setIs24HourView(true);
        sharedPreferences = activity.getSharedPreferences(TAG_SHARED_PREFERENCES, Context.MODE_PRIVATE);

        setTimePickerMinutes();
        setTimePickerHour();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.settings_dialog_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveSettingsToSharedPreferences();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        Dialog dialog = builder.create();
        return dialog;
    }

    private void setTimePickerHour() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(getSavedHour());
        } else {
            timePicker.setCurrentHour(getSavedHour());
        }
    }

    private void setTimePickerMinutes() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setMinute(getSavedMinutes());
        } else {
            timePicker.setCurrentMinute(getSavedMinutes());
        }
    }

    @OnClick(R.id.activitySettingsSet)
    public void saveSettingsToSharedPreferences() {
        int hour = getTimePickerHour();
        int minutes = getTimePickerMinutes();
        saveDataToSharedPreferences(hour, minutes);
    }

    private int getSavedMinutes(){
        return sharedPreferences.getInt(MINUTES_KEY,0);
    }

    private int getSavedHour(){
        return sharedPreferences.getInt(HOUR_KEY,0);
    }

    private int getTimePickerHour() {
        int hour;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
        } else {
            hour = timePicker.getCurrentHour();
        }
        return hour;
    }

    private int getTimePickerMinutes() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? timePicker.getMinute() : timePicker.getCurrentMinute();
    }

    private void saveDataToSharedPreferences(int hour, int minutes) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(HOUR_KEY, hour);
        editor.putInt(MINUTES_KEY, minutes);
        editor.apply();
    }
}