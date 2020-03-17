package com.twelve.latesleeper.activity;

import android.content.Intent;
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

    public long sleepTime;
    //public long wakeUpTime;
    public String sleepTimeDB;
    TextView sleepResults;
    public long hoursSleptInMillis;
    public float hoursSlept;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_results);
        mAuth = FirebaseAuth.getInstance();
       // Bundle bundle = getIntent().getExtras();
        //sleepTime = bundle.getLong("sleepTime");
        //wakeUpTime = bundle.getLong("wakeUpTime");
        sleepResults = findViewById(R.id.sleepResults);

        /*long totalSleep = wakeUpTime - sleepTime;
        String result = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(totalSleep),
                TimeUnit.MILLISECONDS.toMinutes(totalSleep) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totalSleep)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(totalSleep) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalSleep)));*/

        Database.getDatabase().collection("users").document(mAuth.getUid()).collection("goals").document(ViewSpecificGoalActivity.goalID)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    //if it succeeds
                    DocumentSnapshot result = task.getResult();

                    sleepTimeDB = String.valueOf(result.get("sleepTime"));
                    Log.d("TEST", "onComplete: "+sleepTimeDB);
                    String sleepTimeHourAndMinute[] = sleepTimeDB.split(":");
                    String sleepHour = sleepTimeHourAndMinute[0];
                    String sleepMin = sleepTimeHourAndMinute[1];
                    Calendar now = Calendar.getInstance();
                    int year = now.get(Calendar.YEAR);
                    int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
                    int day = now.get(Calendar.DAY_OF_MONTH)-1 ;//sleeptime was yesterday
                    int hour = Integer.parseInt(sleepHour);
                    int minute = Integer.parseInt(sleepMin);
                    String bedDate = year+"-"+month+'-'+day+' '+hour+':'+minute;
                    Log.d("TEST2", "onComplete: "+bedDate);
                    sleepResults.setText(bedDate);

                    SimpleDateFormat bedDateTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    try {
                        Date parsedDate = bedDateTimeStamp.parse(bedDate);
                        Timestamp timestamp = new Timestamp(parsedDate.getTime());
                        Bundle bundle = getIntent().getExtras();
                        sleepTime = bundle.getLong("wakeUpTime");





                        hoursSleptInMillis = (sleepTime - timestamp.getTime());
                        String x = String.format("%02d:%02d:%02d",
                                TimeUnit.MILLISECONDS.toHours(hoursSleptInMillis),
                                TimeUnit.MILLISECONDS.toMinutes(hoursSleptInMillis) -
                                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(hoursSleptInMillis)), // The change is in this line
                                TimeUnit.MILLISECONDS.toSeconds(hoursSleptInMillis) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(hoursSleptInMillis)));
                        sleepResults.setText("Total sleep time last night: "+ x);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }
                else {
                    //if it fails

                }



            }
        });


        //Log.d("TEST2", "onComplete: "+sleepTimeDB);
        //sleepResults.setText("you slept: INSERT CALC Total! Congrats!"+sleepTimeDB);


        Database.getDatabase().collection("users").document(mAuth.getUid())
                .collection("goals").document(ViewSpecificGoalActivity.goalID)
                .update(
                        "totalHours", FieldValue.increment(1)//need to figure out
                );

    }

    public void toRevalue(View view)
    {
        Intent intent = new Intent(SleepResultsActivity.this, RevalueActivity.class);
        startActivity(intent); //navigate to set alarm
    }
}
