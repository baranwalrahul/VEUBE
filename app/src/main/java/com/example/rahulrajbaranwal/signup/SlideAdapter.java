package com.example.rahulrajbaranwal.signup;

/**
 * Created by Rahul Raj Baranwal on 07-05-2020.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    private Object RelativeLayout;

    int currentPage=0;



    public SlideAdapter(Context context){
        this.context = context;
    }
    //Array

    public int[] slide_images = {
            R.drawable.welcome1,
            R.drawable.welcome2,
            R.drawable.welcome3,
    };

    public String[] slide_headings ={
            "Chats over text,audio or video,share your screen and even share files.",
            "Accessible across any platform running on any device",
            "Your security is our priority.Your data will always be safe and secure",


    };




    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view,  Object o) {
        return view == (RelativeLayout) o;


    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);


        container.addView(view);


        return view;
    }
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);
    }

}