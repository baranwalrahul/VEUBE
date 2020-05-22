package com.example.rahulrajbaranwal.signup;

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

public class schedule extends AppCompatActivity {
    ImageButton meet, settings, plussign, threelines;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedule);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions( ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.schedule_action);






        View customActionBarView = actionBar.getCustomView();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#ffffff"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        meet = findViewById(R.id.meet);
        settings = findViewById(R.id.settings);
        plussign = findViewById(R.id.plussign);

        //Date function
        date = findViewById(R.id.date);
        String date_n = new SimpleDateFormat("MMM d, EEE", Locale.getDefault()).format(new Date());
        date.setText(date_n);
        date.setTextColor(Color.WHITE);
        date.setTextSize(20);





        meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), aftersignup.class);
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
