package com.example.lab1;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragBlueprint extends Fragment {

    TextView name;
    TextView basicEdu;
    TextView notableWorks;
    TextView history;
    TextView earnings;

    public fragBlueprint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_blueprint, container, false);
        name = view.findViewById(R.id.cname);
        basicEdu = view.findViewById(R.id.pqual);
        notableWorks = view.findViewById(R.id.cwork);
        history = view.findViewById(R.id.cpast);
        earnings = view.findViewById(R.id.cproperty);



        Bundle bundle = getArguments();
        name.setText(bundle.getStringArrayList("1").get(0));
        basicEdu.setText(bundle.getStringArrayList("1").get(1));
        notableWorks.setText(bundle.getStringArrayList("1").get(2));
        history.setText(bundle.getStringArrayList("1").get(3));
        earnings.setText(bundle.getStringArrayList("1").get(4));

        return view;
    }

}
