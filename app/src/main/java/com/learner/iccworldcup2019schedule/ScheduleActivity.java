package com.learner.iccworldcup2019schedule;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.learner.iccworldcup2019schedule.Common.Common;
import com.learner.iccworldcup2019schedule.Model.MatchSchedule;
import com.learner.iccworldcup2019schedule.Adapter.ScheduleAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<MatchSchedule> mMatchScheduleList = new ArrayList<>();
    private List<Integer> mMatchNo = new ArrayList<>();
    private ScheduleAdapter adapter;
    private DatabaseReference mRootRef;
    private DatabaseReference mScheduleRef;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("World Cup 2019, Schedule");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.keepSynced(true);
        mScheduleRef = mRootRef.child(Common.SCHEDULE_REF);

        mRecyclerView = findViewById(R.id.match_list);
        mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mProgressBar.setVisibility(View.VISIBLE);
        mScheduleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postData: dataSnapshot.getChildren()){
                    MatchSchedule matchSchedule = postData.getValue(MatchSchedule.class);
                    mMatchScheduleList.add(matchSchedule);
                    mMatchNo.add(Integer.valueOf(postData.getKey()));
                }
                if (mMatchScheduleList.size()>0){
                    adapter = new ScheduleAdapter(getApplicationContext(),mMatchScheduleList,mMatchNo);
                    mRecyclerView.setAdapter(adapter);
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
