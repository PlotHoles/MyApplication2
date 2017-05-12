package com.sparecode.yaaroz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.model.RoomMatchData;
import com.sparecode.yaaroz.utils.DebugLog;
import com.sparecode.yaaroz.view.CustomTextView;
import com.sparecode.yaaroz.view.CustomTextViewBold;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Sanket on 1/24/2017.
 */

public class RoomMatchesAdapter extends RecyclerView.Adapter<RoomMatchesAdapter.RoomMatchViewHolder> {

    private Context mContext;
    private List<RoomMatchData> data;
    private OnRoomSelectListener onRoomSelectListener;

    public RoomMatchesAdapter(Context mContext, List<RoomMatchData> data, OnRoomSelectListener onRoomSelectListener) {
        this.mContext = mContext;
        this.data = data;
        this.onRoomSelectListener = onRoomSelectListener;
    }

    @Override
    public RoomMatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_roommatches, parent, false);

        return new RoomMatchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RoomMatchViewHolder holder, final int position) {

        // Picasso.with(mContext).load(data.get(position).getSPhoto()).into(holder.imgRoomImage);
        //Picasso.with(mContext).load(data.get(position).getSPhoto()).into(holder.imgUserPic);
        //holder.txtUserName.setText("TEST");
        holder.RoomDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRoomSelectListener.onRoomDetailClick(data.get(position));
            }
        });
        holder.frameRoomMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DebugLog.e("BG IMAGE CLICKED::");
            }
        });

        holder.imgUserPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DebugLog.e("USER PIC CLICKED");
            }
        });
        holder.txtUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DebugLog.e("USER NAME CLICKED");
            }
        });
        holder.imgChatIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DebugLog.e("CHAT CLICKED");
            }
        });
        holder.imgFavIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DebugLog.e("FAV CLICKED");
            }
        });
    }

    @Override
    public int getItemCount() {
        DebugLog.e("DATA SIZE::" + data.size());
        return data.size();
    }

    public interface OnRoomSelectListener {
        void onRoomSelected(RoomMatchData roomMatchData);

        void onUserIconSelected(RoomMatchData roomMatchData);

        void onChatIconSelected(RoomMatchData roomMatchData);

        void onFavIconClicked(RoomMatchData roomMatchData);

        void onRoomDetailClick(RoomMatchData roomMatchData);
    }

    class RoomMatchViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgRoomImage)
        ImageView imgRoomImage;
        @Bind(R.id.imgUserPic)
        ImageView imgUserPic;
        @Bind(R.id.txtUserName)
        CustomTextView txtUserName;
        @Bind(R.id.txtUserAge)
        CustomTextViewBold txtUserAge;
        @Bind(R.id.linearUserName)
        LinearLayout linearUserName;
        @Bind(R.id.imgChatIcon)
        ImageView imgChatIcon;
        @Bind(R.id.imgFavIcon)
        ImageView imgFavIcon;
        @Bind(R.id.txtRoomLocation)
        TextView txtRoomLocation;
        @Bind(R.id.txtRoomDate)
        TextView txtRoomDate;
        @Bind(R.id.txtRoomLeaseDate)
        TextView txtRoomLeaseDate;
        @Bind(R.id.txtRoomRent)
        TextView txtRoomRent;
        @Bind(R.id.frameRoomMatch)
        FrameLayout frameRoomMatch;
        @Bind(R.id.RoomDetails)
        View RoomDetails;
        public RoomMatchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
