package com.example.rahulrajbaranwal.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class afterwelcome extends AppCompatActivity {
    Button join, signin, signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterwelcome);
        join = findViewById(R.id.join);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(afterwelcome.this, MainActivity.class);
                startActivity(i);
            }
        });
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(afterwelcome.this, home.class);
                startActivity(i);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(afterwelcome.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
