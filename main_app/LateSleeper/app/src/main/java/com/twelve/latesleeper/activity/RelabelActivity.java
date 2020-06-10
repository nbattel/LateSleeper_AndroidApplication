package com.twelve.latesleeper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.twelve.latesleeper.model.Goal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RelabelActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Goal goal;
    private static String goalID;
    TextView titleEditText;
    EditText bodyEditText;
    Button nextButton;
    Spinner dropdown;
    Boolean bodyTextComplete = false;
    //create a list of items for the spinner.
    String[] items = new String[]{"", "I feel anxious", "I feel nervous", "I fear missing out", "I am bored", "I don't care",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_relabel);
        titleEditText = findViewById(R.id.titleEditText);
        bodyEditText = findViewById(R.id.bodyEditText);
        nextButton = findViewById(R.id.nextButton);
        //Get the spinner from the xml.
        dropdown = findViewById(R.id.feelingDropdown);

        goal = (Goal) getIntent().getSerializableExtra("goal");
        goalID = getIntent().getStringExtra("goalID");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item ,items);
        //adapter.setDropDownViewResource(android.R.layout.);

        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        bodyEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    nextButton.setEnabled(false);
                    bodyTextComplete = false;
                }else {
                    bodyTextComplete = true;
                    if(bodyTextComplete && !dropdown.getSelectedItem().toString().equals("")){
                        nextButton.setEnabled(true);
                    }
                }

                if(dropdown.getSelectedItem().toString() == ""){
                    Toast.makeText(getApplicationContext(), "Please Select a dropdown option", Toast.LENGTH_SHORT).show();
                    nextButton.setEnabled(false);
                }else{
                    titleEditText.setText(dropdown.getSelectedItem().toString());
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

    public void nextButtonRelabel(View view){

        String title = titleEditText.getText().toString();
        String body = bodyEditText.getText().toString();
        String date = getDateAndTime();

        Entry entry = new Entry(body, title, date);

        if(dropdown.getSelectedItem().toString() == ""){
            Toast.makeText(getApplicationContext(), "Please Select a dropdown option", Toast.LENGTH_SHORT).show();
            nextButton.setEnabled(false);
        }else{
            // Getting a reference to the journal collection of the specific user
            CollectionReference journalCollection = Database.getDatabase().document("users/" + currentUser.getUid()).collection("goals")
                    .document(ViewSpecificGoalActivity.goalID).collection("relabelJournal");
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
                            Toast toast = Toast.makeText(getApplicationContext(), "Congratulations on completing the Relabel step!", Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(RelabelActivity.this, ReframeActivity.class);
                            intent.putExtra("goal", goal);
                            intent.putExtra("goalID", goalID);
                            startActivity(intent);
                        }
                        else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Failed writing to Journal!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
        }
    }

    public void cancelRelabel(View view){
        Intent intent = new Intent(RelabelActivity.this, UserHomeActivity.class);
        startActivity(intent);
    }

    public String getDateAndTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
