package com.example.rahulrajbaranwal.signup;

/**
 * Created by Rahul Raj Baranwal on 05-05-2020.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class  aftersignup extends AppCompatActivity{
    ImageButton schedule, settings, plussign, threelines;
TextView date;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.aftersignup);
        date = findViewById(R.id.date);
        String date_n = new SimpleDateFormat("MMM d, EEE", Locale.getDefault()).format(new Date());
        date.setText(date_n);
        date.setTextColor(Color.WHITE);
        date.setTextSize(20);
        schedule = findViewById(R.id.schedule);
        settings = findViewById(R.id.settings);
        plussign = findViewById(R.id.plussign);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.schedule_action);
        View customActionBarView = actionBar.getCustomView();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#ffffff"));
        actionBar.setBackgroundDrawable(colorDrawable);



        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), naviagation_menu.class);
                startActivity(i);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), settings.class);
                startActivity(i);
            }
        });
    plussign.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            plussign.setVisibility(view.GONE);
        }
    });
    }
}