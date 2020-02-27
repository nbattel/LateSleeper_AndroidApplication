package com.twelve.latesleeper.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.twelve.latesleeper.model.Entry;

public class Database {

    private static FirebaseFirestore database;

    static {
        database = FirebaseFirestore.getInstance();
    }

    public static void addEntry(String uniqueUserId, Entry entry){
        DocumentReference docRef = database.collection("users").document(uniqueUserId);
        System.out.println(docRef.get());

//
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d("DATA", "DocumentSnapshot data: " + document.getData());
//                    } else {
//                        Log.d("DATA", "No such document");
//                    }
//                } else {
//                    Log.d("DATA", "get failed with ", task.getException());
//                }
//            }
//        });


    }
}
