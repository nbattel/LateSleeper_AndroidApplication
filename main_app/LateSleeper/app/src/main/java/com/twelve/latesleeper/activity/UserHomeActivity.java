package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Journal;


public class UserHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);
        //TODO
        //Get the users username
    }



    // On click function to log the user into their account
    public void toJournal(View view){
        Intent intent = new Intent(UserHomeActivity.this, JournalActivity.class);
        //NEED to verify user info is valid in order to login - do this after
        startActivity(intent);

    }

    // On click function to allow a new user to create an account
    /*public void createAccount(View view) {
        Intent intent = new Intent(LogInActivity.this, CreateAccountActivity.class);
        startActivity(intent);
    }

    // On click test function to go to journal activity
    public void toJournal(View view){
        Intent intent = new Intent(LogInActivity.this, JournalActivity.class);
        startActivity(intent);
    }*/
}
