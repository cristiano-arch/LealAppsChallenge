package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andcris.lealappschallenge.databinding.ActivityHomeBinding;
import com.andcris.lealappschallenge.databinding.ActivityMainBinding;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityHomeBinding activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityHomeBinding.getRoot());

        activityHomeBinding.homeActivityBtWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Workouts", Toast.LENGTH_SHORT).show();
            }
        });

        activityHomeBinding.homeActivityBtExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Exercises", Toast.LENGTH_SHORT).show();
            }
        });
    }
}