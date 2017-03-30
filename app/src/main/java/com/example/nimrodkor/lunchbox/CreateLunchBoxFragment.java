package com.example.nimrodkor.lunchbox;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nimrodkor.lunchbox.Util.DatabaseHelper;
import com.example.nimrodkor.lunchbox.Util.Lunchbox;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateLunchBoxFragment extends Fragment {
    DatabaseHelper mDatabaseHelper;

    public CreateLunchBoxFragment() {
        mDatabaseHelper = new DatabaseHelper(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_create_lunch_box, container, false);
        String id = ((MainActivity) getActivity()).getProfile().toBlocking().first().getId();
        Lunchbox lunchbox = mDatabaseHelper.getCurrentUserLunchbox(id);
        return view;
    }

}
