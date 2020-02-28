package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.twelve.latesleeper.R;

public class MainActivity extends AppCompatActivity {

    // Variable to keep track of the amount of times the user touches the screen
    private int touchCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // On click function to move to the log in activity
    public void nextActivity(View view) {
        touchCount  = touchCount  + 1;

        // If the user taps twice, the activity will change to the login screen
<<<<<<< HEAD
        if (++touchCount == 2) {
=======
        if (touchCount == 2) {
            touchCount = 0;
>>>>>>> 5420476db11fa0e45f7890eb4cdc29977a859657
            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);
        }
    }
}

/* TEST CODE
*
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference user = db.collection("users").document("p0uS1ViEoRqPsrThI1rS");     //.collection("Journal");
        CollectionReference entries = user.collection("Journal");

            user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("USER", "DocumentSnapshot data: " + document.getData());
                        } else {
                            Log.d("ERROR", "No such document");
                        }
                    } else {
                        Log.d("ERROR", "get failed with ", task.getException());
                    }
                }
            });

            entries.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("JOURNAL", document.getId() + " => " + document.getString("date") + "->" + document.getString("title"));
                        }
                    } else {
                        Log.d("COLLECTION ERROR", "Error getting documents: ", task.getException());
                    }
                }
            });
* */