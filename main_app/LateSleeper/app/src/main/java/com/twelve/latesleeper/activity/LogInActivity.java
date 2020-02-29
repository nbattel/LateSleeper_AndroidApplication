package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Journal;

import javax.security.auth.login.LoginException;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    // On click function to log the user into their account
    public void logIn(View view) {
        Intent intent = new Intent(LogInActivity.this, UserHomeActivity.class);
        //NEED to verify user info is valid in order to login - do this after
        startActivity(intent);

    }

    // On click function to allow a new user to create an account
    public void createAccount(View view) {
        Intent intent = new Intent(LogInActivity.this, CreateAccountActivity.class);
        startActivity(intent);
    }
}
