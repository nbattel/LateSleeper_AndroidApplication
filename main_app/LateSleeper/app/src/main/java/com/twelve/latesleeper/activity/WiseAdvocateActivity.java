package com.twelve.latesleeper.activity;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.twelve.latesleeper.R;

public class WiseAdvocateActivity extends AppCompatActivity {
   private QuestionLibrary questionLibrary = new QuestionLibrary();
   private TextView mScoreView;
   private TextView mQuestionView;
   private TextView choice1;
    private Button choice2;
    private Button choice3;
    private Button choice4;
    private Button choice5;
    private int score =0;
    private int question =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplechoice);
        mScoreView = findViewById(R.id.score);
        mQuestionView =findViewById(R.id.question);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        choice5 = findViewById(R.id.choice5);
        chooseQuestion();

        choice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                score = score;
                updateScore(score);
                if (isQuizOver() == true)
                {
                    Intent intent = new Intent(WiseAdvocateActivity.this, QuizResultsActivity.class);
                   // intent.putExtra("finalScore",score);

                    Bundle bundle = new Bundle();
                    bundle.putInt("finalScore", score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    chooseQuestion();
                }
            }
        });
        choice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                score = score +1;
                updateScore(score);
                if (isQuizOver() == true)
                {
                    Intent intent = new Intent(WiseAdvocateActivity.this, QuizResultsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("finalScore", score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    chooseQuestion();
                }
            }
        });
        choice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                score = score +2;
                updateScore(score);
                if (isQuizOver() == true)
                {
                    Intent intent = new Intent(WiseAdvocateActivity.this, QuizResultsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("finalScore", score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    chooseQuestion();
                }
            }
        });
        choice4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                score = score +3;
                updateScore(score);

                if (isQuizOver() == true)
                {
                    Intent intent = new Intent(WiseAdvocateActivity.this, QuizResultsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("finalScore", score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    chooseQuestion();
                }
            }
        });
        choice5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                score = score +4;
                updateScore(score);
                if (isQuizOver() == true)
                {
                    Intent intent = new Intent(WiseAdvocateActivity.this, QuizResultsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("finalScore", score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else {
                    chooseQuestion();
                }
            }
        });




    }

    private void chooseQuestion(){
        System.out.println(isQuizOver()+ "question: "+question);

        mQuestionView.setText(questionLibrary.getQuestion(question));
       choice1.setText(questionLibrary.getChoice1(question));
        choice2.setText(questionLibrary.getChoice2(question));
       choice3.setText(questionLibrary.getChoice3(question));
        choice4.setText(questionLibrary.getChoice4(question));
        choice5.setText(questionLibrary.getChoice5(question));
        question++;
    }
    private void updateScore(int point) {
        mScoreView.setText("" + score);
    }

    private boolean isQuizOver()
    {
        if (question == QuestionLibrary.mcQuestions.length)
        {
            return true;

        }
        else{return false;}
    }


}
