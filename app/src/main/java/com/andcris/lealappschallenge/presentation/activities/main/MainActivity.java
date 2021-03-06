package com.andcris.lealappschallenge.presentation.activities.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityMainBinding;
import com.andcris.lealappschallenge.presentation.activities.home.HomeActivity;
import com.andcris.lealappschallenge.presentation.activities.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        rodarSplashScreen();
        setContentView(activityMainBinding.getRoot());

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }
        finish();
    }

    public void rodarSplashScreen() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_LealAppsChallenge);
    }
}