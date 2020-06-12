package com.example.rahulrajbaranwal.signup;

import android.*;
import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

public class MainActivity extends AppCompatActivity {
    public EditText InputEmail, InputName, InputPassword;
    ImageButton upload;
    private ProgressDialog mProgress;
    private RequestQueue requestQueue;
    public Button InputtSignup, InputLogin;
    private TextView mDisplaydate, displaylogin;
    ImageButton back;
    private static int RESULT_LOAD_IMAGE = 1;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String name = " ";
    String password= " ";
    String email=" ";
    String dob=" ";
    Bitmap lastBitmap= null ;

    String image=" ";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Signing Up...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);




        getSupportActionBar().setTitle("Sign Up");

        InputName = findViewById(R.id.name);
        InputEmail = findViewById(R.id.email);
        upload = findViewById(R.id.upload);

        mDisplaydate = (TextView) findViewById(R.id.dob);
        displaylogin = findViewById(R.id.login);
        InputPassword = findViewById(R.id.password);
        InputtSignup = findViewById(R.id.signup);
        back= findViewById(R.id.back);



        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED &&(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) ){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);

        }




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), afterwelcome.class);
                startActivity(i);
            }
        });



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });



        displaylogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });




        mDisplaydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get((Calendar.DAY_OF_MONTH));
                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                Calendar userAge = new GregorianCalendar(year, month, day);
                Calendar minAdultAge = new GregorianCalendar();
                minAdultAge.add(Calendar.YEAR, -18);
                if (minAdultAge.before(userAge)) {
                    Toast.makeText(getApplicationContext(), "You should be at least 18", Toast.LENGTH_SHORT).show();
                } else {
                    month = month + 1;
                    String date = day + "-" + month + "-" + year;
                    mDisplaydate.setText(date);
                }
            }
        };

        InputtSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                name = Objects.requireNonNull(InputName).getText().toString().trim();
                email = Objects.requireNonNull(InputEmail).getText().toString().trim();
                password = Objects.requireNonNull(InputPassword).getText().toString();
                dob = Objects.requireNonNull(mDisplaydate).getText().toString();
//                Random random = new Random();
//                int otp = random.nextInt(9999999-999999)+999999;
                if(lastBitmap==null) {
                image="image";

                }
                else {
                    image = getStringImage(lastBitmap);
                    Log.d("image", image);

                }

                if (!name.isEmpty() && !email.isEmpty() && !dob.isEmpty() && !password.isEmpty()) {
                    if (email.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if (email.toString().trim().matches(emailPattern)) {

                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                        }
                    }
                    //"\"profile\"" +":"+ "\""+ image+ "\""+
                    {
                        mProgress.show();
                        String data = "{" +
                                "\"name\""+":" + "\"" + name + "\","+
                                "\"username\""+":" + "\"" + email + "\","+
                                "\"password\"" +":"+ "\""+ password+ "\""+
                                "}";
                    // new pushAynce().execute(new UserData(email,name,password,dob,image));

                       Submit(data);



                    }



                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your details!", Toast.LENGTH_LONG).show();
                }

            }


        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }
            else{
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();



        CircleImageView imageView =  findViewById(R.id.imageView);


            //Converting uploaded image into circular shape

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
//            Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//
//            BitmapShader shader = new BitmapShader (bitmap,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//            Paint paint = new Paint();
//            paint.setShader(shader);
//            paint.setAntiAlias(true);
//            Canvas c = new Canvas(circleBitmap);
//            c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth()/2, paint);

            imageView.setImageBitmap(bitmap);

            //Converting image into string

            lastBitmap= bitmap;
           // String image = getStringImage(lastBitmap);
            //Log.d("image", image);



        }


    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,100, baos);
        byte[] imageBytes= baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private  void Submit (String data) {
        final String savedata = data;
        //String URL = "https://en2nr28lzoz5p.x.pipedream.net/otpgenerated";
        String URL = "http://umbeolaunchloadbal-167166363.us-east-2.elb.amazonaws.com/otpgenerated";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mProgress.dismiss();
                Toast.makeText(getApplication(), response, Toast.LENGTH_SHORT).show();
                if(response.trim().equals("true")){

                    Intent i = new Intent(MainActivity.this, OtpGenerated.class);
                    i.putExtra("name",name);
                    i.putExtra("password",password);
                    i.putExtra("username",email);
                    i.putExtra("profile",image);
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
        {
            int socketTimeout=30000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
    class pushAynce extends AsyncTask<UserData,Void,Void>{

        @Override
        protected Void doInBackground(UserData... userData) {
            UserRepo instance=UserRepo.getInstance(getApplicationContext());
            instance.getDao().insert(userData[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "Data Stored", Toast.LENGTH_SHORT).show();

        }
    }
}



