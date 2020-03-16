package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;

public class ViewGoalsActivity  extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private Button startGoal;
    private Button createGoal;
    private Button continueGoal;
    private Button progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_viewgoals);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Database.retrieveJournalCollection(currentUser.getUid());
        createGoal = findViewById(R.id.createGoal);
        startGoal = findViewById(R.id.startGoal);
        continueGoal = findViewById(R.id.continueGoal);
        progress = findViewById(R.id.progress);
        //updateUI(currentUser);
    }

    public void startGoal(View view){ //this needs to go to 4 step process
        Intent intent = new Intent(ViewGoalsActivity.this, RelabelActivity.class);
        startActivity(intent);
    }

    public void toCreateGoal(View view) { //this needs to go the the create goal activity
        Intent intent = new Intent(ViewGoalsActivity.this, CreateGoalActivity.class);
        startActivity(intent);
    }

    public void continueGoal() {}

    public void viewProgress() {}
}
