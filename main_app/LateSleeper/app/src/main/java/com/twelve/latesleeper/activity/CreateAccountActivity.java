package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.twelve.latesleeper.R;
import com.twelve.latesleeper.database.Database;
import com.twelve.latesleeper.model.User;

public class CreateAccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText txtEmail, txtPass, txtPassConfirm, txtFirstName, txtLastName,
                txtStreetAddress, txtCity, txtProvince, txtPhoneNumber;
    Button btnConfirm;
    ProgressBar loadingBar;
    ConstraintLayout dimLayout;
    User newUser;
    Boolean confirmPasswordComplete = false;
    Boolean passwordComplete = false;
    Boolean emailComplete = false;
    private static final String TAG = "CreateACC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mAuth = FirebaseAuth.getInstance();

        loadingBar = findViewById(R.id.loadingBar);
        dimLayout = findViewById(R.id.dimLayout);

        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        txtPassConfirm = findViewById(R.id.txtPassConfirm);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtStreetAddress = findViewById(R.id.txtStreetAddress);
        txtCity = findViewById(R.id.txtCity);
        txtProvince = findViewById(R.id.txtProvince);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setEnabled(false);

        txtPass.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btnConfirm.setEnabled(false);
                    passwordComplete = false;
                }else {
                    passwordComplete = true;
                    if(passwordComplete && emailComplete && confirmPasswordComplete){
                        btnConfirm.setEnabled(true);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        txtPassConfirm.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btnConfirm.setEnabled(false);
                    confirmPasswordComplete = false;
                }else {
                    confirmPasswordComplete = true;
                    if(confirmPasswordComplete && emailComplete && passwordComplete){
                        btnConfirm.setEnabled(true);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        txtEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btnConfirm.setEnabled(false);
                    emailComplete = false;
                }else {
                    emailComplete = true;
                    if(passwordComplete && emailComplete && confirmPasswordComplete){
                        btnConfirm.setEnabled(true);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void createAccount(View view) {
        // Adding the user to our database, this will also set the current user
        if(!txtPass.getText().toString().equals(txtPassConfirm.getText().toString())){
            Toast.makeText(CreateAccountActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
        }
        else{
            dimLayout.bringToFront();
            loadingBar.bringToFront();
            loadingBar.setVisibility(View.VISIBLE);
            dimLayout.setVisibility(View.VISIBLE);
            newUser = new User(txtEmail.getText().toString(), txtFirstName.getText().toString(), txtLastName.getText().toString(),
                    txtStreetAddress.getText().toString(), txtCity.getText().toString(), txtProvince.getText().toString(), txtPhoneNumber.getText().toString());
            firebaseNewAcc(txtEmail.getText().toString(), txtPass.getText().toString());
        }
    };

    public void firebaseNewAcc(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            final FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(newUser.getUser().get("firstName")).build();
                            Log.d("TEST", newUser.getUser().get("firstName"));
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                Log.d(TAG, "Display name set!");
                                                Database.addUser(newUser, user.getUid());
                                                updateUI(user);
                                            }
                                            else {
                                                loadingBar.setVisibility(View.INVISIBLE);
                                                dimLayout.setVisibility(View.INVISIBLE);
                                                Log.d(TAG, "Error setting name!");
                                            }
                                        }
                                    });
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            loadingBar.setVisibility(View.INVISIBLE);
                            dimLayout.setVisibility(View.INVISIBLE);
                            Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser currentUser) {
        if (currentUser == null) {

        }
        else {
            Intent intent = new Intent(CreateAccountActivity.this, UserHomeActivity.class);
            loadingBar.setVisibility(View.INVISIBLE);
            dimLayout.setVisibility(View.INVISIBLE);
            startActivity(intent);
        }
    }
}
