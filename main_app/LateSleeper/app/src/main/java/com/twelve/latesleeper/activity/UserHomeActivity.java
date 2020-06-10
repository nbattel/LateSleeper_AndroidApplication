package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.media.MediaPlayer;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.Said;
import com.twelve.latesleeper.database.Database;

import static java.lang.Math.toIntExact;


public class UserHomeActivity extends AppCompatActivity {
    TextView goalsSet;
    TextView goalsCompleted;
    TextView totalEntries;
    TextView welcomeBack;
    //we have to access our database to get the data, then modify all of these textfields to have the updated info
    //this should be done whenever the activity is instantiated


    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextView welcomeText;
    private int count = 0;

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
        totalEntries = findViewById(R.id.totalEntries);
        welcomeBack = findViewById(R.id.welcomeText);
        //now modify the text based on information about user in database

        if(Said.welcomeVoiceSay == false) {
            MediaPlayer welcomeVoice = MediaPlayer.create(UserHomeActivity.this, R.raw.dashboardwelcome);
            welcomeVoice.start();
            Said.setWelcomeVoiceSay(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        retrieveJournalAmount(currentUser.getUid());
        updateUI(currentUser);
    }

    public void signOut(View view) {
        mAuth.signOut();
        currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    // On click function to log the user into their account
    public void toCreateGoal(View view){
        Intent intent = new Intent(UserHomeActivity.this, GoalOverviewActivity.class);
        //NEED to verify user info is valid in order to login - do this after
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

    }

    public void toViewGoals(View view){
        Intent intent = new Intent(UserHomeActivity.this, ViewGoalsActivity.class);
        startActivity(intent);
    }

    public void toAccelerometer(View view){
        Intent intent = new Intent(UserHomeActivity.this, AccelerometerActivity.class);
        startActivity(intent);
    }


    public void retrieveJournalAmount(String id) {
        Database.getDatabase().collection("users").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().contains("journals")) count = toIntExact(task.getResult().getLong("journals"));
                            else count = 0;
                            totalEntries.setText("Total Entries Submitted" + "\n" + count);
                            Log.d("TESTSIZE", count + "");
                        } else {
                            Log.d("FAILEDDOCUMENT", "Error getting documents.", task.getException());
                        }
                    }
                });
        Database.getDatabase().collection("users").document(id).collection("goals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            count = task.getResult().size();
                            goalsSet.setText("Goals Set" + "\n" + count);
                            Log.d("TESTSIZE", task.getResult().size() + "");
                        } else {
                            Log.d("FAILEDDOCUMENT", "Error getting documents.", task.getException());
                        }
                    }
                });
        Database.getDatabase().collection("users").document(id).collection("goals")
                .whereEqualTo("completed", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            count = task.getResult().size();
                            goalsCompleted.setText("Goals Completed" + "\n" + count);
                            Log.d("TESTSIZE", task.getResult().size() + "");
                        } else {
                            Log.d("FAILEDDOCUMENT", "Error getting documents.", task.getException());
                        }
                    }
                });
    }


    public void updateUI(FirebaseUser user) {
        if (user != null) {
            welcomeText.setText(user.getDisplayName());
        } else {
            Intent intent = new Intent(UserHomeActivity.this, LogInActivity.class);
            startActivity(intent);
        }
    }

}
