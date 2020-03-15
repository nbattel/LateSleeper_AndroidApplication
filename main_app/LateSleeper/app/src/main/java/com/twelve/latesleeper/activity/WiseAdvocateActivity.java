package com.twelve.latesleeper.activity;

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
    private int score;
    private int question = 0;

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
        setChoices();
        setQuestion();

        choice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                score += 0;
                if (isQuizOver())
                {
                   endQuiz();
                }
                else {
                    setQuestion();
                }
            }
        });
        choice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                score += 1;
                updateScore();
                if (isQuizOver())
                {
                    endQuiz();
                }
                else {
                    setQuestion();
                }
            }
        });
        choice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                score += 2;
                updateScore();
                if (isQuizOver())
                {
                    endQuiz();
                }
                else {
                    setQuestion();
                }
            }
        });
        choice4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                score += 3;
                updateScore();
                if (isQuizOver())
                {
                    endQuiz();
                }
                else {
                    setQuestion();
                }
            }
        });
        choice5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                score += 4;
                updateScore();
                if (isQuizOver())
                {
                    endQuiz();
                }
                else {
                    setQuestion();
                }
            }
        });
    }

    private void setChoices(){
        choice1.setText(questionLibrary.getChoice(0));
        choice2.setText(questionLibrary.getChoice(1));
        choice3.setText(questionLibrary.getChoice(2));
        choice4.setText(questionLibrary.getChoice(3));
        choice5.setText(questionLibrary.getChoice(4));
    }

    private void setQuestion(){
        mQuestionView.setText(questionLibrary.getQuestion(question++));
    }

    private void updateScore() {
        mScoreView.setText(Integer.toString(score));
    }

    private boolean isQuizOver() {
        return question == QuestionLibrary.mcQuestions.length;
    }

    private void endQuiz() {
        Intent intent = new Intent(WiseAdvocateActivity.this, QuizResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("finalScore", score);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}