package com.twelve.latesleeper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;
import com.twelve.latesleeper.model.User;
import com.twelve.latesleeper.user.CurrentUser;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void createAccount(View view){
        User user = new User("milosarsik", "milosarsic1", "milosarsic14@gmail.com",
                "Milos", "Arsik", "1377 South Wenige Drive", "London", "Ontario",
                "N5X 4N1", "519-432-6353");

        // Adding the user to our database, this will also set the current user
        Database.addUser(user);

        //CurrentUser.setUser(user);

        //System.out.println(CurrentUser.getJournal());
    }
}
