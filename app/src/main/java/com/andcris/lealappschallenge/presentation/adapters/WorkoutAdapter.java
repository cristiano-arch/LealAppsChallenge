package com.andcris.lealappschallenge.presentation.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andcris.lealappschallenge.R;
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

        holder.workoutItemBinding.rvWorkoutIvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.showWorkoutPopupMenu(view, position, context);
            }
        });
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

        public void showWorkoutPopupMenu(View v, int index, Context context) {
            PopupMenu popupMenu = new PopupMenu(v.getContext(), v, Gravity.END, R.attr.actionOverflowMenuStyle, 0);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu_workout, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.workout_popup_mnDelete) {
                        Toast.makeText(context, "Excluir " + index, Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            popupMenu.show();
        }
    }
}
