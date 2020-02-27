package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.twelve.latesleeper.R;

public class RevalueActivity extends AppCompatActivity {

    private boolean isGoalComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revalue);
    }
}
