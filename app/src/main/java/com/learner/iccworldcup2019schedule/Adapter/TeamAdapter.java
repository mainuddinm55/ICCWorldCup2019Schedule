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
import com.learner.iccworldcup2019schedule.Interface.ItemClickListener;
import com.learner.iccworldcup2019schedule.Model.Team;
import com.learner.iccworldcup2019schedule.R;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamHolder>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Team> mTeamList = new ArrayList<>();
    private ItemClickListener mItemClickListener;

    public TeamAdapter(Context context, List<Team> teamList) {
        this.mContext = context;
        this.mTeamList = teamList;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void setmItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public TeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.team_row_item,parent,false);
        return new TeamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamHolder holder, final int position) {
        Glide.with(mContext).load(mTeamList.get(position).getImageUrl())
                .apply(RequestOptions.placeholderOf(R.drawable.placeholder))
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.mTeamImageView);

        holder.mTeamNameTV.setText(mTeamList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener!=null){
                    mItemClickListener.onClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTeamList.size();
    }

    public class TeamHolder extends RecyclerView.ViewHolder {
        public ImageView mTeamImageView;
        public TextView mTeamNameTV;

        public TeamHolder(View itemView) {
            super(itemView);
            mTeamImageView = itemView.findViewById(R.id.team_image_imageview);
            mTeamNameTV = itemView.findViewById(R.id.team_name_textview);
        }
    }
}
