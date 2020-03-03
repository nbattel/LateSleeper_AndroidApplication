package com.twelve.latesleeper.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.twelve.latesleeper.R;

public class QuizResultsActivity extends AppCompatActivity {
    TextView quizResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizresults);
        quizResult = findViewById(R.id.mcResults);
        int sc = getIntent().getIntExtra("quizScore",0);
        quizResult.setText("You scored a total of: "+ sc);
    }




}
