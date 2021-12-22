package com.andcris.lealappschallenge.presentation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityExerciseBinding;
import com.andcris.lealappschallenge.databinding.ActivityWorkoutBinding;
import com.andcris.lealappschallenge.models.Exercise;
import com.andcris.lealappschallenge.models.Workout;
import com.andcris.lealappschallenge.presentation.adapters.ExerciseAdapter;
import com.andcris.lealappschallenge.presentation.adapters.WorkoutAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    private static final String TAG = "ExerciseActivity";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Exercise> exerciseList;
    private ProgressDialog progressDialog;
    private ExerciseAdapter exerciseAdapter;
    private ActivityExerciseBinding activityExerciseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityExerciseBinding = ActivityExerciseBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityExerciseBinding.getRoot());
    }

    @Override
    protected void onResume() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Carregando...");
        progressDialog.show();

        exerciseList = new ArrayList<>();
        findAllExercises();

        exerciseAdapter = new ExerciseAdapter(exerciseList);
        activityExerciseBinding.exerciseActivityRvExercise.setAdapter(exerciseAdapter);
        activityExerciseBinding.exerciseActivityRvExercise.addItemDecoration(new DividerItemDecoration(
                activityExerciseBinding.exerciseActivityRvExercise.getContext(), DividerItemDecoration.VERTICAL));

        super.onResume();
    }

    private void findAllExercises() {
        db.collection("exercises")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(progressDialog.isShowing()) progressDialog.dismiss();

                        if (!task.getResult().isEmpty()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                exerciseList.add(document.toObject(Exercise.class));
                            }
                            exerciseAdapter.notifyDataSetChanged();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}