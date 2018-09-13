package com.learner.iccworldcup2019schedule;

import android.content.Intent;
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
import com.learner.iccworldcup2019schedule.Adapter.StadiumAdapter;
import com.learner.iccworldcup2019schedule.Adapter.TeamAdapter;
import com.learner.iccworldcup2019schedule.Common.Common;
import com.learner.iccworldcup2019schedule.Interface.ItemClickListener;
import com.learner.iccworldcup2019schedule.Model.Stadium;
import com.learner.iccworldcup2019schedule.Model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamActivity extends AppCompatActivity {
    private RecyclerView mTeamRecyclerView;
    private TeamAdapter mAdapter;
    private List<Team> mTeamList = new ArrayList<>();
    private DatabaseReference mRootRef;
    private DatabaseReference mTeamRef;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("World Cup 2019, Teams");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTeamRecyclerView = findViewById(R.id.team_recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.keepSynced(true);
        mTeamRef = mRootRef.child(Common.TEAM_REF);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mTeamRecyclerView.setHasFixedSize(true);
        mTeamRecyclerView.setLayoutManager(layoutManager);

        mProgressBar.setVisibility(View.VISIBLE);

        mTeamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTeamList.clear();
                for (DataSnapshot postData : dataSnapshot.getChildren()){
                    Team team = postData.getValue(Team.class);
                    mTeamList.add(team);
                }
                if (mTeamList.size()>0){
                    mAdapter = new TeamAdapter(getApplicationContext(),mTeamList);
                    mTeamRecyclerView.setAdapter(mAdapter);
                    mProgressBar.setVisibility(View.GONE);
                }
                mAdapter.setmItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        startActivity(new Intent(getApplicationContext(),TeamDetailsActivity.class).putExtra(Common.TEAM_KEY,mTeamList.get(position)));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
