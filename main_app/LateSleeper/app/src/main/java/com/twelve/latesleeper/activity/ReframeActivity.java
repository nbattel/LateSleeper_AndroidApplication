package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.twelve.latesleeper.R;

public class ReframeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reframe);

    }

    /*public void toMultipleChoice(View view)
    {
        Intent intent = new Intent(ReframeActivity.this, WiseAdvocateActivity.class);
        startActivity(intent);
    }*/

    //OnClick for the save button will trigger the next screen in the workflow
    public void nextButtonReframe(View view){
        Intent intent = new Intent(ReframeActivity.this, RefocusActivity.class);
        startActivity(intent);

        //TODO --> We need to save all the information the user enters on this screen into the database on save
    }

    //OnClick for the cancel button will not save any information
    public void backButtonExit(View view){
        Intent intent = new Intent(ReframeActivity.this, UserHomeActivity.class);
        startActivity(intent);
    }
}
