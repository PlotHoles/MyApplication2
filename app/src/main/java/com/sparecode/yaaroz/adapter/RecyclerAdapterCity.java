package com.sparecode.yaaroz.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sparecode.yaaroz.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prima on 27/1/17.
 */

public class RecyclerAdapterCity extends RecyclerView.Adapter<RecyclerAdapterCity.RecyclerHolder>  {


    ArrayList<Drawable> drawable;
    public RecyclerAdapterCity(ArrayList<Drawable> drawables)
    {
        this.drawable=drawables;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_selectcity, parent, false);
        RecyclerHolder rc = new RecyclerHolder(view);
        return rc;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.imgSelectCity.setImageDrawable(drawable.get(position));
    }

    @Override
    public int getItemCount() {
        return drawable.size();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgSelectCity)
        ImageView imgSelectCity;

        public RecyclerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}


