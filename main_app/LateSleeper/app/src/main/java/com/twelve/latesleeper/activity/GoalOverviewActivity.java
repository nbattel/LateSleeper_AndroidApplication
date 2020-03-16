package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.twelve.latesleeper.R;

public class GoalOverviewActivity extends AppCompatActivity {

    TextView YOUTextView;
    TextView YOUTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);
        YOUTextView = (TextView) findViewById(R.id.YOUTextView);
        YOUTextView2 = (TextView) findViewById(R.id.YOUTextView2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        YOUTextView.setText(Html.fromHtml("It is important that <font color='#D81B60' size=30sp'>YOU</font> realize a need for a change.", Html.FROM_HTML_MODE_LEGACY));
        YOUTextView2.setText(Html.fromHtml("The first thing <font color='#D81B60' size=30sp'>YOU</font> need to do is jot down your current feelings.", Html.FROM_HTML_MODE_LEGACY));
    }

    public void continueButton(View view) {
        Intent intent = new Intent(GoalOverviewActivity.this, CreateGoalSettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void backButton(View view) {

    }

}
