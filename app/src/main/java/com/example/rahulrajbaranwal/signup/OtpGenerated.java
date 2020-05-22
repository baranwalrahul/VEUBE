package com.example.rahulrajbaranwal.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_generated);
        final EditText OTP;
        Button changePassword;

        getSupportActionBar().setTitle("Change Password");
        OTP = findViewById(R.id.OTP);
        changePassword = findViewById(R.id.changePassword);

        changePassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String otp = Objects.requireNonNull(OTP).getText().toString().trim();
                String name = getIntent().getStringExtra("name");
                String password = getIntent().getStringExtra("password");
                String username = getIntent().getStringExtra("username");


                if (!name.isEmpty() && !username.isEmpty() && !otp.isEmpty() && !password.isEmpty()) {
                    if (otp.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_SHORT).show();
                    } else {

                    }

                    {
                        String data = "{" +
                                "\"name\"" + ":" + "\"" + name + "\"," +
                                "\"otp\"" + ":" + "\"" + otp + "\"," +
                                "\"username\"" + ":" + "\"" + username + "\"," +
                                "\"password\"" + ":" + "\"" + password + "\"" +

                                "}";
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
        String URL = "http://Ummodi-env.eba-tt5qhrb5.us-east-1.elasticbeanstalk.com:8080/register";

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplication(), response, Toast.LENGTH_SHORT).show();
                if(response.trim().equals("true")){

                    Intent i = new Intent(OtpGenerated.this, LoginActivity.class);
                    startActivity(i);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
