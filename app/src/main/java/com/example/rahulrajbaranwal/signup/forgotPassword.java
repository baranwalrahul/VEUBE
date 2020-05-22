package com.example.rahulrajbaranwal.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class forgotPassword extends AppCompatActivity {
    private RequestQueue requestQueue;
    EditText emailForgot;
    TextView loginforgot;
    Button resetOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailForgot = findViewById(R.id.emailforgot);
        resetOTP = findViewById(R.id.resetOTP);

        getSupportActionBar().setTitle("Forgot Password");

        resetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random();
                int password = random.nextInt(999999999-999999)+999999;


                final  String username = emailForgot.getText().toString().trim();
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(forgotPassword.this, "Please Enter Your Registered Email", Toast.LENGTH_LONG).show();
                }
                {
                    String data = "{" +
                            "\"username\"" + ":" + "\"" + username + "\"," +
                            "\"password\"" + ":" + "\"" + password + "\"" +

                            "}";
                    Submit(data);


                }



            }
        });

    }

    private  void Submit (String data) {
        final String savedata = data;
        String URL = "http://Ummodi-env.eba-tt5qhrb5.us-east-1.elasticbeanstalk.com:8080/forgotPassword";

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(getApplication(), response, Toast.LENGTH_SHORT).show();
                if(response.equals("true")){
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError
            {
                try
                {
                    return savedata == null ? null : savedata.getBytes("utf-8");
                }
                catch (UnsupportedEncodingException uee)
                {
                    return null;
                }
            }
        };
        requestQueue.add(stringRequest);
    }
}
