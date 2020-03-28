package com.twelve.latesleeper.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;
import com.twelve.latesleeper.model.Goal;

import java.util.Calendar;
import java.util.Date;

public class CreateGoalSettingsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    int mHourSleep, mMinSleep;
    private String time;
    private Integer days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_create_goal_settings);

        TimePicker timePicker = findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
             @Override
             public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                 // TODO Auto-generated method stub
                 time = String.valueOf(hourOfDay).toString() + ":" + String.valueOf(minute).toString();
                 mHourSleep = hourOfDay; //when the user changes the time on the timepicker(big clock on screen), the change in minute and hour are store
                 mMinSleep = minute;
             }

         });

        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        if (numberPicker != null) {
            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(14);
            numberPicker.setWrapSelectorWheel(true);
            numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    String text = "Changed from " + oldVal + " to " + newVal;
                    Toast.makeText(CreateGoalSettingsActivity.this, text, Toast.LENGTH_SHORT).show();
                    days = newVal;
                }
            });
        }

        days = numberPicker.getValue();
        time = timePicker.getHour() + ":" + timePicker.getMinute();

    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
    }

    public void SaveGoal(View view) {
        CollectionReference goalCollection = Database.getDatabase().document("users/" + currentUser.getUid()).collection("goals");
        //moved notifications here
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
        if (Calendar.getInstance().after(calAlarm)) {
            calAlarm.add(Calendar.DAY_OF_MONTH, 1);
        }



        Intent i = new Intent(CreateGoalSettingsActivity.this,MyBroadCastReceiverNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(CreateGoalSettingsActivity.this,9,i,PendingIntent.FLAG_UPDATE_CURRENT);
        //9 is request code, its just random, and 0 is the flag
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calAlarm.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

        //now create goal
        Goal goal = new Goal(time, days, 0, 0, new Date(), false);

        goalCollection.add(goal.getGoal())
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Congratulations on creating a goal!", Toast.LENGTH_LONG);
                            toast.show();
                            ViewSpecificGoalActivity.goalID = task.getResult().getId();
                            //set up notifications
                            Intent intent = new Intent(CreateGoalSettingsActivity.this, ViewGoalsActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Failed creating the goal!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
    }

}
