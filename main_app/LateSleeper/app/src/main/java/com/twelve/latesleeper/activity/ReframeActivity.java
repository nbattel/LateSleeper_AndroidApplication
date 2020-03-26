package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Goal;

import static java.lang.Integer.parseInt;

public class ReframeActivity extends AppCompatActivity {

    private String mcChoices[] = {"", "0 (Never)", "1 (Rarely)", "2 (Sometimes)","3 (Often)", "4 (Always)"};
    private Goal goal;
    private static String goalID;
    Spinner dropdown1;
    Spinner dropdown2;
    Spinner dropdown3;
    Spinner dropdown4;
    Button nextButton;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reframe);
        nextButton = findViewById(R.id.nextButton);
        dropdown1 = findViewById(R.id.question1Dropdown);
        dropdown2 = findViewById(R.id.question2Dropdown);
        dropdown3 = findViewById(R.id.question3Dropdown);
        dropdown4 = findViewById(R.id.question4Dropdown);

        goal = (Goal) getIntent().getSerializableExtra("goal");
        goalID = getIntent().getStringExtra("goalID");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item , mcChoices);

        dropdown1.setAdapter(adapter);
        dropdown2.setAdapter(adapter);
        dropdown3.setAdapter(adapter);
        dropdown4.setAdapter(adapter);
    }

    //OnClick for the save button will trigger the next screen in the workflow
    public void nextButtonReframe(View view){

        if(dropdown1.getSelectedItem().toString() == "" || dropdown2.getSelectedItem().toString() == "" || dropdown3.getSelectedItem().toString() == "" || dropdown4.getSelectedItem().toString() == ""){
            Toast.makeText(getApplicationContext(), "Please complete all questions.", Toast.LENGTH_SHORT).show();
        }else{
            for(int i = 0; i < mcChoices.length; i++){
                if(dropdown1.getSelectedItem().toString().equals(mcChoices[i])){
                    score += parseInt(mcChoices[i].substring(0,1));
                }
                if(dropdown2.getSelectedItem().toString().equals(mcChoices[i])){
                    score += parseInt(mcChoices[i].substring(0,1));
                }
                if(dropdown3.getSelectedItem().toString().equals(mcChoices[i])){
                    score += parseInt(mcChoices[i].substring(0,1));
                }
                if(dropdown4.getSelectedItem().toString().equals(mcChoices[i])){
                    score += parseInt(mcChoices[i].substring(0,1));
                }
            }

            Intent intent = new Intent(ReframeActivity.this, QuizResultsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("finalScore", score);
            intent.putExtra("goal", goal);
            intent.putExtra("goalID", goalID);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
