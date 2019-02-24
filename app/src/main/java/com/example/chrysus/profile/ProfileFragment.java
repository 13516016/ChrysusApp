package com.example.chrysus.profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chrysus.BaseController;
import com.example.chrysus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    BaseController controller;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        controller = new ProfileController(getContext(), view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ProfileController) controller).getUserData();
    }
}
