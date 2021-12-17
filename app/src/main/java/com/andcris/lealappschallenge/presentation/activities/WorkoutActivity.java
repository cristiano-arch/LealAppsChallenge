package com.andcris.lealappschallenge.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.andcris.lealappschallenge.databinding.ActivityWorkoutBinding;
import com.andcris.lealappschallenge.models.Workout;
import com.andcris.lealappschallenge.presentation.adapters.WorkoutAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkoutActivity extends AppCompatActivity {

    private List<Workout> workoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityWorkoutBinding activityWorkoutBinding = ActivityWorkoutBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityWorkoutBinding.getRoot());

        workoutList = new ArrayList<>(workoutsBuilder());
        WorkoutAdapter workoutAdapter = new WorkoutAdapter(getApplicationContext(), workoutList);
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

    public List<Workout> workoutsBuilder() {
        return Arrays.asList(new Workout("Flexão", "É um exercício físico realizado em posição de prancha.", "10-04-19 12:00:17"),
                new Workout("Ponte", "Deite-se de bruços no chão, numa superfície plana.", "10-04-19 12:00:17"),
                new Workout("Agachamento na cadeira", "Com uma cadeira, faça movimentos de sentar e levantar.", "10-04-19 12:00:17"),
                new Workout("Agachamento na parede", "Apoie as costas na parede e busque manter os joelhos flexionados.", "10-04-19 12:00:17"),
                new Workout("Aviãozinho", "De pé, recline o tronco para frente enquanto levata uma das pernas para trás.", "10-04-19 12:00:17"),
                new Workout("Abdominal", "Deite-se de barriga para cima, faça movimentos de elevação do tronco em direção dos joelhos.", "10-04-19 12:00:17"),
                new Workout("Remada", "Apoie-se com uma das mãos na altura do peitoral e realize movimentos de ida e vinda.", "10-04-19 12:00:17"),
                new Workout("Extensão dos pés", "De pé, com o corpo ereto, erga-se na ponta dos pés, subindo e descendo.", "10-04-19 12:00:17"),
                new Workout("Pular corda", "Pule a corda!", "10-04-19 12:00:17"),
                new Workout("Corrida", "Corra!", "10-04-19 12:00:17"));
    }
}