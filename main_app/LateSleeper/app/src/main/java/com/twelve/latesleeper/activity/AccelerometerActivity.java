package com.twelve.latesleeper.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;

import java.util.Calendar;
import java.util.Date;

import at.markushi.ui.CircleButton;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    private FirebaseAuth mAuth;
    public long wakeUpTime;
    public long sleepTime;
    private CircleButton disableAlarmButton;
    private SensorManager sensorManager;
    Sensor accelerometer;
    private static final String TAG = "accelerometer activity";
    TextView xValue, yValue, zValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_accelerometer);
        Bundle bundle = getIntent().getExtras();

        wakeUpTime = bundle.getLong("wakeUpTime");
//        xValue=(TextView) findViewById(R.id.xValue);
//        yValue=(TextView) findViewById(R.id.yValue);
//        zValue=(TextView) findViewById(R.id.zValue);
        //Bundle bundle = getIntent().getExtras();
        // wakeUpTime = bundle.getLong("wakeUpTime");
        disableAlarmButton = findViewById(R.id.disableAlarmButton);
        //get system services

        Log.d(TAG, "onCreate: Initialized Sensor Services");
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //now has permission to use sensor
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //now we have the accelerometer
        sensorManager.registerListener(AccelerometerActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        Date currentTime = Calendar.getInstance().getTime();
        sleepTime = currentTime.getTime();

    }//end of oncreate bracket
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d(TAG, "onSensorChanged: X:  "+sensorEvent.values[0]+" Y: "+sensorEvent.values[1]+" Z: "+sensorEvent.values[2]);
//        xValue.setText("xValue: " + sensorEvent.values[0]);
//        yValue.setText("yValue: " + sensorEvent.values[1]);
//        zValue.setText("zValue: " + sensorEvent.values[2]);

        if((sensorEvent.values[0] > 2 || sensorEvent.values[0] < -2) || (sensorEvent.values[1] > 2 || sensorEvent.values[1] < -2) )
        {

            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(1000);
            }
            Toast.makeText(getApplicationContext(), "Put your phone down, and continue sleeping!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public void turnOffAlarm(View view)
    {

        sensorManager.unregisterListener(this);
        // sensorManager = null;

        if(Utility.ringtoneHelper != null) {
            Utility.ringtoneHelper.stopRingtone();
        }

        Database.getDatabase().collection("users").document(mAuth.getUid())
                .collection("goals").document(ViewSpecificGoalActivity.goalID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot doc = task.getResult();
                            long days = (long)doc.get("days");
                            long daysCompleted = (long)doc.get("daysCompleted");
                            if (daysCompleted + 1 == days) {
                                Database.getDatabase().collection("users").document(mAuth.getUid())
                                        .collection("goals").document(ViewSpecificGoalActivity.goalID)
                                        .update(
                                                "daysCompleted", FieldValue.increment(1),
                                                "completed", true
                                        );
                            }
                            else {
                                Database.getDatabase().collection("users").document(mAuth.getUid())
                                        .collection("goals").document(ViewSpecificGoalActivity.goalID)
                                        .update(
                                                "daysCompleted", FieldValue.increment(1)
                                        );
                            }
                        }
                        else {
                            Log.d(TAG, "onComplete: FAILED");
                        }
                    }
                });

        //go to results page saying how much they slept and track that day
        Intent intent = new Intent(AccelerometerActivity.this, SleepResultsActivity.class);
        Bundle bundle = new Bundle();

        //bundle.putLong("sleepTime", sleepTime);
        bundle.putLong("wakeUpTime",wakeUpTime);
        bundle.putLong("sleepTime",sleepTime);
        intent.putExtras(bundle);
        startActivity(intent); //navigate to alarm results
    }


}