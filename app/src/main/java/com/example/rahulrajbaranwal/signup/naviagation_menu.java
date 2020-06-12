package com.example.rahulrajbaranwal.signup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class naviagation_menu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ConstraintLayout layout;
    ImageButton cross;
    ImageView title,userIMage,dot;
    TextView channgeTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naviagation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        layout=findViewById(R.id.fragmentContainer);
        title=findViewById(R.id.veube_icon);
        userIMage=findViewById(R.id.profile_picture);
        dot=findViewById(R.id.dot);
        channgeTitle=findViewById(R.id.titleChange);
        //        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.schedule_action);

        cross = findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), naviagation_menu.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Intent intent=getIntent();
        int a=intent.getIntExtra("flag",2);



        if(a==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new ScheduleFragment()).commit();
        }
        else if(a==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new MeetFragment()).commit();
        }
        else if(a==3){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new SettingFragment()).commit();
        }



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.naviagation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new ManageProfile()).commit();
        //   getSupportActionBar().setTitle("Manage Profile");
            userIMage.setVisibility(View.GONE);
            dot.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            channgeTitle.setVisibility(View.VISIBLE);
            channgeTitle.setText("Manage Profile");

        } else if (id == R.id.nav_gallery) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new ContactUs()).commit();
            userIMage.setVisibility(View.GONE);
            dot.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            channgeTitle.setVisibility(View.VISIBLE);
            channgeTitle.setText("Contact Us");
        } else if (id == R.id.nav_slideshow) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new Terms_Conditions()).commit();
            userIMage.setVisibility(View.GONE);
            dot.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            channgeTitle.setVisibility(View.VISIBLE);
            channgeTitle.setText("Terms & Conditions");
        } else if (id == R.id.nav_manage) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new About_Us()).commit();
            userIMage.setVisibility(View.GONE);
            dot.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            channgeTitle.setVisibility(View.VISIBLE);
            channgeTitle.setText("About Us");
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
