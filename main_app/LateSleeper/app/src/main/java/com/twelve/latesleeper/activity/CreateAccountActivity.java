package com.twelve.latesleeper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;
import com.twelve.latesleeper.model.User;

public class CreateAccountActivity extends AppCompatActivity {

    EditText txtEmail, txtPass, txtPassConfirm, txtFirstName, txtLastName,
                txtStreetAddress, txtCity, txtProvince, txtPhoneNumber;
    Button btnConfirm;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPass = (EditText) findViewById(R.id.txtPass);
        txtPassConfirm = (EditText) findViewById(R.id.txtPassConfirm);
        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        txtStreetAddress = (EditText) findViewById(R.id.txtStreetAddress);
        txtCity = (EditText) findViewById(R.id.txtCity);
        txtProvince = (EditText) findViewById(R.id.txtProvince);
        txtPhoneNumber = (EditText) findViewById(R.id.txtPhoneNumber);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
    }

    public void createAccount(View view){


        // Adding the user to our database, this will also set the current user
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User(txtEmail.getText().toString(), txtPass.getText().toString(), txtPassConfirm.getText().toString(), txtFirstName.getText().toString(), txtLastName.getText().toString(),
                        txtStreetAddress.getText().toString(), txtCity.getText().toString(), txtProvince.getText().toString(), txtPhoneNumber.getText().toString());
                Database.addUser(user);
            }
        });
        //CurrentUser.setUser(user);

        //System.out.println(CurrentUser.getJournal());
    }
}
