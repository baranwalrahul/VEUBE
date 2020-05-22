package com.example.rahulrajbaranwal.signup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.widget.Toolbar;

public class settings extends AppCompatActivity {
    ImageButton meet, schedule, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.settings_action);
        View customActionBarView = actionBar.getCustomView();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#ffffff"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);





        setContentView(R.layout.activity_settings);


        meet = findViewById(R.id.meet);
        schedule = findViewById(R.id.schedule);
        back = findViewById(R.id.back);
        meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), aftersignup.class);
                startActivity(i);
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(getApplicationContext(), naviagation_menu.class);
                startActivity(j);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(getApplicationContext(), aftersignup.class);
                startActivity(k);
            }
        });
    }
}
