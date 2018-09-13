package com.learner.iccworldcup2019schedule;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.learner.iccworldcup2019schedule.Adapter.PointTableAdapter;
import com.learner.iccworldcup2019schedule.Common.Common;
import com.learner.iccworldcup2019schedule.Model.PointTable;

import java.util.ArrayList;
import java.util.List;

public class PointTableActivity extends AppCompatActivity {
    private RecyclerView mPointTableRecyclerView;
    private PointTableAdapter mAdapter;
    private List<PointTable> mPointTableList = new ArrayList<>();
    private DatabaseReference mRootRef;
    private DatabaseReference mPointTableRef;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_table);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("World Cup 2019, Point Table");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPointTableRecyclerView = findViewById(R.id.point_table_rv);
        mProgressBar = findViewById(R.id.progress_bar);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.keepSynced(true);
        mPointTableRef = mRootRef.child(Common.POINT_TABLE_REF);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mPointTableRecyclerView.setHasFixedSize(true);
        mPointTableRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mPointTableRecyclerView.getContext(),
                layoutManager.getOrientation());
        mPointTableRecyclerView.addItemDecoration(dividerItemDecoration);
        mProgressBar.setVisibility(View.VISIBLE);
        mPointTableRef.orderByChild("position").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPointTableList.clear();
                for (DataSnapshot postData: dataSnapshot.getChildren()){
                    PointTable pointTable = postData.getValue(PointTable.class);
                    mPointTableList.add(pointTable);
                }
                Log.e(Common.TAG, "onDataChange: "+mPointTableList.size() );
                if (mPointTableList.size()>0){
                    mAdapter = new PointTableAdapter(getApplicationContext(),mPointTableList);
                    mPointTableRecyclerView.setAdapter(mAdapter);
                    mProgressBar.setVisibility(View.GONE);
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
