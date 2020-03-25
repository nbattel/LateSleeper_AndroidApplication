package com.twelve.latesleeper.activity;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextClock;

import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;


import java.util.Calendar;
import java.util.Date;


public class AlarmClockWakeUpActivity extends AppCompatActivity {
    TimePicker timePicker;
    private FirebaseAuth mAuth;
    int mHour,mMin;
    public long wakeUpTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_alarm_clock_wakeup);
        //Bundle bundle = getIntent().getExtras();
        //sleepTime = bundle.getLong("bedTime");
        timePicker = findViewById(R.id.timepicker);
       // timeTextView = findViewById(R.id.timeTextView);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
            mHour = hour; //when the user changes the time on the timepicker(big clock on screen), the change in minute and hour are store
            mMin = minute;
         //   timeTextView.setText(timeTextView.getText().toString()+" "+mHour+" :"+mMin); //output the time that they are setting the alarm to
            }
        });


    }//end of oncreate bracket




    public void setAlarm(View view) //this is the onclick for the button
    {
       // Toast.makeText(getApplicationContext(), "ALARM SET", Toast.LENGTH_SHORT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Date date = new Date();
        Calendar calAlarm = Calendar.getInstance();//for alarm
        Calendar calNow = Calendar.getInstance();//for the current time
        calAlarm.setTime(date);
        calNow.setTime(date);
        calAlarm.set(Calendar.HOUR_OF_DAY,mHour);//setting the hour to the user entered hour through timepicker
        calAlarm.set(Calendar.MINUTE,mMin); //setting the minute to the user entered minute through timepicker
        calAlarm.set(Calendar.SECOND,0);//just setting the alarm to go off  right when time changes to the specific minute


        if(calAlarm.before((calNow)))
        {
            calAlarm.add(Calendar.DATE,1);
        }
        wakeUpTime = calAlarm.getTimeInMillis();
        //call broadcast receiver
        Intent i = new Intent(AlarmClockWakeUpActivity.this,MyBroadcastReceiverAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmClockWakeUpActivity.this,24444,i,PendingIntent.FLAG_UPDATE_CURRENT);
        //24444 is request code, its just random, and 0 is the flag
        alarmManager.set(AlarmManager.RTC_WAKEUP,calAlarm.getTimeInMillis(),pendingIntent);//actually set the alarm
        Toast.makeText(getApplicationContext(), "Alarm Set!", Toast.LENGTH_SHORT).show();

    }

    public void  toAccelerometer(View view)
    {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(AlarmClockWakeUpActivity.this, AccelerometerActivity.class);
        bundle.putLong("wakeUpTime", wakeUpTime);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}//end of class bracket


