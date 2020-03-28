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
    TextView badHabitText;
    TextView fourStepsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_overview);
        YOUTextView = (TextView) findViewById(R.id.YOUTextView);
        YOUTextView2 = (TextView) findViewById(R.id.YOUTextView2);
        badHabitText = (TextView) findViewById(R.id.badHabitTextView);
        fourStepsText = (TextView) findViewById(R.id.fourStepsTextView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        YOUTextView.setText(Html.fromHtml("It is important that <font color='#D81B60' size=30sp'>YOU</font> realize a need for a change.", Html.FROM_HTML_MODE_LEGACY));
        YOUTextView2.setText(Html.fromHtml("The first thing <font color='#D81B60' size=30sp'>YOU</font> need to do is jot down your current feelings.", Html.FROM_HTML_MODE_LEGACY));
        badHabitText.setText(Html.fromHtml("In the future, you can come back to what you wrote down and reflect on how <font color='#D81B60' size=30sp'>a bad habit negatively affects your life</font>.", Html.FROM_HTML_MODE_LEGACY));
        fourStepsText.setText(Html.fromHtml("Once you create your goal, you can go through the <font color='#D81B60' size=30sp'>four step process</font> before going to sleep. You will receive a <font color='#D81B60' size=30sp'>push notification</font> when it is time to stop using your phone, where you can <font color='#D81B60' size=30sp'>set an alarm</font> for the next day.", Html.FROM_HTML_MODE_LEGACY));
    }

    public void continueButton(View view) {
        Intent intent = new Intent(GoalOverviewActivity.this, CreateGoalSettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

}
