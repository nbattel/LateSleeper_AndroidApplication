package com.twelve.latesleeper.activity;

import android.content.Intent;
import android.os.Bundle;
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
    private static final String TAG = "CreateACC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mAuth = FirebaseAuth.getInstance();

        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        dimLayout = (ConstraintLayout) findViewById(R.id.dimLayout);

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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void createAccount(View view) {
        // Adding the user to our database, this will also set the current user
        dimLayout.bringToFront();
        loadingBar.bringToFront();
        loadingBar.setVisibility(View.VISIBLE);
        dimLayout.setVisibility(View.VISIBLE);
        newUser = new User(txtEmail.getText().toString(), txtFirstName.getText().toString(), txtLastName.getText().toString(),
                        txtStreetAddress.getText().toString(), txtCity.getText().toString(), txtProvince.getText().toString(), txtPhoneNumber.getText().toString());
        firebaseNewAcc(txtEmail.getText().toString(), txtPass.getText().toString());
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
