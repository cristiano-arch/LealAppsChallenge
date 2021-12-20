package com.andcris.lealappschallenge.presentation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityAddEditWorkoutBinding;
import com.andcris.lealappschallenge.models.Workout;
import com.andcris.lealappschallenge.utils.Util;

import java.util.Objects;

public class AddEditWorkoutActivity extends AppCompatActivity {

    public Boolean isEdit = false;

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
            Objects.requireNonNull(activityAddEditWorkoutBinding.addEditWorkoutTilDate.getEditText()).setText(Util.formatDate(workout.getDate()));
            activityAddEditWorkoutBinding.addEditWorkoutBtAdd.setText(R.string.add_edit_workout_tb_edit);
            isEdit = true;
        }

        activityAddEditWorkoutBinding.addEditWorkoutBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddEditWorkoutActivity.this, isEdit ? "Editar " + Objects.requireNonNull(workout).getName() : "Adicionar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_edit_workout, menu);
        return isEdit;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_edit_workout_mnDelete) {
            Toast.makeText(this, "Excluir", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}