package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityLoginBinding;
import com.andcris.lealappschallenge.utils.Util;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityLoginBinding activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityLoginBinding.getRoot());

        activityLoginBinding.loginActivityBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }
        });

        activityLoginBinding.loginActivityTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}