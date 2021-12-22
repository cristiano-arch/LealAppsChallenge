package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.andcris.lealappschallenge.databinding.ActivityRegisterBinding;
import com.andcris.lealappschallenge.utils.Util;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding activityRegisterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityRegisterBinding.getRoot());

        activityRegisterBinding.registerActivityBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = activityRegisterBinding.registerActivityTilEmail.getEditText().getText().toString().trim();
                String password = activityRegisterBinding.registerActivityTilPassword.getEditText().getText().toString().trim();
                if (validateEmail(email) & validatePassword(password)) {
                    finish();
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
}