package com.zavrsni.unizg.fer.triviamemoryboard.levels.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zavrsni.unizg.fer.triviamemoryboard.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastMovesFragment extends Fragment {


    public LastMovesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_moves, container, false);
    }

}
