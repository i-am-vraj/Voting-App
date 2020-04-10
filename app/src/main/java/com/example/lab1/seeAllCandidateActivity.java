package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class seeAllCandidateActivity extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_candidate);
        lv = findViewById(R.id.seeallcan);

        ArrayList<String> list = new ArrayList<>();
        final Candidate c1 = new Candidate("Narendra Modi 1","BA from mithibai collage 1","Chaiwala,Divorced 1","Gujrat Business Expo, Daarubandhi 1","80 LACS 1");
        final Candidate c2 = new Candidate("Narendra Modi 2","BA from mithibai collage 2","Chaiwala,Divorced 2","Gujrat Business Expo, Daarubandhi 2","80 LACS 2");
        final Candidate c3 = new Candidate("Narendra Modi 3","BA from mithibai collage 3","Chaiwala,Divorced 3","Gujrat Business Expo, Daarubandhi 3","80 LACS 3");
        final Candidate c4 = new Candidate("Narendra Modi 4","BA from mithibai collage 4","Chaiwala,Divorced 4","Gujrat Business Expo, Daarubandhi 4","80 LACS 4");
        final Candidate c5 = new Candidate("Narendra Modi 5","BA from mithibai collage 5","Chaiwala,Divorced 5","Gujrat Business Expo, Daarubandhi 5","80 LACS 5");
        list.add(c1.getName());
        list.add(c2.getName());
        list.add(c3.getName());
        list.add(c4.getName());
        list.add(c5.getName());

        ArrayAdapter adapter = new ArrayAdapter(seeAllCandidateActivity.this,R.layout.listviewlayout,list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(seeAllCandidateActivity.this,CandidateActivity.class);

                switch(i)
                {
                    case 0:
                        intent.putExtra("name",c1.getName());
                        intent.putExtra("basicEdu",c1.getBasicEdu());
                        intent.putExtra("notableWorks",c1.getNotableWorks());
                        intent.putExtra("history",c1.getHistory());
                        intent.putExtra("earning",c1.getEarnings());
                        break;
                    case 1:
                        intent.putExtra("name",c2.getName());
                        intent.putExtra("basicEdu",c2.getBasicEdu());
                        intent.putExtra("notableWorks",c2.getNotableWorks());
                        intent.putExtra("history",c2.getHistory());
                        intent.putExtra("earning",c2.getEarnings());
                        break;
                    case 2:
                        intent.putExtra("name",c3.getName());
                        intent.putExtra("basicEdu",c3.getBasicEdu());
                        intent.putExtra("notableWorks",c3.getNotableWorks());
                        intent.putExtra("history",c3.getHistory());
                        intent.putExtra("earning",c3.getEarnings());
                        break;
                    case 3:
                        intent.putExtra("name",c4.getName());
                        intent.putExtra("basicEdu",c4.getBasicEdu());
                        intent.putExtra("notableWorks",c4.getNotableWorks());
                        intent.putExtra("history",c4.getHistory());
                        intent.putExtra("earning",c4.getEarnings());
                        break;
                    case 4:
                        intent.putExtra("name",c5.getName());
                        intent.putExtra("basicEdu",c5.getBasicEdu());
                        intent.putExtra("notableWorks",c5.getNotableWorks());
                        intent.putExtra("history",c5.getHistory());
                        intent.putExtra("earning",c5.getEarnings());
                        break;
                    default:
                        intent.putExtra("name","");
                        intent.putExtra("basicEdu","");
                        intent.putExtra("notableWorks","");
                        intent.putExtra("history","");
                        intent.putExtra("earning","");
                        break;
                }
                startActivity(intent);
            }
        });
    }

}
