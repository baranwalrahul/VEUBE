package com.example.rahulrajbaranwal.signup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Rahul Raj Baranwal on 04-06-2020.
 */

public class CustomListAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    //to store the animal images
    private final Integer[] imageIDarray;

    //to store the list of countries
    private final String[] nameArray;

    public CustomListAdapter(Activity context, String[] nameArrayParam, Integer[] imageIDArrayParam){

        super(context,R.layout.list_view, nameArrayParam);
        this.context=context;
        this.imageIDarray = imageIDArrayParam;
        this.nameArray = nameArrayParam;
    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_view, null,true);

        TextView nameTextField = (TextView) rowView.findViewById(R.id.txt_Id1);
        ImageButton imageView = (ImageButton) rowView.findViewById(R.id.participants_Id);

        //this code sets the values of the objects to values from the arrays
        nameTextField.setText(nameArray[position]);
        imageView.setImageResource(imageIDarray[position]);
        return rowView;

    };
}

