package com.twelve.latesleeper.activity;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.twelve.latesleeper.R;

public class CreateGoalSettingsActivity extends AppCompatActivity {

    NumberPicker np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal_settings);

        np = findViewById(R.id.numberPicker);

        np.setMinValue(1);
        np.setMaxValue(14);

        np.setOnValueChangedListener(onValueChangeListener);
    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
        new NumberPicker.OnValueChangeListener(){
        @Override
        public void onValueChange(NumberPicker numberPicker, int i, int i1) {
            Toast.makeText(CreateGoalSettingsActivity.this,
                    "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);
        }
    };
}
