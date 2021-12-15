package com.andcris.lealappschallenge;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.andcris.lealappschallenge.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.mainActivityTvBindingTest.setText("Binding works!");
    }
}