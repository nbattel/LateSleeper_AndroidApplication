package com.twelve.latesleeper;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Variable to keep track of the amount of times the user touches the screen
    private int touchCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // On click function to move to the log in activity
    public void nextActivity(View view) {
        touchCount  = touchCount  + 1;

        // If the user taps twice, the activity will change to the login screen
        if (++touchCount == 2) {
            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);
        }
    }
}
