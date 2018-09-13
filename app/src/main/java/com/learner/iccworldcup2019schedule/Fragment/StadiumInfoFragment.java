package com.learner.iccworldcup2019schedule.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.learner.iccworldcup2019schedule.Common.Common;
import com.learner.iccworldcup2019schedule.Model.Stadium;
import com.learner.iccworldcup2019schedule.R;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class StadiumInfoFragment extends Fragment {

    private ImageView mStadiumImageImageView;
    private TextView mStadiumInfoTextTV;
    private TextView mGroundTextTV, mGroundNameTV, mCityTextTV, mCityNameTV, mEstablishTextTV, mEstablishTV, mHomeGroundTextTV, mHomeGroundTV;
    private TextView mCapacityTextTV, mCapacityTV, mFloodLightTextTV, mFloodLightTV, mEndNameTextTV, mEndNameTV, mMatchesTextTV, mMatchesTV;
    private View view;
    private Stadium mStadium;

    public StadiumInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_stadium_info,container,false);


        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle!=null){
            mStadium = (Stadium) bundle.getSerializable(Common.STADIUM_KEY);
            getActivity().setTitle(mStadium.getGround()+", "+mStadium.getCity());
            initialization();

            setupView();
        }

        return view;
    }

    private void setupView() {
        Glide.with(getActivity()).load(mStadium.getImageUrl())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .apply(RequestOptions.placeholderOf(R.drawable.placeholder))
                .into(mStadiumImageImageView);

        Log.e(TAG, "setupView: "+mStadium.getImageUrl() );

        mStadiumInfoTextTV.setText(getActivity().getResources().getString(R.string.stadium_info_text));
        mGroundTextTV.setText(getActivity().getResources().getString(R.string.stadium_name_text));
        mGroundNameTV.setText(": "+mStadium.getGround());
        mCityTextTV.setText(getActivity().getResources().getString(R.string.stadium_city_text));
        mCityNameTV.setText(": "+mStadium.getCity());
        mEstablishTextTV.setText(getActivity().getResources().getString(R.string.stadium_establish_text));
        mEstablishTV.setText(": "+mStadium.getEstablished());
        mHomeGroundTextTV.setText(getActivity().getResources().getString(R.string.stadium_homeground_text));
        mHomeGroundTV.setText(": "+mStadium.getHome_ground());
        mCapacityTextTV.setText(getActivity().getResources().getString(R.string.stadium_capacity_text));
        mCapacityTV.setText(": "+mStadium.getCapacity());
        mFloodLightTextTV.setText(getActivity().getResources().getString(R.string.stadium_floodlight_text));
        mFloodLightTV.setText(": "+mStadium.getFloodLight());
        mEndNameTextTV.setText(getActivity().getResources().getString(R.string.stadium_end_name_text));
        mEndNameTV.setText(": "+mStadium.getEndNames());
        mMatchesTextTV.setText(getActivity().getResources().getString(R.string.stadium_match_text));
        mMatchesTV.setText(": "+mStadium.getMatches());

    }

    private void initialization() {
        mStadiumInfoTextTV = view.findViewById(R.id.stadium_info_text_textview);
        mStadiumImageImageView = view.findViewById(R.id.stadium_image_imageview);
        mGroundTextTV = view.findViewById(R.id.ground_name_text_textview);
        mGroundNameTV = view.findViewById(R.id.ground_name_textview);
        mCityTextTV = view.findViewById(R.id.city_text_textview);
        mCityNameTV = view.findViewById(R.id.city_textview);
        mEstablishTextTV = view.findViewById(R.id.establish_text_textview);
        mEstablishTV = view.findViewById(R.id.establish_textview);
        mHomeGroundTextTV = view.findViewById(R.id.homeground_text_textview);
        mHomeGroundTV = view.findViewById(R.id.homeground_textview);
        mCapacityTextTV = view.findViewById(R.id.capacity_text_textview);
        mCapacityTV = view.findViewById(R.id.capacity_textview);
        mFloodLightTextTV = view.findViewById(R.id.flood_light_text_textview);
        mFloodLightTV = view.findViewById(R.id.flood_light_textview);
        mEndNameTextTV = view.findViewById(R.id.end_name_text_textview);
        mEndNameTV = view.findViewById(R.id.end_name_textview);
        mMatchesTextTV = view.findViewById(R.id.matches_text_textview);
        mMatchesTV = view.findViewById(R.id.matches_textview);
    }

}
