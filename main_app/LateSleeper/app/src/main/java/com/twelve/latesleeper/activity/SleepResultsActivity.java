package com.twelve.latesleeper.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SleepResultsActivity extends AppCompatActivity {


    public long wakeUpTime;
    public long sleepTime;
    public String sleepTimeDB;
    TextView sleepResults;
    public long hoursSleptInMillis;
    public float hoursSlept;
    private FirebaseAuth mAuth;
    private SensorManager sensorManager;

    Sensor accelerometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_results);
        mAuth = FirebaseAuth.getInstance();
        //Bundle bundle = getIntent().getExtras();
        //wakeUpTime = bundle.getLong("wakeUpTime");
        sleepResults = findViewById(R.id.sleepResults);

        Bundle bundle = getIntent().getExtras();
        wakeUpTime = bundle.getLong("wakeUpTime");
        sleepTime = bundle.getLong("sleepTime");

        hoursSleptInMillis = (wakeUpTime - sleepTime);
        String x = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(hoursSleptInMillis),
                    TimeUnit.MILLISECONDS.toMinutes(hoursSleptInMillis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(hoursSleptInMillis)), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(hoursSleptInMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(hoursSleptInMillis)));
                        sleepResults.setText("Total Sleep Time\n" + x);
                        //now add the hours to the DB
        String array[] = x.split(":");
        int totalHr = Integer.parseInt(array[0]);
        Database.getDatabase().collection("users").document(mAuth.getUid())
                                .collection("goals").document(ViewSpecificGoalActivity.goalID)
                                .update(
                                        "totalHours", FieldValue.increment(totalHr)//need to figure out
                                );
    }






    public void toRevalue(View view)
    {
        Intent intent = new Intent(SleepResultsActivity.this, RevalueActivity.class);
        startActivity(intent); //navigate to set alarm
    }
}
