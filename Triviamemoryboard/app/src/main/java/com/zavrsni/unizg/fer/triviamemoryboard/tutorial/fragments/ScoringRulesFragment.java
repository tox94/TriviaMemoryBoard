package com.zavrsni.unizg.fer.triviamemoryboard.tutorial.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zavrsni.unizg.fer.triviamemoryboard.R;


public class ScoringRulesFragment extends Fragment {

    public ScoringRulesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scoring_rules, container, false);
    }
}
