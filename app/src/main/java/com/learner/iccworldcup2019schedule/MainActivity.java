package com.learner.iccworldcup2019schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.learner.iccworldcup2019schedule.Common.Common;
import com.learner.iccworldcup2019schedule.Model.Stats;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int mDayLeft;
    private Date mDate = new Date();
    private long mCurrentDate;
    private long mStartDate;
    private TextView mDayLeftTV;
    private DatabaseReference mRootRef;
    private DatabaseReference mStatsRef;
    private Stats mStats;
    private TextView mTotalPlayedMatchTV, mTotalMatchesTV, mTotalRunsTV, mAverageRunsTV, mTotalWicketsTV, mAverageWicketTV;
    private TextView mTotalSixesTV, mAverageSixTV, mTotalFoursTV, mAverageFourTV, mTotalCenturyTV, mAverageCenturyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Word Cup 2019");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDayLeftTV = findViewById(R.id.day_left_textview);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mCurrentDate = mDate.getTime() / 1000;
        mStartDate = getDateInUnix("30/05/2019");

        mDayLeft = (int) ((mStartDate - mCurrentDate) / 86400);
        mTotalPlayedMatchTV = findViewById(R.id.total_played_matche_textview);
        mTotalMatchesTV = findViewById(R.id.total_match);
        mTotalRunsTV = findViewById(R.id.total_runs_textview);
        mAverageRunsTV = findViewById(R.id.average_run_per_match_textview);
        mTotalWicketsTV = findViewById(R.id.total_wicket_textview);
        mAverageWicketTV = findViewById(R.id.average_wicket_per_match_textview);
        mTotalSixesTV = findViewById(R.id.total_sixes_textview);
        mAverageSixTV = findViewById(R.id.average_six_per_match_textview);
        mTotalFoursTV = findViewById(R.id.total_four_textview);
        mAverageFourTV = findViewById(R.id.average_four_per_match_textview);
        mTotalCenturyTV = findViewById(R.id.total_century_textview);
        mAverageCenturyTV = findViewById(R.id.average_century_per_match_textview);

        mDayLeftTV.setText(mDayLeft + "");
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mRootRef.keepSynced(true);
        mStatsRef = mRootRef.child(Common.STATS_REF);

        mStatsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mStats = dataSnapshot.getValue(Stats.class);
                setData();
                Log.e(Common.TAG, "onDataChange: " + mStats.getTotalPlayedMatchs());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setData() {
        mTotalPlayedMatchTV.setText(mStats.getTotalPlayedMatchs());
        mTotalMatchesTV.setText(mStats.getTotalMatchs());
        mTotalRunsTV.setText(mStats.getTotalRuns());
        mAverageRunsTV.setText(mStats.getAverageRunPerMatch() + " per match");
        mTotalWicketsTV.setText(mStats.getTotalWickets());
        mAverageWicketTV.setText(mStats.getAverageWicketPerMatch() + " per match");
        mTotalSixesTV.setText(mStats.getTotalSixes());
        mAverageSixTV.setText(mStats.getAverageSixPerMatch() + " per match");
        mTotalFoursTV.setText(mStats.getTotalFours());
        mAverageFourTV.setText(mStats.getAverageFourPerMatch() + " per match");
        mTotalCenturyTV.setText(mStats.getTotalCentury());
        mAverageCenturyTV.setText(mStats.getAverageCenturyPerMatch() + " per match");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            setData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()) {
            case R.id.nav_schedule:
                startActivity(new Intent(getApplicationContext(), ScheduleActivity.class));
                break;
            case R.id.nav_team:
                startActivity(new Intent(getApplicationContext(),TeamActivity.class));
                break;
            case R.id.nav_point_table:
                startActivity(new Intent(getApplicationContext(), PointTableActivity.class));
                break;
            case R.id.nav_stadium:
                startActivity(new Intent(getApplicationContext(), StadiumActivity.class));
                break;
            case R.id.nav_share:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static long getDateInUnix(String date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        long unixTime = 0;
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+6:00"));
        try {
            unixTime = dateFormat.parse(date).getTime();
            unixTime = unixTime / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return unixTime;
    }
}
