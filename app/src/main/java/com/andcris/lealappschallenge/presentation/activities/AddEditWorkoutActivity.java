package com.andcris.lealappschallenge.presentation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityAddEditWorkoutBinding;
import com.andcris.lealappschallenge.models.Workout;
import com.andcris.lealappschallenge.utils.DatePickerFragment;
import com.andcris.lealappschallenge.utils.Util;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;

public class AddEditWorkoutActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ActivityAddEditWorkoutBinding activityAddEditWorkoutBinding;
    public Boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityAddEditWorkoutBinding = ActivityAddEditWorkoutBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityAddEditWorkoutBinding.getRoot());

        Workout workout = getIntent().getParcelableExtra("workout");

        if (workout != null) {
            setTitle(R.string.add_edit_workout_tb_edit);
            activityAddEditWorkoutBinding.addEditWorkoutTilName.getEditText().setText(workout.getName());
            activityAddEditWorkoutBinding.addEditWorkoutTilDescription.getEditText().setText(workout.getDescription());
            activityAddEditWorkoutBinding.addEditWorkoutTilDate.getEditText().setText(Util.formatDate(workout.getDate()));
            activityAddEditWorkoutBinding.addEditWorkoutBtAdd.setText(R.string.add_edit_workout_tb_edit);
            isEdit = true;
        }

        activityAddEditWorkoutBinding.addEditWorkoutTilDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

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

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        activityAddEditWorkoutBinding.addEditWorkoutTilDate.getEditText().setText(Util.formatDate(calendar.getTime()));
    }
}