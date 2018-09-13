package com.learner.iccworldcup2019schedule.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.learner.iccworldcup2019schedule.Model.Stadium;
import com.learner.iccworldcup2019schedule.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class StadiumMatchesFragment extends Fragment {

    private View mView;
    private RecyclerView mRecyclerView;
    private List<MatchSchedule> mMatchScheduleList = new ArrayList<>();
    private List<Integer> mMatchNo = new ArrayList<>();
    private ScheduleAdapter adapter;
    private DatabaseReference mRootRef;
    private DatabaseReference mScheduleRef;

    public StadiumMatchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_stadium_matches,container,false);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.keepSynced(true);
        mScheduleRef = mRootRef.child(Common.SCHEDULE_REF);

        Intent intent = getActivity().getIntent();
        if (intent!=null){
            final Stadium stadium = (Stadium) intent.getSerializableExtra(Common.STADIUM_KEY);
            getActivity().setTitle(stadium.getGround()+", "+stadium.getCity());
            mRecyclerView = mView.findViewById(R.id.stadium_match_list_recylcerview);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mScheduleRef.orderByKey().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mMatchScheduleList.clear();
                    mMatchNo.clear();
                    for (DataSnapshot postData: dataSnapshot.getChildren()){
                        MatchSchedule matchSchedule = postData.getValue(MatchSchedule.class);
                        if (matchSchedule.getStadium().startsWith(stadium.getGround())){
                            mMatchScheduleList.add(matchSchedule);
                            mMatchNo.add(Integer.valueOf(postData.getKey()));
                            Log.e(Common.TAG, "onDataChange: "+postData.getKey()+" "+matchSchedule.getStadium());
                        }

                    }
                    if (mMatchScheduleList.size()>0){
                        adapter = new ScheduleAdapter(getContext(),mMatchScheduleList,mMatchNo);
                        mRecyclerView.setAdapter(adapter);
                    }
                    Log.e(Common.TAG, "onDataChange: "+mMatchScheduleList.size()+" "+stadium.getGround() );

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        return mView;
    }

}
