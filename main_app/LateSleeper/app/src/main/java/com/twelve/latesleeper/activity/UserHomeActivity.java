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

    public void viewJournal(View view){

    }

    public void viewProgress(View view){

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
