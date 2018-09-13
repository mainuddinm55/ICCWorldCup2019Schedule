package com.learner.iccworldcup2019schedule;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.learner.iccworldcup2019schedule.Adapter.ViewpagerAdapter;
import com.learner.iccworldcup2019schedule.Common.Common;
import com.learner.iccworldcup2019schedule.Fragment.StadiumInfoFragment;
import com.learner.iccworldcup2019schedule.Fragment.StadiumMatchesFragment;
import com.learner.iccworldcup2019schedule.Fragment.TeamInfoFragment;
import com.learner.iccworldcup2019schedule.Fragment.TeamMatchsFragment;
import com.learner.iccworldcup2019schedule.Fragment.TeamSquadFragment;
import com.learner.iccworldcup2019schedule.Model.Team;

public class TeamDetailsActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Team mTeam;
    private ImageView mTeamImageIV;
    private TextView mTeamNameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        mTeamImageIV = findViewById(R.id.team_image_imageview);
        mTeamNameTV = findViewById(R.id.team_name_textview);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            mTeam = (Team) bundle.getSerializable(Common.TEAM_KEY);

            setTitle(mTeam.getName());
            Glide.with(this).load(mTeam.getImageUrl())
                    .apply(RequestOptions.placeholderOf(R.drawable.placeholder))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                    .into(mTeamImageIV);
            mTeamNameTV.setText(mTeam.getName());
        }
    }

    private void setupViewPager(ViewPager viewPager){
        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TeamInfoFragment(),"INFO");
        adapter.addFragment(new TeamSquadFragment(),"SQUAD");
        adapter.addFragment(new TeamMatchsFragment(),"MATCHES");
        viewPager.setAdapter(adapter);
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
