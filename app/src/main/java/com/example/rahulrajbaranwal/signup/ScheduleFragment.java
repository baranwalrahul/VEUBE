package com.example.rahulrajbaranwal.signup;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ScheduleFragment extends Fragment {

  ImageView meetChat,settings;
  TextView date;
  ImageButton plussign;

    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




        meetChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),naviagation_menu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("flag",2);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),naviagation_menu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("flag",3);
                startActivity(intent);
            }
        });





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View v=inflater.from(getContext()).inflate(R.layout.fragment_schedule,container,false);
      meetChat=v.findViewById(R.id.meet);
      settings=v.findViewById(R.id.settings);
        //Date function
        date = v.findViewById(R.id.date);
        String date_n = new SimpleDateFormat("MMM d, EEE", Locale.getDefault()).format(new Date());
        date.setText(date_n);
        date.setTextColor(Color.WHITE);
        date.setTextSize(20);


        plussign = v.findViewById(R.id.plussign);

        plussign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plussign.setVisibility(view.GONE);
            }
        });





      return v;



    }
}
