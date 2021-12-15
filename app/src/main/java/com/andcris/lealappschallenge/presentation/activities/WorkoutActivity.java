package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityWorkoutBinding;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityWorkoutBinding activityWorkoutBinding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityWorkoutBinding.getRoot());
    }
}