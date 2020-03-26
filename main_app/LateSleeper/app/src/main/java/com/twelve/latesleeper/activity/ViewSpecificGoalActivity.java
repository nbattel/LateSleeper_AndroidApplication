package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Goal;

import java.util.Date;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class ViewSpecificGoalActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Goal goal;
    private HashMap<String, Object> goalInfo;

    public static String goalID;

    ProgressBar loadingBar;
    ConstraintLayout dimLayout;

    TextView sleepTimeTextView;
    TextView daysTextView;
    TextView daysCompletedTextView;
    TextView totalHoursTextView;
    TextView dateCreatedTextView;
    TextView completedTextView;
    Button startGoal;

    private String sleepTime;
    private Integer days;
    private Integer daysCompleted;
    private Integer totalHours;
    private Date dateCreated;
    private Boolean completed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_view_goal);

        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        dimLayout = (ConstraintLayout) findViewById(R.id.dimLayout);

        sleepTimeTextView = findViewById(R.id.sleepTimeTextView);
        daysTextView = findViewById(R.id.daysTextView);
        daysCompletedTextView = findViewById(R.id.daysCompletedTextView);
        totalHoursTextView = findViewById(R.id.totalHoursTextView);
        dateCreatedTextView = findViewById(R.id.dateCreatedTextView);
        completedTextView = findViewById(R.id.completedTextView);
        startGoal = findViewById(R.id.startGoal);

        goalID = getIntent().getStringExtra("goalID");
        Log.d("TAG", "onCreate: " + goalID);

        /*if(goalInfo.get("daysCompleted").toString() == '0')
        {
            startGoal.setText("Continue Goal");
        }
        else{
            startGoal.setText("Start Goal");

        }*/
    }

    @Override
    public void onStart() {
        super.onStart();

        dimLayout.setVisibility(View.VISIBLE);
        dimLayout.bringToFront();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        goal = (Goal) getIntent().getSerializableExtra("goal");
        goalInfo = goal.getGoal();

        goalID = getIntent().getStringExtra("goalID");

        sleepTime = goalInfo.get("sleepTime").toString();
        days = parseInt(goalInfo.get("days").toString());
        daysCompleted = parseInt(goalInfo.get("daysCompleted").toString());
        totalHours = parseInt(goalInfo.get("daysCompleted").toString());
        dateCreated = new Date(goalInfo.get("dateCreated").toString());
        completed = Boolean.parseBoolean(goalInfo.get("completed").toString());

        sleepTimeTextView.setText("Sleep time \n" + goalInfo.get("sleepTime").toString());
        daysTextView.setText("Days for goal \n" + goalInfo.get("days").toString());
        daysCompletedTextView.setText("Days completed \n" + parseInt(goalInfo.get("daysCompleted").toString()));
        totalHoursTextView.setText("Total hours recorded \n" + goalInfo.get("totalHours").toString());
        dateCreatedTextView.setText("Created \n" + goalInfo.get("dateCreated").toString());
        completedTextView.setText("Completed \n" + goalInfo.get("completed").toString());

        dimLayout.setVisibility(View.GONE);

        if(daysCompleted != 0)
        {
            startGoal.setText("Continue Goal");
        }
        else{
            startGoal.setText("Start Goal");
        }

        if(completed) {
            startGoal.setVisibility(View.GONE);
        }
    }

    public void startGoal(View view){
        Intent intent = new Intent(ViewSpecificGoalActivity.this, RelabelActivity.class);
        intent.putExtra("goal", goal);
        intent.putExtra("goalID", goalID);
        startActivity(intent);
    }

    public void relabelJournal(View view) {
        Intent intent = new Intent(ViewSpecificGoalActivity.this, ViewJournalActivity.class);
        intent.putExtra("journalType", true);
        startActivity(intent);
    }

    public void revalueJournal(View view) {
        Intent intent = new Intent(ViewSpecificGoalActivity.this, ViewJournalActivity.class);
        intent.putExtra("journalType", false);
        startActivity(intent);
    }

}
