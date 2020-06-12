package com.example.rahulrajbaranwal.signup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.example.rahulrajbaranwal.signup.LoginActivity.MyPREFERENCES;


public class MeetFragment extends Fragment {
    ImageView imageView;
    ImageView settings;
    TextView date;
    ImageButton plussign, join, host;
    EditText editText3;

    SharedPreferences sharedPreferences;




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
        join = v.findViewById(R.id.join);
        host = v.findViewById(R.id.host);

        editText3 =v.findViewById(R.id.editText3);
        sharedPreferences=getContext().getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);
        editText3.setText(sharedPreferences.getString("note",""));




      if(editText3.getText().toString().length()>0){
          plussign.setVisibility(View.GONE);
      }

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
                savedSharedPreferences(editText3.getText().toString());

                Intent obj=new Intent(getContext(),naviagation_menu.class);
                obj.putExtra("flag",1);
               obj.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(obj);
            }
        });
        host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), host_meeting.class);
                startActivity(intent);
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), home.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedSharedPreferences(editText3.getText().toString());
                Intent intent=new Intent(getContext(),naviagation_menu.class);
                intent.putExtra("flag",3);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        savedSharedPreferences(editText3.getText().toString());
    }

    //BoilerPlate code removal
    private void savedSharedPreferences(String s){
        sharedPreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //  String Note = sharedPreferences.getString(note, "");
        //  editText3.setText(Note);

        editor.putString("note", s);
        editor.commit();
    }
}
