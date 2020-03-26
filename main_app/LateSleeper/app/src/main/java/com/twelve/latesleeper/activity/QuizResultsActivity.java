package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Goal;

public class QuizResultsActivity extends AppCompatActivity {
    TextView quizResult;
    TextView refocus;
    private Goal goal;
    private static String goalID;
    private int touchCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizresults);
        quizResult = findViewById(R.id.mcResults);
        refocus = findViewById(R.id.moveToRefocus);
        Bundle bundle = getIntent().getExtras();
        goal = (Goal) getIntent().getSerializableExtra("goal");
        goalID = getIntent().getStringExtra("goalID");
        int sc = bundle.getInt("finalScore");
        quizResult.setText("You scored a total of: " + sc);
        if (sc > 5) {
            refocus.setText("After taking the quiz, the wise advocate has determined that this pattern is unhealthy, click continue button ");
        } else {
            refocus.setText("This pattern has been determined as healthy by the wise advocate, " +
                    "no further action is required, however, it is up to you if you want to continue with the 4 steps. " +
                    "Click the next button if you do");
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
}


