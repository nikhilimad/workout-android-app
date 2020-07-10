package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gymapp.Adapter.RecyclerViewAdapter;
import com.example.gymapp.model.excercise;

import java.util.ArrayList;
import java.util.List;

public class ListExercises extends AppCompatActivity {

    List<excercise> excerciseList=new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);

        initData();

        recyclerView = (RecyclerView)findViewById(R.id.list_ex);
        adapter= new RecyclerViewAdapter(excerciseList,getBaseContext());
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void initData() {

        excerciseList.add(new excercise(R.drawable.easy_pose,"Easy Pose"));
        excerciseList.add(new excercise(R.drawable.cobra_pose,"Cobra Pose"));
        excerciseList.add(new excercise(R.drawable.downward_dog_pose,"Downward Facing Dog Pose"));
        excerciseList.add(new excercise(R.drawable.boat_pose,"Boat Pose"));
        excerciseList.add(new excercise(R.drawable.half_pose,"Half Pose"));
        excerciseList.add(new excercise(R.drawable.low_pose,"Low Pose"));
        excerciseList.add(new excercise(R.drawable.upward_pose,"Upward Pose"));
        excerciseList.add(new excercise(R.drawable.crescent_pose,"Crescent Pose"));
        excerciseList.add(new excercise(R.drawable.warrior_pose,"Warrior Pose"));
        excerciseList.add(new excercise(R.drawable.bow_pose,"Bow Pose"));

    }
}
