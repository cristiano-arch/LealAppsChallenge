package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityAddEditWorkoutBinding;
import com.andcris.lealappschallenge.models.Workout;

import java.util.Objects;

public class AddEditWorkoutActivity extends AppCompatActivity {

    private String toastText = "Adicionar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityAddEditWorkoutBinding activityAddEditWorkoutBinding = ActivityAddEditWorkoutBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityAddEditWorkoutBinding.getRoot());

        Workout workout = getIntent().getParcelableExtra("workout");

        if (workout != null) {
            setTitle(R.string.add_edit_workout_tb_edit);
            Objects.requireNonNull(activityAddEditWorkoutBinding.addEditWorkoutTilName.getEditText()).setText(workout.getName());
            Objects.requireNonNull(activityAddEditWorkoutBinding.addEditWorkoutTilDescription.getEditText()).setText(workout.getDescription());
            Objects.requireNonNull(activityAddEditWorkoutBinding.addEditWorkoutTilDate.getEditText()).setText(workout.getDate());
            activityAddEditWorkoutBinding.addEditWorkoutBtAdd.setText(R.string.add_edit_workout_tb_edit);
            toastText = "Editar";
        }

        activityAddEditWorkoutBinding.addEditWorkoutBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddEditWorkoutActivity.this, toastText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}