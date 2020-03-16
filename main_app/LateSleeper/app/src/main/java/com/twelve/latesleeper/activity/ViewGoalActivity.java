package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Goal;

import java.util.HashMap;

public class ViewGoalActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Goal goal;
    private HashMap<String, Object> goalInfo;
    ProgressBar loadingBar;
    ConstraintLayout dimLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_view_goal);

        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        dimLayout = (ConstraintLayout) findViewById(R.id.dimLayout);
    }

    @Override
    public void onStart() {
        super.onStart();
        dimLayout.setVisibility(View.VISIBLE);
        dimLayout.bringToFront();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        goal = (Goal) getIntent().getSerializableExtra("goal");
        goalInfo = goal.getGoal();
        //updateUI(currentUser);
        dimLayout.setVisibility(View.GONE);
    }

    public void startGoal(View view) {
        Intent intent = new Intent(ViewGoalActivity.this, AlarmClockWakeUpActivity.class);
        startActivity(intent);
    }

}
