package com.czh.myczhhistory.uis.adapder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.czh.myczhhistory.R;
import com.czh.myczhhistory.entities.TohEntit;
import com.czh.myczhhistory.uis.activity.ContentDetailsActiivty;
import com.leo.afbaselibrary.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created By Admin  on 2020/9/28
 * @Email : 163235610@qq.com
 * @Author:Mrczh
 * @Instructions:
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    List<TohEntit> tohEntits;


    public HomeAdapter(Context context, List<TohEntit> tohEntits) {
        this.context = context;
        this.tohEntits = tohEntits;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itme_home, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TohEntit entit = tohEntits.get(position);
        LogUtils.e(entit.pic);
        GlideUtils.loadRoundCornersImage(context, entit.pic, holder.img,20);
        holder.tvContent.setText(entit.des);
        holder.tvTitle.setText(entit.title);
        holder.tvTime.setText(String.format("%s年%s月%s日    %s",entit.year,entit.month,entit.day,entit.lunar));
        holder.ll_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e(entit._id+"===");
                Intent intent = new Intent(context, ContentDetailsActiivty.class);
                intent.putExtra("id",entit._id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tohEntits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.ll_top)
        LinearLayout ll_top;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
