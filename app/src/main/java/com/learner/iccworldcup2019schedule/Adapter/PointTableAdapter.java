package com.learner.iccworldcup2019schedule.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learner.iccworldcup2019schedule.Model.PointTable;
import com.learner.iccworldcup2019schedule.R;

import java.util.ArrayList;
import java.util.List;

public class PointTableAdapter extends RecyclerView.Adapter<PointTableAdapter.PointTableHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<PointTable> mPointTableList = new ArrayList<PointTable>();

    public PointTableAdapter(Context context, List<PointTable> pointTableList) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mPointTableList = pointTableList;
    }

    @NonNull
    @Override
    public PointTableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.point_table_item, parent, false);
        return new PointTableHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PointTableHolder holder, int position) {
        if (position<4){
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.semifinal_list_color));
        }
        holder.mTeamNameTV.setText(mPointTableList.get(position).getTeam());
        holder.mPlayedMatchTV.setText(mPointTableList.get(position).getMatchs());
        holder.mWonMatchTV.setText(mPointTableList.get(position).getWon());
        holder.mLostMatchTV.setText(mPointTableList.get(position).getLost());
        holder.mNoResultTV.setText(mPointTableList.get(position).getNoResult());
        holder.mTotalPointTV.setText(mPointTableList.get(position).getPoints());
        holder.mNetRunRateTV.setText(mPointTableList.get(position).getNetRunRate());
    }

    @Override
    public int getItemCount() {
        return mPointTableList.size();
    }

    public class PointTableHolder extends RecyclerView.ViewHolder{
        public TextView mTeamNameTV, mPlayedMatchTV, mWonMatchTV, mLostMatchTV, mNoResultTV, mTotalPointTV, mNetRunRateTV;
        public PointTableHolder(View itemView) {
            super(itemView);
            mTeamNameTV = itemView.findViewById(R.id.team_name_textview);
            mPlayedMatchTV = itemView.findViewById(R.id.played_match_textview);
            mWonMatchTV = itemView.findViewById(R.id.won_match_textview);
            mLostMatchTV = itemView.findViewById(R.id.lost_match_textview);
            mNoResultTV = itemView.findViewById(R.id.no_result_textview);
            mTotalPointTV = itemView.findViewById(R.id.get_point_textview);
            mNetRunRateTV = itemView.findViewById(R.id.net_runrate_textview);
        }
    }
}
