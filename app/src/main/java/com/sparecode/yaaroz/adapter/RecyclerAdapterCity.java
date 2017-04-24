package com.sparecode.yaaroz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.model.CityData;
import com.sparecode.yaaroz.utils.DebugLog;
import com.sparecode.yaaroz.view.CustomTextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prima on 27/1/17.
 */

public class RecyclerAdapterCity extends RecyclerView.Adapter<RecyclerAdapterCity.RecyclerHolder> {


    //ArrayList<Drawable> drawable;
    private List<CityData> cityDatas;
    private onCitySelected onCitySelected;

    public RecyclerAdapterCity(List<CityData> cityDatas, onCitySelected onCitySelected) {
        this.cityDatas = cityDatas;
        this.onCitySelected = onCitySelected;
    }

    Context mContext;

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_selectcity, parent, false);
        RecyclerHolder rc = new RecyclerHolder(view);
        mContext = parent.getContext();
        return rc;
    }

    @Override
    public void onBindViewHolder(final RecyclerHolder holder, final int position) {

        holder.txtCityName.setText(cityDatas.get(position).getSCityName());
        /*holder.txtCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCitySelected.onCitySelected(cityDatas.get(position));
            }
        });*/
/*        holder.imgSelectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imgSelectCity.setSelected(true);

            }
        });*/
        holder.frameCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.frameCity.setSelected(true);
                if (holder.imgSelector.getVisibility() == View.VISIBLE) {
                    holder.imgSelector.setVisibility(View.GONE);
                } else {
                    holder.imgSelector.setVisibility(View.VISIBLE);
                }

                DebugLog.e("FRAME CLICKED::");
                onCitySelected.onCitySelected(cityDatas.get(position));
            }
        });
    }

    public interface onCitySelected {
        void onCitySelected(CityData cityData);
    }

    @Override
    public int getItemCount() {
        return cityDatas.size();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgSelectCity)
        ImageView imgSelectCity;
        @Bind(R.id.txtCityName)
        CustomTextView txtCityName;
        @Bind(R.id.frameCity)
        FrameLayout frameCity;
        @Bind(R.id.imgSelector)
        ImageButton imgSelector;

        public RecyclerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}


