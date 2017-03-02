package com.sparecode.yaaroz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.model.RecentChatData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prima on 25/1/17.
 */

public class RecentChatAdapter extends RecyclerView.Adapter<RecentChatAdapter.RecentChatHolder> {

    ArrayList<RecentChatData> recentChatDataArrayList;

    public RecentChatAdapter(ArrayList<RecentChatData> recentChatData) {
        this.recentChatDataArrayList = recentChatData;
    }

    @Override
    public RecentChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recentchatpage, parent, false);
        RecentChatHolder recentChatHolder = new RecentChatHolder(view);
        return recentChatHolder;
    }

    @Override
    public void onBindViewHolder(RecentChatHolder holder, int position) {
        holder.txtChatName.setText(recentChatDataArrayList.get(position).getName());
        holder.txtChatDays.setText(recentChatDataArrayList.get(position).getDay());
        holder.txtChatDate.setText(recentChatDataArrayList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return recentChatDataArrayList.size();
    }

    public class RecentChatHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgRecentChat)
        ImageView imgRecentChat;
        @Bind(R.id.txtChatName)
        TextView txtChatName;
        @Bind(R.id.txtChatDays)
        TextView txtChatDays;
        @Bind(R.id.txtChatDate)
        TextView txtChatDate;

        public RecentChatHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }





}
