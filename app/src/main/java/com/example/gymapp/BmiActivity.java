 package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

 public class BmiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
    }

    public void onCalculate(View view) {

        EditText editTextHeight = (EditText) findViewById(R.id.etHeight);
        EditText editTextWeight = (EditText) findViewById(R.id.etWeight);
        TextView textViewResult = (TextView) findViewById(R.id.tvResult);
        TextView textViewResult2 = (TextView) findViewById(R.id.tvResult2);


        double height = Double.parseDouble(editTextHeight.getText().toString());
        double weight = Double.parseDouble(editTextWeight.getText().toString());
        double bmi = weight / (height * height);

        textViewResult.setText(Double.toString(bmi));

        if (bmi <= 18.5) {
            textViewResult2.setText("underWight");
        } else if (bmi > 18.5 && bmi < 25)
            textViewResult2.setText("Normal");

        else {
            textViewResult2.setText("OverWeight");
        }
    }
}
