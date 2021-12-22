package com.andcris.lealappschallenge.presentation.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andcris.lealappschallenge.R;
import com.andcris.lealappschallenge.databinding.ExerciseItemBinding;
import com.andcris.lealappschallenge.models.Exercise;
import com.bumptech.glide.Glide;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    List<Exercise> exerciseList;

    public ExerciseAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseViewHolder(ExerciseItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);

        holder.exerciseItemBinding.rvExerciseTvName.setText(exercise.getName());
        holder.exerciseItemBinding.rvExerciseTvDescription.setText(exercise.getDescription());

        Glide.with(holder.exerciseItemBinding.rvExerciseIvImage.getContext())
                .load(exercise.getImageUrl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.blank)
                .into(holder.exerciseItemBinding.rvExerciseIvImage);
    }

    @Override
    public int getItemCount() {
        return exerciseList != null ? exerciseList.size() : 0;
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        ExerciseItemBinding exerciseItemBinding;

        public ExerciseViewHolder(ExerciseItemBinding exerciseItemBinding) {
            super(exerciseItemBinding.getRoot());
            this.exerciseItemBinding = exerciseItemBinding;
        }
    }
}
