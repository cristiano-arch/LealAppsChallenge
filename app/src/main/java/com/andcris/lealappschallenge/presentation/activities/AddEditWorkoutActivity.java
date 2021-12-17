package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityAddEditWorkoutBinding;

public class AddEditWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityAddEditWorkoutBinding activityAddEditWorkoutBinding = ActivityAddEditWorkoutBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityAddEditWorkoutBinding.getRoot());

        activityAddEditWorkoutBinding.addEditWorkoutBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddEditWorkoutActivity.this, "Adicionar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}