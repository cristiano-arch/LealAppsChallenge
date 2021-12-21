package com.andcris.lealappschallenge.presentation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddEditWorkoutActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "AddEditWorkoutActivity";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ActivityAddEditWorkoutBinding activityAddEditWorkoutBinding;
    private Boolean isEdit = false;
    private ProgressDialog progressDialog;
    private Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityAddEditWorkoutBinding = ActivityAddEditWorkoutBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityAddEditWorkoutBinding.getRoot());

        workout = getIntent().getParcelableExtra("workout");

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
                String name = activityAddEditWorkoutBinding.addEditWorkoutTilName.getEditText().getText().toString().trim();
                String description = activityAddEditWorkoutBinding.addEditWorkoutTilDescription.getEditText().getText().toString().trim();
                String date = activityAddEditWorkoutBinding.addEditWorkoutTilDate.getEditText().getText().toString().trim();
                if (validateName(name) & validateDate(date)) {
                    showProgressDialog();
                    if (!isEdit) {
                        insert(name, description, Util.getDateFromString(date.replace("/", "-")));
                    } else {
                        update(workout.getId(), name, description, Util.getDateFromString(date.replace("/", "-")));
                    }
                }
            }
        });
    }

    public void delete (String id) {
        db.collection("workouts")
                .document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(progressDialog.isShowing()) progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(AddEditWorkoutActivity.this, "Sucesso! Treino excluído!", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Log.w(TAG, "Error deleting documents.", task.getException());
                            Toast.makeText(AddEditWorkoutActivity.this, "Oops! Algo deu errado!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void update(String id, String name, String description, Date date) {
        Map<String, Object> workout = new HashMap<>();
        workout.put("name", name);
        workout.put("description", description);
        workout.put("date", date);

        db.collection("workouts")
                .document(id)
                .update(workout)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddEditWorkoutActivity.this, "Sucesso! Treino atualizado!", Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing()) progressDialog.dismiss();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        Toast.makeText(AddEditWorkoutActivity.this, "Oops! Algo deu errado!", Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing()) progressDialog.dismiss();
                    }
                });
    }

    public void insert(String name, String description, Date date) {
        Map<String, Object> workout = new HashMap<>();
        workout.put("name", name);
        workout.put("description", description);
        workout.put("date", date);

        db.collection("workouts")
                .add(workout)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddEditWorkoutActivity.this, "Sucesso! Treino criado!", Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing()) progressDialog.dismiss();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(AddEditWorkoutActivity.this, "Oops! Algo deu errado!", Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing()) progressDialog.dismiss();
                    }
                });
    }

    public boolean validateName(String name) {
        if (name.isEmpty()) {
            activityAddEditWorkoutBinding.addEditWorkoutTilName.setError("Nome não pode ser vazio");
            return false;
        } else {
            activityAddEditWorkoutBinding.addEditWorkoutTilName.setError(null);
            return true;
        }
    }

    public boolean validateDate(String date) {
        if (date.equalsIgnoreCase("Selecione")) {
            activityAddEditWorkoutBinding.addEditWorkoutTilDate.setError("Selecione uma data");
            return false;
        } else {
            activityAddEditWorkoutBinding.addEditWorkoutTilDate.setError(null);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_edit_workout, menu);
        return isEdit;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_edit_workout_mnDelete) {
            showProgressDialog();
            delete(workout.getId());
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

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(AddEditWorkoutActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Carregando...");
        progressDialog.show();
    }
}