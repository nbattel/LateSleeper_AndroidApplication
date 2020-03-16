package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.QuerySnapshot;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;


public class UserHomeActivity extends AppCompatActivity {
    TextView goalsSet;
    //TextView getGoalsCompleted;
    TextView goalsCompleted;
    TextView consecutiveDays;
    TextView totalEntries;
    TextView welcomeBack;
    //we have to access our database to get the data, then modify all of these textfields to have the updated info
    //this should be done whenever the activity is instantiated


    private FirebaseAuth mAuth;
    private TextView welcomeText;
    private int entries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_userhome);
        welcomeText = (TextView) findViewById(R.id.welcomeText);
        //TODO
        //Get the users username
        goalsCompleted = findViewById(R.id.goalsCompleted);
        goalsSet = findViewById(R.id.goalsSet);
        consecutiveDays = findViewById(R.id.daysConsec);
        totalEntries = findViewById(R.id.totalEntries);
        welcomeBack = findViewById(R.id.welcomeText);
        //now modify the text based on information about user in database
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        retrieveJournalAmount(currentUser.getUid());
        updateUI(currentUser);
    }

    public void signOut(View view) {
        mAuth.signOut();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    // On click function to log the user into their account
    public void toCreateGoal(View view){ //this needs to change
        Intent intent = new Intent(UserHomeActivity.this, RelabelActivity.class);
        //NEED to verify user info is valid in order to login - do this after
        startActivity(intent);

    }

    public void toViewGoals(View view){
        Intent intent = new Intent(UserHomeActivity.this, ViewGoalsActivity.class);
        startActivity(intent);
    }


    public void retrieveJournalAmount(String id) {
        Database.getDatabase().collection("users").document(id).collection("journal")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            entries = task.getResult().size();
                            totalEntries.setText("Total Entries Submitted: " + entries);
                            Log.d("TESTSIZE", task.getResult().size() + "");
                        } else {
                            Log.d("FAILEDDOCUMENT", "Error getting documents.", task.getException());
                        }
                    }
                });
    }


    public void updateUI(FirebaseUser user) {
        if (user != null) {
            welcomeText.setText("Welcome, " + user.getDisplayName());
        } else {
            Intent intent = new Intent(UserHomeActivity.this, LogInActivity.class);
            startActivity(intent);
        }
    }

}
