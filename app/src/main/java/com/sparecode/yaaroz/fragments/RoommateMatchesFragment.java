package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.adapter.RoommateMatchesAdapter;
import com.sparecode.yaaroz.model.RoommatesMatchesData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by prima on 30/1/17.
 */
public class RoommateMatchesFragment extends BaseFragment {

    int chatList;
    RoommatesMatchesData roommatesMatchesData;
    ArrayList<RoommatesMatchesData> roommatesDataArrayList;
    @Bind(R.id.roommateMatchesRecyclerView)
    RecyclerView roommateMatchesRecyclerView;

    public RoommateMatchesFragment() {
    }


    public RoommateMatchesFragment(RoommatesMatchesData roommatesMatchesData) {
        this.roommatesMatchesData = roommatesMatchesData;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_roommate_matches, container, false);
        ButterKnife.bind(this, view);
        return view;
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

        roommatesMatchesData = new RoommatesMatchesData();
        roommatesDataArrayList = new ArrayList<>();


        for (chatList = 0; chatList <= 10; chatList++) {
            roommatesDataArrayList.add(roommatesMatchesData);
        }


        RoommateMatchesAdapter roommateMatchesAdapter = new RoommateMatchesAdapter(roommatesDataArrayList, getActivity());
        roommateMatchesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        roommateMatchesRecyclerView.setAdapter(roommateMatchesAdapter);


    }
}
