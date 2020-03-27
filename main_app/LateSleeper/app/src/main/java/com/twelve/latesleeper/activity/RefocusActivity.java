package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Goal;

public class RefocusActivity extends AppCompatActivity {

    private Goal goal;
    private static String goalID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refocus);
        goal = (Goal) getIntent().getSerializableExtra("goal");
        goalID = getIntent().getStringExtra("goalID");
    }

    //OnClick for the save button will trigger the next screen in the workflow
    public void nextButton(View view){
        Intent intent = new Intent(RefocusActivity.this, UserHomeActivity.class);
        intent.putExtra("goal", goal);
        intent.putExtra("goalID", goalID);
        startActivity(intent);
    }
}
