package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.model.Entry;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JournalActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    EditText titleEditText;
    EditText bodyEditText;
    Button journalButton;
    Boolean titleTextComplete = false;
    Boolean bodyTextComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_journal);
        titleEditText = findViewById(R.id.titleEditText);
        bodyEditText = findViewById(R.id.bodyEditText);
        journalButton = findViewById(R.id.writeToJournalButton);

        Toast toast = Toast.makeText(getApplicationContext(), "Please fill in all fields to write to the Journal.", Toast.LENGTH_LONG);
        toast.show();

        titleEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    journalButton.setEnabled(false);
                    titleTextComplete = false;
                }else {
                    titleTextComplete = true;
                    if(titleTextComplete && bodyTextComplete){
                        journalButton.setEnabled(true);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        bodyEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    journalButton.setEnabled(false);
                    bodyTextComplete = false;
                }else {
                    bodyTextComplete = true;
                    if(titleTextComplete && bodyTextComplete){
                        journalButton.setEnabled(true);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
    }

    public void writeToJournal(View view){

        String title = titleEditText.getText().toString();
        String body = bodyEditText.getText().toString();
        String date = getDateAndTime();

        Entry entry = new Entry(body, title, date);

        // Getting a reference to the journal collection of the specific user
        CollectionReference journalCollection = Database.getDatabase().document("users/" + currentUser.getUid()).collection("journal");
        // Add entry to user
        journalCollection.add(entry.getEntry())
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Wrote to Journal!", Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(JournalActivity.this, RelabelActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Failed writing to Journal!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
    }

    public void cancelJournal(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "Cancelling Create New Goal!", Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(JournalActivity.this, UserHomeActivity.class);
        startActivity(intent);
    }

    public String getDateAndTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
