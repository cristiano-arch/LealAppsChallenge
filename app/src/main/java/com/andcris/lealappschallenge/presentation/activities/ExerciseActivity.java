package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Bundle;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityExerciseBinding;
import com.andcris.lealappschallenge.presentation.adapters.ExerciseAdapter;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityExerciseBinding activityExerciseBinding = ActivityExerciseBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityExerciseBinding.getRoot());

        ExerciseAdapter ExerciseAdapter = new ExerciseAdapter();
        activityExerciseBinding.exerciseActivityRvExercise.setAdapter(ExerciseAdapter);
        activityExerciseBinding.exerciseActivityRvExercise.addItemDecoration(new DividerItemDecoration(
                activityExerciseBinding.exerciseActivityRvExercise.getContext(), DividerItemDecoration.VERTICAL));
    }
}