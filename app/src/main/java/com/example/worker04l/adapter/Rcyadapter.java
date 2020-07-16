package com.example.worker04l.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.worker04l.R;
import com.example.worker04l.bean.Shitilei;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Rcyadapter extends RecyclerView.Adapter {
    private ArrayList<Shitilei.DataBean.DatasBean> list;
    private Context context;

    public Rcyadapter(ArrayList<Shitilei.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcy_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemchickLis!=null){
                    onItemchickLis.Chick(holder.getLayoutPosition());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Shitilei.DataBean.DatasBean bean = list.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        Glide.with(context).load(bean.getEnvelopePic()).into(holder1.rcy_iv);
        holder1.rcy_tv.setText(bean.getProjectLink());
    }
    public interface OnItemchickLis{
        void Chick(int position);
    }
    OnItemchickLis onItemchickLis;

    public OnItemchickLis getOnItemchickLis() {
        return onItemchickLis;
    }

    public void setOnItemchickLis(OnItemchickLis onItemchickLis) {
        this.onItemchickLis = onItemchickLis;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView rcy_iv;
        public TextView rcy_tv;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.rcy_iv = (ImageView) rootView.findViewById(R.id.rcy_iv);
            this.rcy_tv = (TextView) rootView.findViewById(R.id.rcy_tv);
        }

    }
}
