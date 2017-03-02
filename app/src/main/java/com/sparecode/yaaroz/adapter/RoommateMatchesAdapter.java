package com.sparecode.yaaroz.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.model.RoommatesMatchesData;
import com.sparecode.yaaroz.view.CustomTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prima on 30/1/17.
 */

public class RoommateMatchesAdapter extends RecyclerView.Adapter<RoommateMatchesAdapter.RecyclerViewHolder> {

    ArrayList<RoommatesMatchesData> roommatesMatchesDataArrayList;
    Context context;
    int addImages, addDetails;

    public RoommateMatchesAdapter(ArrayList<RoommatesMatchesData> roommatesMatchesDatas, Context context) {
        this.roommatesMatchesDataArrayList = roommatesMatchesDatas;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_roommatematches, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgMainRoommate)
        ImageView imgMainRoommate;
        @Bind(R.id.txtMainNameRoommate)
        CustomTextView txtMainNameRoommate;
        @Bind(R.id.txtMainDetail)
        CustomTextView txtMainDetail;
        @Bind(R.id.txtAge42)
        CustomTextView txtAge42;
        @Bind(R.id.txtGenderM)
        CustomTextView txtGenderM;
        @Bind(R.id.txtMutual)
        CustomTextView txtMutual;
        @Bind(R.id.linearMutual)
        LinearLayout linearMutual;
        @Bind(R.id.txtQuirks)
        CustomTextView txtQuirks;
        @Bind(R.id.linearQuirks)
        LinearLayout linearQuirks;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            for (addImages = 0; addImages <= 10; addImages++) {
                ImageView image = new ImageView(context);
                LinearLayout.LayoutParams layoutParams =new LinearLayout.LayoutParams(100,100);
                layoutParams.setMargins(10,0,10,0);


                image.setLayoutParams(layoutParams);
                image.setImageResource(R.drawable.profilelogogirl2);
                linearMutual.addView(image);
            }
            for (addDetails = 0; addDetails < 3; addDetails++) {
                CustomTextView txtDetails=new CustomTextView(context);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                txtDetails.setLayoutParams(layoutParams);
                txtDetails.setText("Have Pets");
                txtDetails.setTextColor(Color.parseColor("#016FDE"));
                linearQuirks.addView(txtDetails);
            }


        }
    }



}
