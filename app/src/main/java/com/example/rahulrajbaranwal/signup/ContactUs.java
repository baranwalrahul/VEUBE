package com.example.rahulrajbaranwal.signup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUs extends Fragment {

TextView concern;
    public ContactUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= LayoutInflater.from(getContext()).inflate(R.layout.fragment_contact_us, container, false);

        concern = v.findViewById(R.id.concern);
        concern.setMovementMethod(LinkMovementMethod.getInstance());
     concern.setLinkTextColor(getResources().getColor(R.color.holo_dark_blue));
        concern.setText("For the further assistance you can either mail us your concern at info@umbeo.com or visit https://www.umbeo.com");
        Linkify.addLinks(concern, Linkify.WEB_URLS | Linkify.PHONE_NUMBERS);
        Linkify.addLinks(concern, Linkify.ALL );


        return v;


    }

}
