package com.example.rahulrajbaranwal.signup;

/**
 * Created by Rahul Raj Baranwal on 04-05-2020.
 */

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public EditText InputEmail, InputPassword;
    public  TextView InputForgot, InputRegister;
    ImageButton back;
    String email=" ";

    private RequestQueue requestQueue;
    String KEY_USERNAME="username";
    String KEY_PASSWORD="password";

    public Button InputLogin;

    private String username;
    String password=" ";



    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        getSupportActionBar().setTitle("Sign In");


        InputEmail = findViewById(R.id.email1);

        InputPassword = findViewById(R.id.password1);
        InputLogin = findViewById(R.id.signin);
        InputForgot = findViewById(R.id.forgot);
        InputRegister= findViewById(R.id.register);
        back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), afterwelcome.class);
                startActivity(i);
            }
        });
        InputForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, forgotPassword.class);
                startActivity(i);
            }
        });
        InputRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, naviagation_menu.class);
                startActivity(i);
            }
        });



        InputLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                email = Objects.requireNonNull(InputEmail).getText().toString().trim();
                password = Objects.requireNonNull(InputPassword).getText().toString();


                if (!email.isEmpty() && !password.isEmpty()) {
                    if (email.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
                    } else {
                        if (email.toString().trim().matches(emailPattern)) {

                        }
                    }
                    String data = "{" +
                            "\"username\""+":" + "\"" + email + "\","+
                            "\"password\"" +":"+ "\""+ password+ "\""+

                            "}";
                    userLogin(data);


                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
                }
            }

        });

    }
    private void userLogin(String data){
        final String savedata = data;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", email);
        params.put("password", password);
        JSONObject jsonObject=new JSONObject(params);
        String Login_URL ="http://Ummodi-env.eba-tt5qhrb5.us-east-1.elasticbeanstalk.com:8080/authenticate";
        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Login_URL, jsonObject,




                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("token").trim().length()>0){
                                startActivity(new Intent(getApplicationContext(),naviagation_menu.class));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },




                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getClass().getSimpleName(), Toast.LENGTH_SHORT).show();

                    }});

// Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }


}



