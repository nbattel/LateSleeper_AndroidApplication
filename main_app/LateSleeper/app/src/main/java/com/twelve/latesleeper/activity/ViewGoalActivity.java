package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Goal;

public class ViewGoalActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Goal goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_view_goal);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        goal = (Goal)getIntent().getSerializableExtra("goal");
        //updateUI(currentUser);
        Log.d("TAG", "onStart: " + goal.getGoal().get("sleepTime"));
    }

    public void toCreateGoal() {
        Intent intent = new Intent(ViewGoalActivity.this, GoalOverviewActivity.class);
        startActivity(intent);
    }

    public void startGoal() {}

    public void continueGoal() {}

    public void viewProgress() {}
}
