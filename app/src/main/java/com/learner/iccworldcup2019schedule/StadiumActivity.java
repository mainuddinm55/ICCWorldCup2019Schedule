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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.learner.iccworldcup2019schedule.Adapter.StadiumAdapter;
import com.learner.iccworldcup2019schedule.Common.Common;
import com.learner.iccworldcup2019schedule.Interface.ItemClickListener;
import com.learner.iccworldcup2019schedule.Model.Stadium;

import java.util.ArrayList;
import java.util.List;

public class StadiumActivity extends AppCompatActivity {
    private RecyclerView mStadiumRecyclerView;
    private StadiumAdapter mAdapter;
    private List<Stadium> mStadiumList = new ArrayList<>();
    private DatabaseReference mRootRef;
    private DatabaseReference mStadiumRef;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("World Cup 2019, Stadiums");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mStadiumRecyclerView = findViewById(R.id.stadium_recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.keepSynced(true);
        mStadiumRef = mRootRef.child(Common.STADIUM_REF);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mStadiumRecyclerView.setHasFixedSize(true);
        mStadiumRecyclerView.setLayoutManager(layoutManager);

        mProgressBar.setVisibility(View.VISIBLE);
        mStadiumRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mStadiumList.clear();
                for (DataSnapshot postData : dataSnapshot.getChildren()){
                    Stadium stadium = postData.getValue(Stadium.class);
                    mStadiumList.add(stadium);
                }
                if (mStadiumList.size()>0){
                    mAdapter = new StadiumAdapter(getApplicationContext(),mStadiumList);
                    mStadiumRecyclerView.setAdapter(mAdapter);
                    mProgressBar.setVisibility(View.GONE);
                    mAdapter.setItemClickListener(new ItemClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            startActivity(new Intent(getApplicationContext(),StadiumDetailsActivity.class)
                                    .putExtra(Common.STADIUM_KEY,mStadiumList.get(position)));
                        }
                    });
                }

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
