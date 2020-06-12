package com.example.rahulrajbaranwal.signup;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManageProfile extends Fragment {
    View changePassword;
    ImageButton cross;
    Button save_change;
    TextView forgot_password;
    ConstraintLayout forgot;
    private ProgressDialog mProgress;
    EditText current_password,new_password,confirm_new_password;
    String current_password1,confirm_new_password1;
    SharedPreferences sharedPreferences;




    public ManageProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= LayoutInflater.from(getContext()).inflate(R.layout.fragment_blank, container, false);
        changePassword=v.findViewById(R.id.changePassword);


        //The layout of changing password
        forgot=v.findViewById(R.id.forgot);

        //For making layout invisible again
        cross = v.findViewById(R.id.cross);



        //Getting new password
        new_password= v.findViewById(R.id.new_password);
        confirm_new_password = v.findViewById(R.id.confirm_new_password);


        save_change = v.findViewById(R.id.save_change);
        current_password = v.findViewById(R.id.current_password);

        sharedPreferences=getContext().getSharedPreferences("Data",Context.MODE_PRIVATE);






        //Calling forgot page with this
        forgot_password = v.findViewById(R.id.forgot_password);





        return v;
    }





    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProgress = new ProgressDialog(getContext());
        mProgress.setTitle("Changing Password...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), forgotPassword.class));
            }
        });


        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgot.setVisibility(View.GONE);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                forgot.setVisibility(View.VISIBLE);
//                SharedPreferences sharedPreferences=getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
//                Toast.makeText(getContext(), sharedPreferences.getString("token","ok")+
//                        " "+sharedPreferences.getString("user","oka")+" "
//                        +sharedPreferences.getString("password","pass")
//                        , Toast.LENGTH_SHORT).show();
            }
        });

//


        save_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("values", "onClick: "+current_password.getText().toString()+" "+confirm_new_password.getText().toString());

//
                if(current_password.getText().toString().equals(sharedPreferences.getString("password","pass"))){


                    {
                        if(new_password.getText().toString().equals(confirm_new_password.getText().toString())) {
                            mProgress.show();
                            String data = "{" +
                                    "\"username\"" + ":" + "\"" + sharedPreferences.getString("user", "oka")+ "\"," +
                                    "\"password\"" + ":" + "\"" + confirm_new_password.getText().toString() + "\"" +

                                    "}";
                            Log.d("values",data);
                           userChangePassword(data);

                        }
                        else{
                            Toast.makeText(getContext(), "Password don't match, Re-enter", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else{
                    Toast.makeText(getContext(), "Wrong current password", Toast.LENGTH_LONG).show();
                }

            }
        });






    }
    private void userChangePassword(String data){
        final String savedata = data;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", sharedPreferences.getString("user","oka"));
        params.put("password", confirm_new_password.getText().toString());
        params.put("opassword", sharedPreferences.getString("password","pass"));
//        params.put("password", sharedPreferences.getString("password","null"));

        //SharedPreferences.Editor editor= sharedPreferences.edit();
        //editor.putString(username, username);
       // editor.putString(password, password);
       // editor.commit();




        JSONObject jsonObject=new JSONObject(params);
        String URL ="http://umbeolaunchloadbal-167166363.us-east-2.elb.amazonaws.com/changePassword";
        final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                URL, jsonObject,




                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        mProgress.dismiss();

                        try {
                            if(response.toString().equals("true")){

                                Toast.makeText(getContext(), "Password changed successfully", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            mProgress.dismiss();
                            e.printStackTrace();
                        }


                    }
                },





                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mProgress.dismiss();
                        if(!error.getClass().getSimpleName().equals("ParseError")){
                            Toast.makeText(getContext(), "Password Not Changed", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Password Changed successfully", Toast.LENGTH_SHORT).show();
                            forgot.setVisibility(View.GONE);

                        }

                    }




        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
            String bearer = "Bearer ".concat(sharedPreferences.getString("token","ok"));
            Map<String, String> headersSys = super.getHeaders();
            Map<String, String> headers = new HashMap<String, String>();
            headersSys.remove("Authorization");
            headers.put("Authorization", bearer);
            headers.putAll(headersSys);
            return headers;
        }
        };







// Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjReq);

    }


}
