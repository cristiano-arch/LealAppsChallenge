package com.andcris.lealappschallenge.presentation.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andcris.lealappschallenge.databinding.WorkoutItemBinding;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {
    @NonNull
    @Override
    public WorkoutAdapter.WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkoutViewHolder(WorkoutItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.WorkoutViewHolder holder, int position) {
        holder.workoutItemBinding.rvWorkoutTvName.setText("Flexão");
        holder.workoutItemBinding.rvWorkoutTvDescription.setText("É um exercício físico realizado em posição de prancha");
        holder.workoutItemBinding.rvWorkoutTvDate.setText("10-04-19 12:00:17");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class WorkoutViewHolder extends RecyclerView.ViewHolder {

        WorkoutItemBinding workoutItemBinding;

        public WorkoutViewHolder(WorkoutItemBinding workoutItemBinding) {
            super(workoutItemBinding.getRoot());
            this.workoutItemBinding = workoutItemBinding;
        }
    }
}
