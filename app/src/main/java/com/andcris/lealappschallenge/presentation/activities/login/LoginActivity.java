package com.andcris.lealappschallenge.presentation.activities.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.andcris.lealappschallenge.databinding.ActivityLoginBinding;
import com.andcris.lealappschallenge.presentation.activities.home.HomeActivity;
import com.andcris.lealappschallenge.presentation.activities.register.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding activityLoginBinding;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityLoginBinding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        activityLoginBinding.loginActivityBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = activityLoginBinding.loginActivityTilEmail.getEditText().getText().toString().trim();
                String password = activityLoginBinding.loginActivityTilPassword.getEditText().getText().toString().trim();
                if (validateEmail(email) & validatePassword(password)) {
                    progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Carregando...");
                    progressDialog.show();
                    loginUser(email, password);
                }
            }
        });

        activityLoginBinding.loginActivityTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    public boolean validateEmail(String email) {
        if (email.isEmpty()) {
            activityLoginBinding.loginActivityTilEmail.setError("Email não pode ser vazio");
            return false;
        } else {
            activityLoginBinding.loginActivityTilEmail.setError(null);
            return true;
        }
    }

    public boolean validatePassword(String password) {
        if (password.isEmpty()) {
            activityLoginBinding.loginActivityTilPassword.setError("Senha não pode ser vazia");
            return false;
        } else {
            activityLoginBinding.loginActivityTilPassword.setError(null);
            return true;
        }
    }

    public void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(progressDialog.isShowing()) progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Email ou Senha incorretos.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}