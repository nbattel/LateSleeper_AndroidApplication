package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Goal;

import at.markushi.ui.CircleButton;

public class QuizResultsActivity extends AppCompatActivity {
    TextView quizResult;
    TextView refocus;
    private Goal goal;
    private static String goalID;
    private int touchCount;

    public CircleButton nextButton;
    public CircleButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizresults);
        nextButton = (CircleButton) findViewById(R.id.nextButton);
        homeButton = (CircleButton) findViewById(R.id.homeButton);
        quizResult = findViewById(R.id.mcResults);
        refocus = findViewById(R.id.moveToRefocus);
        Bundle bundle = getIntent().getExtras();
        goal = (Goal) getIntent().getSerializableExtra("goal");
        goalID = getIntent().getStringExtra("goalID");
        int sc = bundle.getInt("finalScore");
        quizResult.setText("You scored: " + sc);
        if (sc >= 20) {
            refocus.setText("The wise advocate has determined that this pattern is unhealthy. Continue below.");


        } else {
            refocus.setText("The wise advocate has determined that this pattern is healthy. " +
                    "No further action is required!");
            nextButton.setEnabled(false);
            nextButton.setVisibility(View.INVISIBLE);

            homeButton.setEnabled(true);
            homeButton.setVisibility(View.VISIBLE);

        }

    }

    public void nextButton(View view) {
        Intent intent = new Intent(QuizResultsActivity.this, RefocusActivity.class);
        intent.putExtra("goal", goal);
        intent.putExtra("goalID", goalID);
        startActivity(intent);
        Toast toast = Toast.makeText(getApplicationContext(), "Congratulations on completing the Reframe step!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void homeButton(View view) {
        Intent intent = new Intent(QuizResultsActivity.this, UserHomeActivity.class);
        intent.putExtra("goal", goal);
        intent.putExtra("goalID", goalID);
        startActivity(intent);
        Toast toast = Toast.makeText(getApplicationContext(), "Congratulations on completing the Reframe step!", Toast.LENGTH_SHORT);
        toast.show();
    }

}


