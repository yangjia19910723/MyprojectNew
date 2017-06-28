package com.example.pangjia.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by pangjia on 2017/6/14.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruits;
    private Context mContext;
    public FruitAdapter( List<Fruit> mFruits){
        this.mFruits=mFruits;
    }

    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getAdapterPosition();
                Intent mIntent=new Intent(mContext,FruitActivity.class);
                mIntent.putExtra(FruitActivity.FRUIT_NAME,mFruits.get(pos).getName());
                mIntent.putExtra(FruitActivity.FRUIT_IMAGE_ID,mFruits.get(pos).getImgId());
                mContext.startActivity(mIntent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
             Fruit mFruit=mFruits.get(position);
            holder.mTv.setText(mFruit.getName());
//            Glide.with(mContext).load(mFruit.getImgId()).into(holder.mImg);
            Picasso.with(mContext).load(mFruit.getImgId()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).into(holder.mImg);
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView mImg;
        TextView mTv;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView;
            mImg=(ImageView)itemView.findViewById(R.id.img);
            mTv=(TextView)itemView.findViewById(R.id.fruit_name);
        }
    }

    @Override
    public int getItemCount() {
        return mFruits.size();
    }
}
