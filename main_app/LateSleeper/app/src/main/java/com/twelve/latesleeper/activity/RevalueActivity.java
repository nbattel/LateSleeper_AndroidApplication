package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.twelve.latesleeper.R;

public class RevalueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revalue);
    }

    //OnClick for the save button will trigger the next screen in the workflow
    public void nextButtonRevalue(View view){

        if(Flag.getIsGoalComplete()){
            //Add Gratification screen into workflow HERE
        }
        else{
            Intent intent = new Intent(RevalueActivity.this, RelabelActivity.class);
            startActivity(intent);
        }

        Toast toast = Toast.makeText(getApplicationContext(),"Revalue Next Button Clicked",Toast.LENGTH_SHORT);
        toast.show();
        //TODO
        //We need to save all the information the user enters on this screen into the database on save
    }

    //OnClick for the cancel button will not save any information
    public void backButtonRevalue(View view){
        Intent intent = new Intent(RevalueActivity.this, RefocusActivity.class);
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(),"Revalue Back Button Clicked",Toast.LENGTH_LONG);
        toast.show();
    }
}
