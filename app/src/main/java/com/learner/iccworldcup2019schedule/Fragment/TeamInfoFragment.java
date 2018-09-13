package com.learner.iccworldcup2019schedule.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learner.iccworldcup2019schedule.Common.Common;
import com.learner.iccworldcup2019schedule.Model.Team;
import com.learner.iccworldcup2019schedule.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamInfoFragment extends Fragment {

    private Team mTeam;
    private TextView  mAboutTeamTextTV, mNicknameTextTV, mNicknameTV, mCoachNameTextTV, mCoachNameTV;
    private TextView mRankingTextTV, mRankingTV, mParticipationTextTV, mParticipationTV, mPerformanceTextTV, mPerformanceTV;
    private TextView mSponsorTextTV, mSponsorTv;
    private View mView;

    public TeamInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_team_info, container, false);

        initialization();

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle!=null){
            mTeam = (Team) bundle.getSerializable(Common.TEAM_KEY);
            setData();
        }
        return mView;
    }

    private void setData() {
        mAboutTeamTextTV.setText("About "+mTeam.getName());
        mNicknameTextTV.setText(getActivity().getResources().getString(R.string.nickname_text));
        mNicknameTV.setText(mTeam.getNickName());
        mCoachNameTextTV.setText(getActivity().getResources().getString(R.string.coach_text));
        mCoachNameTV.setText(mTeam.getCoach());
        mRankingTextTV.setText(getActivity().getResources().getString(R.string.ranking_text));
        mRankingTV.setText(mTeam.getIccRanking());
        mParticipationTextTV.setText(getActivity().getResources().getString(R.string.wc_participation_text));
        mParticipationTV.setText(mTeam.getWorldCupPerticipant());
        mPerformanceTextTV.setText(getActivity().getResources().getString(R.string.best_performance_text));
        mPerformanceTV.setText(mTeam.getBestPerformance());
        mSponsorTextTV.setText(getActivity().getResources().getString(R.string.sponsor_text));
        mSponsorTv.setText(mTeam.getSponsor());

    }

    private void initialization() {
        mAboutTeamTextTV = mView.findViewById(R.id.about_team_textview);
        mNicknameTextTV = mView.findViewById(R.id.nickname_text_textview);
        mNicknameTV = mView.findViewById(R.id.nickname_textview);
        mCoachNameTextTV = mView.findViewById(R.id.coachname_text_textview);
        mCoachNameTV = mView.findViewById(R.id.coachname_textview);
        mRankingTextTV = mView.findViewById(R.id.ranking_text_textview);
        mRankingTV = mView.findViewById(R.id.ranking_textview);
        mParticipationTextTV = mView.findViewById(R.id.participation_text_textview);
        mParticipationTV = mView.findViewById(R.id.participation_textview);
        mPerformanceTextTV = mView.findViewById(R.id.performance_text_textview);
        mPerformanceTV = mView.findViewById(R.id.performance_textview);
        mSponsorTextTV = mView.findViewById(R.id.sponsor_text_textview);
        mSponsorTv = mView.findViewById(R.id.sponsor_textview);
    }

}
