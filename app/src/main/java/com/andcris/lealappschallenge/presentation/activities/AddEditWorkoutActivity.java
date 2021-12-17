package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityAddEditWorkoutBinding;
import com.andcris.lealappschallenge.models.Workout;

public class AddEditWorkoutActivity extends AppCompatActivity {

    private Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityAddEditWorkoutBinding activityAddEditWorkoutBinding = ActivityAddEditWorkoutBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityAddEditWorkoutBinding.getRoot());

        workout = getIntent().getParcelableExtra("workout");

        Toast.makeText(this, workout != null ? "Editar " + workout.getName() : "Cadastar", Toast.LENGTH_SHORT).show();

        activityAddEditWorkoutBinding.addEditWorkoutBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddEditWorkoutActivity.this, "Adicionar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}