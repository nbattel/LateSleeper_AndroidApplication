package com.twelve.latesleeper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twelve.latesleeper.R;

import java.util.Date;

public class ViewGoalActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


        private TextClock tClock;
        private TextView tView;
        private Button btn;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_goal);
            tClock = findViewById(R.id.textClock1);



        }


   /* @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }*/
}
