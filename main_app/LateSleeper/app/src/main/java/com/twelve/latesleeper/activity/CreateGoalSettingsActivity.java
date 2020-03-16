package com.twelve.latesleeper.activity;

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

import java.util.Date;

public class CreateGoalSettingsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

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

        Goal goal = new Goal(time, days, 0, 0, new Date() );

        goalCollection.add(goal.getGoal())
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Congratulations on creating a goal!", Toast.LENGTH_LONG);
                            toast.show();
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
