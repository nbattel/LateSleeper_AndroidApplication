package com.twelve.latesleeper.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.twelve.latesleeper.model.Entry;
import com.twelve.latesleeper.model.User;

public class Database {

    private static FirebaseFirestore database;
    private static CollectionReference userCollection;
    private static int size;

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

    public static void retrieveJournalCollection(String id) {
        userCollection.document(id).collection("journal")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("DOCUMENT", document.getId() + "=>" + document.getData());
                            }
                        } else {
                            Log.d("FAILEDDOCUMENT", "Error getting documents.", task.getException());
                        }
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