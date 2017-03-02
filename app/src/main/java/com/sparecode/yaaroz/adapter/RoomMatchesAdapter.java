package com.sparecode.yaaroz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.R;


/**
 * Created by Sanket on 1/24/2017.
 */

public class RoomMatchesAdapter extends RecyclerView.Adapter<RoomMatchesAdapter.RoomMatchViewHolder> {

    @Override
    public RoomMatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_roommatches, parent, false);

        return new RoomMatchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RoomMatchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class RoomMatchViewHolder extends RecyclerView.ViewHolder {

        public RoomMatchViewHolder(View itemView) {
            super(itemView);
        }
    }
}
