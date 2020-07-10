package com.example.gymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    private EditText UserName, UserPassword, UserEmail;
    private Button regButton;
    private TextView UserLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupViews();
        firebaseAuth=FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(validate()){
                   String user_email=UserEmail.getText().toString().trim();
                   String user_password= UserPassword.getText().toString().trim();

                   firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {

                           if(task.isSuccessful()){
                             sendEmailVerification();
                           }
                           else{
                               Toast.makeText(RegistrationActivity.this,"registration unsuccessful",Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
            }
        });

        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });
    }

    private void setupViews(){
        UserName = (EditText) findViewById(R.id.etUsername);
        UserPassword = (EditText) findViewById(R.id.etPassword);
        UserEmail = (EditText) findViewById(R.id.etEmail);
        regButton = (Button) findViewById(R.id.btnSignup);
        UserLogin = (TextView) findViewById(R.id.tvBack);
    }

    private Boolean validate(){
        Boolean result= false;
        String name= UserName.getText().toString();
        String password= UserPassword.getText().toString();
        String email= UserEmail.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else{
            result=true;
        }
        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegistrationActivity.this,"Successfully Registered, Verification mail sent!",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(RegistrationActivity.this,"Registration failed, Verification mail not sent!",Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}
