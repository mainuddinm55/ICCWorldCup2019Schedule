package com.learner.iccworldcup2019schedule.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.learner.iccworldcup2019schedule.Model.MatchSchedule;
import com.learner.iccworldcup2019schedule.R;
import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<MatchSchedule> mMatchScheduleList = new ArrayList<MatchSchedule>();
    private List<Integer> mMatchNo = new ArrayList<>();

    public ScheduleAdapter(Context mContext, List<MatchSchedule> mMatchScheduleList, List<Integer> matchNo) {
        this.mContext = mContext;
        this.mMatchScheduleList = mMatchScheduleList;
        this.mMatchNo = matchNo;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.match_list_item, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Glide.with(mContext).load(mMatchScheduleList.get(position).getTeam1_flag_url())
                .into(holder.mTeamOneFlagImageView);

        Glide.with(mContext).load(mMatchScheduleList.get(position).getTeam2_flag_url())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.mTeamTwoFlagImageView);

        holder.mTeamOneNameTextView.setText(mMatchScheduleList.get(position).getTeam1());
        holder.mTeamTwoNameTextView.setText(mMatchScheduleList.get(position).getTeam2());
        holder.mMatchDateTextView.setText("Date: " + mMatchScheduleList.get(position).getDate());
        holder.mMatchTimeTextView.setText("Time: " + mMatchScheduleList.get(position).getTime());
        holder.mMatchVenueTextView.setText("Venue: " + mMatchScheduleList.get(position).getStadium());
        holder.mMatchNoTextView.setText("Match " + mMatchNo.get(position));
        holder.mMatchVSTextView.setText(mContext.getResources().getString(R.string.team_vs_text));
    }

    @Override
    public int getItemCount() {
        return mMatchScheduleList.size();
    }


    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mTeamOneFlagImageView;
        public ImageView mTeamTwoFlagImageView;
        public TextView mTeamOneNameTextView;
        public TextView mTeamTwoNameTextView;
        public TextView mMatchDateTextView;
        public TextView mMatchTimeTextView;
        public TextView mMatchVenueTextView;
        public TextView mMatchVSTextView;
        public TextView mMatchNoTextView;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            mTeamOneFlagImageView = itemView.findViewById(R.id.team_one_flag);
            mTeamTwoFlagImageView = itemView.findViewById(R.id.team_two_flag);
            mTeamOneNameTextView = itemView.findViewById(R.id.team_one_name);
            mTeamTwoNameTextView = itemView.findViewById(R.id.team_two_name);
            mMatchDateTextView = itemView.findViewById(R.id.match_start_date);
            mMatchTimeTextView = itemView.findViewById(R.id.match_start_time);
            mMatchVenueTextView = itemView.findViewById(R.id.match_venue_name);
            mMatchNoTextView = itemView.findViewById(R.id.match_no);
            mMatchVSTextView = itemView.findViewById(R.id.vs);
        }
    }
}
