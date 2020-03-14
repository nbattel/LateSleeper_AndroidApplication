package com.twelve.latesleeper.activity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.twelve.latesleeper.R;

import java.util.Calendar;
import java.util.Date;

public class AlarmClockSleepTimeActivity extends AppCompatActivity {
    int mHourSleep, mMinSleep;
    TimePicker timePickerSleep;
    TextView timeTextViewSleep;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock_sleeptime);
        timePickerSleep = findViewById(R.id.timepickerSleep);
        timeTextViewSleep = findViewById(R.id.timeTextViewSleep);
        timePickerSleep.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                mHourSleep = hour; //when the user changes the time on the timepicker(big clock on screen), the change in minute and hour are store
                mMinSleep = minute;
                timeTextViewSleep.setText(timeTextViewSleep.getText().toString() + " " + mHourSleep + " :" + mMinSleep); //output the time that they are setting the alarm to
            }
        });
    }

    public void configureWakeUp(View view){
        //send push notification at specific sleep time
        //and navigate to set alarm clock
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Date date = new Date();
        Calendar calAlarm = Calendar.getInstance();//for alarm
        Calendar calNow = Calendar.getInstance();//for the current time
        calAlarm.setTime(date);
        calNow.setTime(date);
        calAlarm.set(Calendar.HOUR_OF_DAY,mHourSleep);//setting the hour to the user entered hour through timepicker
        calAlarm.set(Calendar.MINUTE,mMinSleep); //setting the minute to the user entered minute through timepicker
        calAlarm.set(Calendar.SECOND,0);//just setting the alarm to go off  right when time changes to the specific minute

        if(calAlarm.before((calNow)))
        {

            calAlarm.add(Calendar.DATE,1); //if its 8pm and you set alarm to 6:30pm it will happen the next day
        }






            Intent i = new Intent(AlarmClockSleepTimeActivity.this,MyBroadCastReceiverNotification.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmClockSleepTimeActivity.this,9,i,PendingIntent.FLAG_UPDATE_CURRENT);
            //24444 is request code, its just random, and 0 is the flag
            alarmManager.set(AlarmManager.RTC_WAKEUP,calAlarm.getTimeInMillis(),pendingIntent);


    }



}//end of class
