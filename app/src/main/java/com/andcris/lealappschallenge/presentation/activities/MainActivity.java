package com.andcris.lealappschallenge.presentation.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.andcris.lealappschallenge.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityMainBinding.getRoot());

        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
}