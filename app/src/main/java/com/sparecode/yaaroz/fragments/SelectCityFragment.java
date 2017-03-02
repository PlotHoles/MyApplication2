package com.sparecode.yaaroz.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.adapter.RecyclerAdapterCity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prima on 24/1/17.
 */

public class SelectCityFragment extends BaseFragment {

    ArrayList<Drawable> drawables;
    ArrayList<String> strings;
    @Bind(R.id.recyclerCityView)
    RecyclerView recyclerCityView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selectcity, container, false);


        drawables = new ArrayList<>();
        strings=new ArrayList<>();
        ButterKnife.bind(this, view);



        return view;
    }

    private void displayCity() {


        drawables.add(getResources().getDrawable(R.drawable.canada));
        drawables.add(getResources().getDrawable(R.drawable.canada));
        drawables.add(getResources().getDrawable(R.drawable.canada));
        drawables.add(getResources().getDrawable(R.drawable.canada));
        drawables.add(getResources().getDrawable(R.drawable.canada));
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbar().setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerAdapterCity recyclerAdapterCity=new RecyclerAdapterCity(drawables);
        recyclerCityView.setLayoutManager(new GridLayoutManager(getContext(),2));
        displayCity();
        recyclerCityView.setAdapter(recyclerAdapterCity);

    }
}
