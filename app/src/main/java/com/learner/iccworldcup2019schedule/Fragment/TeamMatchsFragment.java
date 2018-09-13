package com.learner.iccworldcup2019schedule.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.learner.iccworldcup2019schedule.Adapter.ScheduleAdapter;
import com.learner.iccworldcup2019schedule.Common.Common;
import com.learner.iccworldcup2019schedule.Model.MatchSchedule;
import com.learner.iccworldcup2019schedule.Model.Team;
import com.learner.iccworldcup2019schedule.R;
import com.learner.iccworldcup2019schedule.ScheduleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamMatchsFragment extends Fragment {

    private RecyclerView mTeamMatchesScheduleRV;
    private DatabaseReference mRootRef;
    private DatabaseReference mScheduleRef;
    private List<MatchSchedule> mTeamMatchScheduleList = new ArrayList<>();
    private List<Integer> mTeamMatchNo = new ArrayList<>();
    private ScheduleAdapter mAdapter;
    private View mView;


    public TeamMatchsFragment() {
        // Required empty public constructor
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mScheduleRef = mRootRef.child(Common.SCHEDULE_REF);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_team_matchs, container, false);

        mTeamMatchesScheduleRV = mView.findViewById(R.id.team_match_list_recylcerview);
        mTeamMatchesScheduleRV.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mTeamMatchesScheduleRV.setLayoutManager(layoutManager);

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle!=null){
            final Team team = (Team) bundle.getSerializable(Common.TEAM_KEY);
            mScheduleRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mTeamMatchNo.clear();
                    mTeamMatchScheduleList.clear();
                    for (DataSnapshot postData : dataSnapshot.getChildren()){
                        MatchSchedule matchSchedule = postData.getValue(MatchSchedule.class);
                        if (matchSchedule.getTeam1().equals(team.getName())||matchSchedule.getTeam2().equals(team.getName())){
                            mTeamMatchScheduleList.add(matchSchedule);
                            mTeamMatchNo.add(Integer.valueOf(postData.getKey()));
                        }
                    }

                    if (mTeamMatchScheduleList.size()>0){
                        mAdapter = new ScheduleAdapter(getContext(),mTeamMatchScheduleList,mTeamMatchNo);
                        mTeamMatchesScheduleRV.setAdapter(mAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        return mView;
    }

}
