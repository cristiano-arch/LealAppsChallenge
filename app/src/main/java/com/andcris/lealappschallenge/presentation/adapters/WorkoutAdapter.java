package com.andcris.lealappschallenge.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andcris.lealappschallenge.databinding.WorkoutItemBinding;
import com.andcris.lealappschallenge.models.Workout;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder> {

    private Context context;
    private List<Workout> workoutList;

    public WorkoutAdapter(Context context, List<Workout> workoutList) {
        this.context = context;
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public WorkoutAdapter.WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkoutViewHolder(WorkoutItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutAdapter.WorkoutViewHolder holder, int position) {
        Workout workout = workoutList.get(position);

        holder.workoutItemBinding.rvWorkoutTvName.setText(workout.getName());
        holder.workoutItemBinding.rvWorkoutTvDescription.setText(workout.getDescription());
        holder.workoutItemBinding.rvWorkoutTvDate.setText(workout.getDate());
    }

    @Override
    public int getItemCount() {
        return workoutList != null ? workoutList.size() : 0;
    }

    public static class WorkoutViewHolder extends RecyclerView.ViewHolder {

        WorkoutItemBinding workoutItemBinding;

        public WorkoutViewHolder(WorkoutItemBinding workoutItemBinding) {
            super(workoutItemBinding.getRoot());
            this.workoutItemBinding = workoutItemBinding;
        }
    }
}
