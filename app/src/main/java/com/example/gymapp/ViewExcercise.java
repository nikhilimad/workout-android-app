package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewExcercise extends AppCompatActivity {

    int image_id;
    String name;
    TextView timer,title;
    ImageView detail_image;
    Button btnStrt;
    boolean isRunning= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_excercise);

        timer=(TextView)findViewById(R.id.timer);
        title=(TextView)findViewById(R.id.title);
        detail_image=(ImageView)findViewById(R.id.detail_image);

        btnStrt=(Button)findViewById(R.id.btnstrt);
        btnStrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(!isRunning){
                  btnStrt.setText("DONE");
                  new CountDownTimer(20000,1000){

                      @Override
                      public void onTick(long l) {
                          timer.setText(""+l/1000);

                      }

                      @Override
                      public void onFinish() {
                          Toast.makeText(ViewExcercise.this,"Finish!!",Toast.LENGTH_SHORT).show();
                          finish();

                      }
                  }.start();
              }
              else{
                  Toast.makeText(ViewExcercise.this,"Finish!!",Toast.LENGTH_SHORT).show();
                  finish();

              }
              isRunning=!isRunning;
            }
        });

        timer.setText("");

        if(getIntent()!=null){
            image_id=getIntent().getIntExtra("image_id",-1);
            name=getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);
        }
    }
}
