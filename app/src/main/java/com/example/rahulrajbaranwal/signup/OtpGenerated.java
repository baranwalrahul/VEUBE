package com.example.rahulrajbaranwal.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Random;

public class OtpGenerated extends AppCompatActivity {
    private RequestQueue requestQueue;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_generated);
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Validating OTP...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
        final EditText OTP;
        Button changePassword;

        getSupportActionBar().setTitle("Change Password");
        OTP = findViewById(R.id.OTP);
        changePassword = findViewById(R.id.changePassword);

        changePassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mProgress.show();

                String otp = Objects.requireNonNull(OTP).getText().toString().trim();
                String name = getIntent().getStringExtra("name");
                String password = getIntent().getStringExtra("password");
                String username = getIntent().getStringExtra("username");
                String profile = getIntent().getStringExtra("profile");



                if (!name.isEmpty() && !username.isEmpty() && !otp.isEmpty() && !password.isEmpty() ) {
                    if (otp.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();
                    } else {

                    }

                    Log.e("OtpGeneretaed", "onClick: "+username+" "+password+" "+name+" "+otp+" "+profile);
                    {
                        String data = "{" +
                                "\"username\"" + ":" + "\"" + username + "\"," +
                                "\"password\"" + ":" + "\"" + password + "\"," +
                                "\"name\"" + ":" + "\"" + name + "\"," +
                                "\"otp\"" + ":" + "\"" + otp + "\"," +
                                "\"profile\"" + ":" + "\"" + profile.replaceAll("\\s+","" )+ "\"" +


                                "}";
                        Log.d("Profile", data);

                        Submit(data);


                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
                }
            }



        });


    }
    private  void Submit (String data) {
        final String savedata = data;
        String URL = "http://umbeolaunchloadbal-167166363.us-east-2.elb.amazonaws.com/register";

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mProgress.dismiss();
                Toast.makeText(getApplication(), response, Toast.LENGTH_SHORT).show();
                if(response.trim().equals("true")){

                    Intent i = new Intent(OtpGenerated.this, LoginActivity.class);
                    startActivity(i);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgress.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return savedata == null ? null : savedata.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }
        };
        requestQueue.add(stringRequest);
    }
}
