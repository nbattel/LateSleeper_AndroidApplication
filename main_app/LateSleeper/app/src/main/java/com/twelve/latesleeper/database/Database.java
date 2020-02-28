package com.twelve.latesleeper.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.twelve.latesleeper.model.Entry;

public class Database {

    private static FirebaseFirestore database;
    private static CollectionReference userCollection;

    static {
        database = FirebaseFirestore.getInstance();
        userCollection = database.collection("users");
    }

    public static void addEntry(String uniqueUserId, Entry entry){
        CollectionReference journalCollection = userCollection.document(uniqueUserId).collection("journal");

        // Add entry to user
        journalCollection.add(entry.getEntry());
//
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

    }
}
