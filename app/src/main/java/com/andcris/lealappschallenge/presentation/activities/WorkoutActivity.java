package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Bundle;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityWorkoutBinding;
import com.andcris.lealappschallenge.presentation.adapters.WorkoutAdapter;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityWorkoutBinding activityWorkoutBinding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityWorkoutBinding.getRoot());

        WorkoutAdapter workoutAdapter = new WorkoutAdapter();
        activityWorkoutBinding.workoutActivityRvWorkout.setAdapter(workoutAdapter);
        activityWorkoutBinding.workoutActivityRvWorkout.addItemDecoration(new DividerItemDecoration(
                activityWorkoutBinding.workoutActivityRvWorkout.getContext(), DividerItemDecoration.VERTICAL));

    }
}