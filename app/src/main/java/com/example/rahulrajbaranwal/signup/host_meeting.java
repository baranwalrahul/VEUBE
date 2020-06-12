package com.example.rahulrajbaranwal.signup;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class host_meeting extends AppCompatActivity {

    ImageButton CameraBtn, MikeBtn, ChatBtn, dotbtn;
    ListView listview;
    Button leave_btn;
    String[] nameArray = {"Participants","Share Screen","Transfer File","Raise Hand"};
    boolean isOpened = false;
    Integer[] imageArray = {R.drawable.participaints_icon,
            R.drawable.sharescreen_icon,
            R.drawable.transfer_icon,
            R.drawable.raisehand_icon,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_meeting);
        getSupportActionBar().hide();
        CameraBtn = (ImageButton)findViewById(R.id.camera_on);
        MikeBtn = (ImageButton)findViewById(R.id.mic_on);
        ChatBtn = (ImageButton)findViewById(R.id.chat_button);
        dotbtn = (ImageButton)findViewById(R.id.dot_buttonId);
        leave_btn =  findViewById(R.id.leave_btn);

        CustomListAdapter whatever = new CustomListAdapter(this, nameArray, imageArray);
        listview = (ListView) findViewById(R.id.list1);
        listview.setAdapter(whatever);
        leave_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), naviagation_menu.class);
                startActivity(intent);
            }
        });
        CameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.INTENT_ACTION_VIDEO_CAMERA);
                startActivity(intent);
            }
        });
        dotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOpened) {
                    isOpened = true;
                    listview.setVisibility(View.VISIBLE);
                }
                else{
                    isOpened = false;
                    listview.setVisibility(View.INVISIBLE);
                }

            }
        });
        MikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                int mode = audioManager.getMode();
                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
                boolean state = audioManager.isMicrophoneMute();
                audioManager.setMicrophoneMute(!state);
                audioManager.setMode(mode);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.participants_Id:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            case R.id.share_Id:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            case R.id.transfer_Id:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            case R.id.raise_Id:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
