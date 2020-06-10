package com.twelve.latesleeper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;
import com.twelve.latesleeper.model.Entry;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RevalueActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    EditText titleEditText;
    EditText bodyEditText;
    Button nextButtonRevalue;
    Boolean titleTextComplete = false;
    Boolean bodyTextComplete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revalue);
        mAuth = FirebaseAuth.getInstance();
        titleEditText = findViewById(R.id.journalTitle);
        bodyEditText = findViewById(R.id.journalBody);
        nextButtonRevalue = findViewById(R.id.nextButtonRevalue);

        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    nextButtonRevalue.setEnabled(false);
                    titleTextComplete = false;
                }else {
                    titleTextComplete = true;
                    if(bodyTextComplete && titleTextComplete){
                        nextButtonRevalue.setEnabled(true);
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
                    nextButtonRevalue.setEnabled(false);
                    bodyTextComplete = false;
                }else {
                    bodyTextComplete = true;
                    if(bodyTextComplete && titleTextComplete){
                        nextButtonRevalue.setEnabled(true);
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

    public void nextButtonRevalue(View view){

        String title = titleEditText.getText().toString();
        String body = bodyEditText.getText().toString();
        String date = getDateAndTime();

        Entry entry = new Entry(body, title, date);

        // Getting a reference to the journal collection of the specific user
        CollectionReference journalCollection = Database.getDatabase().document("users/" + currentUser.getUid()).collection("goals")
                .document(ViewSpecificGoalActivity.goalID).collection("revalueJournal");
        // Add entry to user
        journalCollection.add(entry.getEntry())
                .addOnCompleteListener(this, new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Database.getDatabase().document("users/" + currentUser.getUid())
                                    .update(
                                            "journals", FieldValue.increment(1)
                                    );
                            Toast toast = Toast.makeText(getApplicationContext(), "Congratulations on completing the Revalue step!", Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(RevalueActivity.this, UserHomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Failed writing to Journal!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
    }

    public String getDateAndTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
