package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twelve.latesleeper.R;

public class ViewGoalActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void toCreateGoal() {
        Intent intent = new Intent(ViewGoalActivity.this, GoalOverviewActivity.class);
        startActivity(intent);
    }

    public void startGoal() {}

    public void continueGoal() {}

    public void viewProgress() {}
}
