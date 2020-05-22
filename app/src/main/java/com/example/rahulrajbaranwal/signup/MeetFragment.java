package com.example.rahulrajbaranwal.signup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MeetFragment extends Fragment {
    ImageView imageView;
    ImageView settings;
    TextView date;
    ImageButton plussign;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= LayoutInflater.from(getContext()).inflate(R.layout.fragment_meet,container,false);
        imageView=v.findViewById(R.id.schedule);
        settings=v.findViewById(R.id.settings);
        plussign = v.findViewById(R.id.plussign);

        plussign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plussign.setVisibility(view.GONE);
            }
        });

        //Date function
        date = v.findViewById(R.id.date);
        String date_n = new SimpleDateFormat("MMM d, EEE", Locale.getDefault()).format(new Date());
        date.setText(date_n);
        date.setTextColor(Color.WHITE);
        date.setTextSize(20);
        return v;



    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj=new Intent(getContext(),naviagation_menu.class);
                obj.putExtra("flag",1);
               obj.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(obj);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),naviagation_menu.class);
                intent.putExtra("flag",3);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
