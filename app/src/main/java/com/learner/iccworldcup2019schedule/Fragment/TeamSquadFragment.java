package com.learner.iccworldcup2019schedule.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learner.iccworldcup2019schedule.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamSquadFragment extends Fragment {


    public TeamSquadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_squad, container, false);
    }

}
