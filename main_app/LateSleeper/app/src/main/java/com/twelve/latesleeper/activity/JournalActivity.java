package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Entry;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JournalActivity extends AppCompatActivity {

    EditText titleEditText = findViewById(R.id.titleEditText);
    EditText bodyEditText = findViewById(R.id.bodyEditText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
    }

    public void writeToJournal(View view){
        String title = titleEditText.getText().toString();
        String body = bodyEditText.getText().toString();
        String date = getDateAndTime();

        Entry entry = new Entry(body, title, date);
    }

    public String getDateAndTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
