package com.andcris.lealappschallenge.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Bundle;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ActivityExerciseBinding;
import com.andcris.lealappschallenge.models.Exercise;
import com.andcris.lealappschallenge.models.Workout;
import com.andcris.lealappschallenge.presentation.adapters.ExerciseAdapter;

import java.util.Arrays;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityExerciseBinding activityExerciseBinding = ActivityExerciseBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityExerciseBinding.getRoot());

        ExerciseAdapter ExerciseAdapter = new ExerciseAdapter(exercisesBuilder());
        activityExerciseBinding.exerciseActivityRvExercise.setAdapter(ExerciseAdapter);
        activityExerciseBinding.exerciseActivityRvExercise.addItemDecoration(new DividerItemDecoration(
                activityExerciseBinding.exerciseActivityRvExercise.getContext(), DividerItemDecoration.VERTICAL));
    }
    
    public List<Exercise> exercisesBuilder() {
        return Arrays.asList(new Exercise("Flexão", R.drawable.blank, "É um exercício físico realizado em posição de prancha."),
                new Exercise("Ponte", R.drawable.blank, "Deite-se de bruços no chão, numa superfície plana."),
                new Exercise("Agachamento na cadeira", R.drawable.blank, "Com uma cadeira, faça movimentos de sentar e levantar."),
                new Exercise("Agachamento na parede", R.drawable.blank, "Apoie as costas na parede e busque manter os joelhos flexionados."),
                new Exercise("Aviãozinho", R.drawable.blank, "De pé, recline o tronco para frente enquanto levata uma das pernas para trás."),
                new Exercise("Abdominal", R.drawable.blank, "Deite-se de barriga para cima, faça movimentos de elevação do tronco em direção dos joelhos."),
                new Exercise("Remada", R.drawable.blank, "Apoie-se com uma das mãos na altura do peitoral e realize movimentos de ida e vinda."),
                new Exercise("Extensão dos pés", R.drawable.blank, "De pé, com o corpo ereto, erga-se na ponta dos pés, subindo e descendo."),
                new Exercise("Pular corda", R.drawable.blank, "Pule a corda!"),
                new Exercise("Corrida", R.drawable.blank, "Corra!"));
    }
}