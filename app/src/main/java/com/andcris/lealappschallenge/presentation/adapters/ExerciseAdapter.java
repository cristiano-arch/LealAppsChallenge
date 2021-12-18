package com.andcris.lealappschallenge.presentation.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ExerciseItemBinding;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    @NonNull
    @Override
    public ExerciseAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseViewHolder(ExerciseItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ExerciseViewHolder holder, int position) {
        holder.exerciseItemBinding.rvExerciseTvName.setText("Flexão");
        holder.exerciseItemBinding.rvExerciseIvImage.setImageResource(R.drawable.blank);
        holder.exerciseItemBinding.rvExerciseTvDescription.setText("É um exercício físico realizado em posição de prancha");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        ExerciseItemBinding exerciseItemBinding;

        public ExerciseViewHolder(ExerciseItemBinding exerciseItemBinding) {
            super(exerciseItemBinding.getRoot());
            this.exerciseItemBinding = exerciseItemBinding;
        }
    }
}
