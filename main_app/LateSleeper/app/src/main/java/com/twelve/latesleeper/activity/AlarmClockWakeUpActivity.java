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

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.twelve.latesleeper.R;


import java.util.Calendar;
import java.util.Date;


public class AlarmClockWakeUpActivity extends AppCompatActivity {
    TimePicker timePicker;
    private FirebaseAuth mAuth;
    TextClock currentTime;
    TextView timeTextView;
    int mHour,mMin;
    public long sleepTime;
    public long wakeUpTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock_wakeup);
        Bundle bundle = getIntent().getExtras();
        sleepTime = bundle.getLong("bedTime");
        timePicker = findViewById(R.id.timepicker);
        timeTextView = findViewById(R.id.timeTextView);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
            mHour = hour; //when the user changes the time on the timepicker(big clock on screen), the change in minute and hour are store
            mMin = minute;
            timeTextView.setText(timeTextView.getText().toString()+" "+mHour+" :"+mMin); //output the time that they are setting the alarm to
            }
        });


    }//end of oncreate bracket

    //trying to have button disable alarm once its going off
    public void turnOffAlarm(View view)
    {
      /*  Intent intent = new Intent(alarmClockActivity.this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 24444, intent, PendingIntent.FLAG_UPDATE_CURRENT| Intent.FILL_IN_DATA);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);*/
        if(Utility.ringtoneHelper != null) {
            Utility.ringtoneHelper.stopRingtone();
        }


        //go to results page saying how much they slept and track that day
        Intent intent = new Intent(AlarmClockWakeUpActivity.this, SleepResultsActivity.class);
        Bundle bundle = new Bundle();

        bundle.putLong("sleepTime", sleepTime);
        bundle.putLong("wakeUpTime",wakeUpTime);
        intent.putExtras(bundle);
        startActivity(intent); //navigate to set alarm
    }

    public void setTimer(View view) //this is the onclick for the button
    {
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
    }
        //alarmTime = findViewById(R.id.textClock)

        /*currentTime = findViewById(R.id.textClock);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (currentTime.getText().toString().equals(AlarmTime())){
                    r.play();
                }else{
                    r.stop();
                }
            }
        }, 0, 1000);
    }

    public String AlarmTime(){

        Integer alarmHours = alarmTime.getCurrentHour();
        Integer alarmMinutes = alarmTime.getCurrentMinute();
        String stringAlarmMinutes;

        if (alarmMinutes<10){
            stringAlarmMinutes = "0";
            stringAlarmMinutes = stringAlarmMinutes.concat(alarmMinutes.toString());
        }else{
            stringAlarmMinutes = alarmMinutes.toString();
        }
        String stringAlarmTime;

        if(alarmHours>12){
            alarmHours = alarmHours-12;
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes).concat("PM");
        }else{
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes).concat("AM");
        }
        return stringAlarmTime;
    }*/
}//end of class bracket


