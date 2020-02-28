package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;
import com.twelve.latesleeper.model.Entry;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JournalActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText bodyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
    }

    public void toRelabel(View view){
        Intent intent = new Intent(JournalActivity.this, RelabelActivity.class);
        startActivity(intent);
    }


    public void writeToJournal(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "Writing to Journal!", Toast.LENGTH_SHORT);
        toast.show();

        titleEditText = findViewById(R.id.titleEditText);
        bodyEditText = findViewById(R.id.bodyEditText);

        String title = titleEditText.getText().toString();
        String body = bodyEditText.getText().toString();
        String date = getDateAndTime();

        Entry entry = new Entry(body, title, date);

        Database.addEntry("b7rTinkMBp2NVenxTNkM", entry);
    }

    public String getDateAndTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
