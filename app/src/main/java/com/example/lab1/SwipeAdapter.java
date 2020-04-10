package com.example.lab1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    public SwipeAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new fragBlueprint();
        Bundle bundle = new Bundle();
        ArrayList<Candidate> candidates = new ArrayList<>();
        Candidate c1 = new Candidate("Narendra Modi 1","BA from mithibai collage 1","Chaiwala,Divorced 1","Gujrat Business Expo, Daarubandhi 1","80 LACS 1");
        Candidate c2 = new Candidate("Narendra Modi 2","BA from mithibai collage 2","Chaiwala,Divorced 2","Gujrat Business Expo, Daarubandhi 2","80 LACS 2");
        Candidate c3 = new Candidate("Narendra Modi 3","BA from mithibai collage 3","Chaiwala,Divorced 3","Gujrat Business Expo, Daarubandhi 3","80 LACS 3");
        Candidate c4 = new Candidate("Narendra Modi 4","BA from mithibai collage 4","Chaiwala,Divorced 4","Gujrat Business Expo, Daarubandhi 4","80 LACS 4");
        Candidate c5 = new Candidate("Narendra Modi 5","BA from mithibai collage 5","Chaiwala,Divorced 5","Gujrat Business Expo, Daarubandhi 5","80 LACS 5");
        candidates.add(c1);
        candidates.add(c2);
        candidates.add(c3);
        candidates.add(c4);
        candidates.add(c5);

        ArrayList<String> details = new ArrayList<>();
        details.add(candidates.get(position).getName());
        details.add(candidates.get(position).getBasicEdu());
        details.add(candidates.get(position).getNotableWorks());
        details.add(candidates.get(position).getHistory());
        details.add(candidates.get(position).getEarnings());

        bundle.putStringArrayList("1",details);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
