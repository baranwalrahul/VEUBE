package com.example.rahulrajbaranwal.signup;

/**
 * Created by Rahul Raj Baranwal on 07-05-2020.
 */


import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends AppCompatActivity {

    private AutoScrollViewPager nSlideViewPager;
    private LinearLayout nDotLayout;
    private Button button;

    private TextView[] nDots;
    int currentPage=0;
    int NUM_PAGES=3;
    Timer timer;
    final long DELAY_MS =500;
    final long PERIOD_MS=1500;

    private SlideAdapter slideAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().setTitle("Welcome");
        button = findViewById(R.id.started);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Welcome.this, afterwelcome.class);
                startActivity(i);
            }
        });

        nSlideViewPager =(AutoScrollViewPager)findViewById(R.id.slidevierpager);

        nDotLayout =(LinearLayout)findViewById(R.id.dotsLayout);

        slideAdapter = new SlideAdapter(this);
        nSlideViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);
        nSlideViewPager.addOnPageChangeListener(viewListner);
        nSlideViewPager.startAutoScroll();
        nSlideViewPager.setInterval(3000);


        nSlideViewPager.addOnPageChangeListener(viewListner);
    }

    public void addDotsIndicator(int position) {
        nDots = new TextView[3];
        nDotLayout.removeAllViews();

        for (int i = 0; i < nDots.length; i++) {
            nDots[i] = new TextView(this);
            nDots[i].setText(Html.fromHtml("&#8226"));
            nDots[i].setTextSize(48);
            nDots[i].setTextColor(getResources().getColor(R.color.holo_purple));

            nDotLayout.addView(nDots[i]);
        }
        if (nDots.length > 0) {
            nDots[position].setTextColor(getResources().getColor(R.color.holo_new_gray));




        }
    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}


