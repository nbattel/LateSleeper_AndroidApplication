package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twelve.latesleeper.R;


public class UserHomeActivity extends AppCompatActivity {
    TextView goalsSet;
    //TextView getGoalsCompleted;
    TextView goalsCompleted;
    TextView consecutiveDays;
    TextView totalEntries;
    TextView welcomeBack;
    //we have to access our database to get the data, then modify all of these textfields to have the updated info
    //this should be done whenever the activity is instantiated


    private FirebaseAuth mAuth;
    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_userhome);
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        //TODO
        //Get the users username
        goalsCompleted = findViewById(R.id.goalsCompleted);
        goalsSet = findViewById(R.id.goalsSet);
        consecutiveDays = findViewById(R.id.daysConsec);
        totalEntries = findViewById(R.id.totalEntries);
        welcomeBack = findViewById(R.id.welcomeTezt);
        //now modify the text based on information about user in database
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void signOut(View view) {
        mAuth.signOut();
        updateUI(mAuth.getCurrentUser());
    }

    // On click function to log the user into their account
    public void toJournal(View view){
        Intent intent = new Intent(UserHomeActivity.this, JournalActivity.class);
        //NEED to verify user info is valid in order to login - do this after
        startActivity(intent);

    }

    public void toViewGoals(View view){
        Intent intent = new Intent(UserHomeActivity.this, ViewGoalsActivity.class);
        startActivity(intent);
    }





    public void updateUI(FirebaseUser user) {
        if (user != null) {
            welcomeText.setText("Welcome Back, " + user.getDisplayName());
        } else {
            Intent intent = new Intent(UserHomeActivity.this, LogInActivity.class);
            startActivity(intent);
        }
    }

}
