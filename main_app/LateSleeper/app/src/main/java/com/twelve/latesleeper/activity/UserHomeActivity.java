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

    public void toViewGoals(View view){
        Intent intent = new Intent(UserHomeActivity.this, ViewGoalsActivity.class);
        startActivity(intent);
    }

}
