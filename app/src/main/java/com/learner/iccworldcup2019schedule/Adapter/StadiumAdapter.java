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
import com.learner.iccworldcup2019schedule.Model.Stadium;
import com.learner.iccworldcup2019schedule.R;

import java.util.ArrayList;
import java.util.List;

public class StadiumAdapter extends RecyclerView.Adapter<StadiumAdapter.StadiumViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Stadium> mStadiumList = new ArrayList<Stadium>();
    private ItemClickListener itemClickListener;

    public StadiumAdapter(Context context, List<Stadium> stadiumList) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mStadiumList = stadiumList;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public StadiumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.stadium_list_item,parent,false);
        return new StadiumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StadiumViewHolder holder, final int position) {
        Glide.with(mContext).load(mStadiumList.get(position).getImageUrl())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.mStadiumImageView);
        holder.mStadiumNameTextView.setText(mStadiumList.get(position).getGround());
        holder.mStadiumCityTextView.setText(mStadiumList.get(position).getCity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener!=null){
                    itemClickListener.onClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStadiumList.size();
    }

    public class StadiumViewHolder extends RecyclerView.ViewHolder{

        public ImageView mStadiumImageView;
        public TextView mStadiumNameTextView, mStadiumCityTextView;

        public StadiumViewHolder(final View itemView) {
            super(itemView);

            mStadiumImageView = itemView.findViewById(R.id.stadium_imageview);
            mStadiumNameTextView = itemView.findViewById(R.id.stadium_name_textview);
            mStadiumCityTextView = itemView.findViewById(R.id.stadium_city_textview);

        }
    }
}
