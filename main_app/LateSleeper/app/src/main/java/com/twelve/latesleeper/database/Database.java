package com.twelve.latesleeper.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.twelve.latesleeper.model.Entry;
import com.twelve.latesleeper.model.Journal;
import com.twelve.latesleeper.model.User;
import com.twelve.latesleeper.user.CurrentUser;

public class Database {

    private static FirebaseFirestore database;
    private static CollectionReference userCollection;
    private static Journal tempJournal;

    static {
        database = FirebaseFirestore.getInstance();
        userCollection = database.collection("users");
        tempJournal = new Journal();
    }

    // Function for adding a new user to the database and storing their generated ID in the CurrentUser class
    public static void addUser(User user){
        userCollection.add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println(documentReference.getId());

                        // Empty entry
                        Entry entry = new Entry(" "," "," ");

                        // Adding a journal collection to the newly created user
                        CollectionReference temp = userCollection.document(documentReference.getId()).collection("journal");

                        temp.add(entry);

                        // Setting the CurrentUser Id to the newly signed in user
                        CurrentUser.setID(documentReference.getId());

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
        journalCollection.add(entry);
    }

    public static Journal getJournal(String uniqueUserId){
        // Wiping any previous journal we got
        tempJournal.wipe();

        userCollection.document(uniqueUserId).collection("journal")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("SUCCESS", document.getId() + " => " + document.getData());

                                // Create new Entry object
                                Entry entry = new Entry(document.getString("body"),
                                        document.getString("title"), document.getString("date"));

                                // Add entry object to the tempJournal
                                tempJournal.addEntry(entry);
                            }
                        } else {
                            Log.d("FAILURE", "Error getting documents: ", task.getException());
                        }
                    }
                });

        return tempJournal;
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