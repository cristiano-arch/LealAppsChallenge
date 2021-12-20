package com.andcris.lealappschallenge.presentation.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.andcris.lealappschallenge.databinding.ActivityWorkoutBinding;
import com.andcris.lealappschallenge.models.Workout;
import com.andcris.lealappschallenge.presentation.adapters.WorkoutAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {

    private static final String TAG = "WorkoutActivity";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Workout> workoutList;
    private WorkoutAdapter workoutAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityWorkoutBinding activityWorkoutBinding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityWorkoutBinding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Carregando...");
        progressDialog.show();

        workoutList = new ArrayList<>();
        findAllWorkouts();

        workoutAdapter = new WorkoutAdapter(getApplicationContext(), workoutList);
        activityWorkoutBinding.workoutActivityRvWorkout.setAdapter(workoutAdapter);
        activityWorkoutBinding.workoutActivityRvWorkout.addItemDecoration(new DividerItemDecoration(
                activityWorkoutBinding.workoutActivityRvWorkout.getContext(), DividerItemDecoration.VERTICAL));

        activityWorkoutBinding.workoutActivityRvWorkout.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), activityWorkoutBinding.workoutActivityRvWorkout, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Workout workout = workoutList.get(position);
                startActivity(new Intent(WorkoutActivity.this, AddEditWorkoutActivity.class)
                        .putExtra("workout", workout));
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        }));

        activityWorkoutBinding.workoutActivityFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WorkoutActivity.this, AddEditWorkoutActivity.class));
            }
        });

        activityWorkoutBinding.workoutActivityRvWorkout.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    activityWorkoutBinding.workoutActivityFabAdd.hide();
                } else {
                    activityWorkoutBinding.workoutActivityFabAdd.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void findAllWorkouts() {
        db.collection("workouts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(progressDialog.isShowing()) progressDialog.dismiss();

                        if (!task.getResult().isEmpty()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                workoutList.add(document.toObject(Workout.class));
                                workoutAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}