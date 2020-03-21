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

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {
    private FirebaseAuth mAuth;
    public long wakeUpTime;
    private Button disableAlarmButton;
    private SensorManager sensorManager;
    Sensor accelerometer;
    private static final String TAG = "accelerometer activity";
   TextView xValue, yValue, zValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_accelerometer);

        xValue=(TextView) findViewById(R.id.xValue);
        yValue=(TextView) findViewById(R.id.yValue);
        zValue=(TextView) findViewById(R.id.zValue);
        //Bundle bundle = getIntent().getExtras();
       // wakeUpTime = bundle.getLong("wakeUpTime");
        //disableAlarmButton = findViewById(R.id.disableAlarmButton);
        //get system services

        Log.d(TAG, "onCreate: Initialized Sensor Services");
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //now has permission to use sensor
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //now we have the accelerometer
        sensorManager.registerListener(AccelerometerActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);


    }//end of oncreate bracket
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d(TAG, "onSensorChanged: X:  "+sensorEvent.values[0]+" Y: "+sensorEvent.values[1]+" Z: "+sensorEvent.values[2]);
        xValue.setText("xValue: " + sensorEvent.values[0]);
        yValue.setText("yValue: " + sensorEvent.values[1]);
        zValue.setText("zValue: " + sensorEvent.values[2]);

       if(sensorEvent.values[0] > 1)
       {

           Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
           } else {
               //deprecated in API 26
               v.vibrate(500);
           }
           Toast.makeText(getApplicationContext(), "PUT YOUR PHONE DOWN CUNT", Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    /*public void turnOffAlarm(View view)
    {


        if(Utility.ringtoneHelper != null) {
            Utility.ringtoneHelper.stopRingtone();
        }


        //go to results page saying how much they slept and track that day
        Intent intent = new Intent(AccelerometerActivity.this, SleepResultsActivity.class);
        Bundle bundle = new Bundle();

        // bundle.putLong("sleepTime", sleepTime);
        //bundle.putLong("wakeUpTime",wakeUpTime);
        //intent.putExtras(bundle);

        Database.getDatabase().collection("users").document(mAuth.getUid())
                .collection("goals").document(ViewSpecificGoalActivity.goalID)
                .update(
                        "daysCompleted", FieldValue.increment(1)
                );

        startActivity(intent); //navigate to alarm results
    }*/


}
