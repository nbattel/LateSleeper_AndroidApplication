package com.twelve.latesleeper.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.twelve.latesleeper.model.Entry;
import com.twelve.latesleeper.model.User;

public class Database {

    private static FirebaseFirestore database;
    private static CollectionReference userCollection;

    static {
        database = FirebaseFirestore.getInstance();
        userCollection = database.collection("users");
    }

    public static FirebaseFirestore getDatabase() { return database; }

    // Function for adding a new user to the database and storing their generated ID in the CurrentUser class
    public static void addUser(User user, String id){
        userCollection.document(id)
                .set(user.getUser())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.w("SUCCESS", ".addUser() has succeeded");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FAILURE", ".addUser() has failed", e);
                    }
                });
    }

    // Function for adding a new entry document to a journal collection of a specified user
    public static void addEntry(String uniqueUserId, Entry entry){
        // Getting a reference to the journal collection of the specific user
        CollectionReference journalCollection = userCollection.document(uniqueUserId).collection("journal");
        // Add entry to user
        journalCollection.add(entry.getEntry());
    }
}









////////////////////////////////////////////////////////////////////////////////////////////////////
//        journalCollection.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d("DOCUMENT", "DocumentSnapshot data: " + document.getData());
//                    } else {
//                        Log.d("DOCUMENT", "No such document");
//                    }
//                } else {
//                    Log.d("ERROR", "get failed with ", task.getException());
//                }
//            }
//        });