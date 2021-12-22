package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityAddEditWorkoutBinding;
import com.andcris.lealappschallenge.databinding.ActivityLoginBinding;
import com.andcris.lealappschallenge.utils.Util;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityLoginBinding.getRoot());

        activityLoginBinding.loginActivityBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = activityLoginBinding.loginActivityTilEmail.getEditText().getText().toString().trim();
                String password = activityLoginBinding.loginActivityTilPassword.getEditText().getText().toString().trim();
                if (validateEmail(email) & validatePassword(password)) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
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
}