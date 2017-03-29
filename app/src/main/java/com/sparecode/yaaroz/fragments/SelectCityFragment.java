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
import com.sparecode.yaaroz.dialog.SweetAlertDialog;
import com.sparecode.yaaroz.model.CityData;
import com.sparecode.yaaroz.model.CityWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prima on 24/1/17.
 */

public class SelectCityFragment extends BaseFragment implements SelectCityBackend.CityProvider, RecyclerAdapterCity.onCitySelected {

    ArrayList<Drawable> drawables;
    ArrayList<String> strings;
    @Bind(R.id.recyclerCityView)
    RecyclerView recyclerCityView;
    private SelectCityBackend selectCityBackend;
    List<CityData> cityDataList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selectcity, container, false);


        drawables = new ArrayList<>();
        strings = new ArrayList<>();
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

    RecyclerAdapterCity recyclerAdapterCity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cityDataList = new ArrayList<>();

        recyclerAdapterCity = new RecyclerAdapterCity(cityDataList, this);
        recyclerCityView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        displayCity();
        recyclerCityView.setAdapter(recyclerAdapterCity);
        selectCityBackend = new SelectCityBackend(getActivity(), this);
    }

    @Override
    public void onCityReceive(CityWrapper cityWrapper) {
        if (getActivity() != null) {
            cityDataList.addAll(cityWrapper.getData());
            recyclerAdapterCity.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(String msg) {
        if (getActivity() != null) {
            new SweetAlertDialog(getActivity()).setTitleText("" + msg).show();
        }
    }

    @Override
    public void onCitySelected(CityData cityData) {
        if (getActivity() != null) {
            new SweetAlertDialog(getActivity()).setTitleText("" + cityData.getNCityId()).show();
        }
    }
}
