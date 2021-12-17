package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityExerciseBinding;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityExerciseBinding activityExerciseBinding = ActivityExerciseBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityExerciseBinding.getRoot());
    }
}