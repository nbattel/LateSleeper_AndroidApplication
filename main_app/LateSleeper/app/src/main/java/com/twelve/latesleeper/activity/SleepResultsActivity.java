package com.twelve.latesleeper.activity;

import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.twelve.latesleeper.R;

import java.util.concurrent.TimeUnit;

public class SleepResultsActivity extends AppCompatActivity {

    public long sleepTime;
    public long wakeUpTime;
    TextView sleepResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_results);
        Bundle bundle = getIntent().getExtras();
        sleepTime = bundle.getLong("sleepTime");
        wakeUpTime = bundle.getLong("wakeUpTime");
        sleepResults = findViewById(R.id.sleepResults);

        long totalSleep = wakeUpTime - sleepTime;
        String result = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(totalSleep),
                TimeUnit.MILLISECONDS.toMinutes(totalSleep) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totalSleep)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(totalSleep) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalSleep)));



        sleepResults.setText("you slept: "+result+" Total! Congrats!");



    }
}
