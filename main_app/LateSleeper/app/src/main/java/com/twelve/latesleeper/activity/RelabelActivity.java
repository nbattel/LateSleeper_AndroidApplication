package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.twelve.latesleeper.R;

public class RelabelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relabel);
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner2);

//create a list of items for the spinner.
        String[] items = new String[]{"I feel anxious", "I feel nervous", "I fear of missing out", "I am bored"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item ,items);
        //adapter.setDropDownViewResource(android.R.layout.);

        dropdown.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP); //PorterDuff.Mode.SRC_ATOP);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        //dropdown.setOnItemSelectedListener(onItemSelected());
    }

    //OnClick for the save button will trigger the next screen in the workflow
    public void nextButtonRelabel(View view){
        Intent intent = new Intent(RelabelActivity.this, ReframeActivity.class);
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(),"Relabel Next Button Clicked",Toast.LENGTH_SHORT);
        toast.show();
        //TODO
        //We need to save all the information the user enters on this screen into the database on save
    }

   /* public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }*/
    //OnClick for the cancel button will not save any information
    public void backButtonRelabel(View view){
        Intent intent = new Intent(RelabelActivity.this, JournalActivity.class);
        startActivity(intent);

        Toast toast = Toast.makeText(getApplicationContext(),"Relabel Back Button Clicked",Toast.LENGTH_SHORT);
        toast.show();
    }
}
