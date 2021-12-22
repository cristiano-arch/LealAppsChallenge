package com.andcris.lealappschallenge.presentation.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.andcris.lealappschallenge.databinding.ActivityRegisterBinding;
import com.andcris.lealappschallenge.utils.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private ActivityRegisterBinding activityRegisterBinding;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityRegisterBinding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        activityRegisterBinding.registerActivityBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = activityRegisterBinding.registerActivityTilEmail.getEditText().getText().toString().trim();
                String password = activityRegisterBinding.registerActivityTilPassword.getEditText().getText().toString().trim();
                if (validateEmail(email) & validatePassword(password)) {
                    progressDialog = new ProgressDialog(RegisterActivity.this);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Carregando...");
                    progressDialog.show();
                    createUser(email, password);
                }
            }
        });

        activityRegisterBinding.registerActivityTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public boolean validateEmail(String email) {
        if (email.isEmpty()) {
            activityRegisterBinding.registerActivityTilEmail.setError("Email não pode ser vazio");
            return false;
        } else {
            if (!Util.isEmailValid(email)) {
                activityRegisterBinding.registerActivityTilEmail.setError("Email inválido");
                return false;
            } else {
                activityRegisterBinding.registerActivityTilEmail.setError(null);
                return true;
            }
        }
    }

    public boolean validatePassword(String password) {
        if (password.isEmpty()) {
            activityRegisterBinding.registerActivityTilPassword.setError("Senha não pode ser vazia");
            return false;
        } else {
            if (password.length() < 6) {
                activityRegisterBinding.registerActivityTilPassword.setError("Senha deve ter pelo menos seis caracteres");
                return false;
            } else {
                activityRegisterBinding.registerActivityTilPassword.setError(null);
                return true;
            }
        }
    }

    public void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(progressDialog.isShowing()) progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Usuário cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Oops! Algo deu errado!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}