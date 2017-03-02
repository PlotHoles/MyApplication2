package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.adapter.RoomMatchesAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sanket on 1/24/2017.
 */

public class RoomMatchesFragment extends BaseFragment {

    @Bind(R.id.roomMatchRecycler)
    RecyclerView roomMatchRecycler;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_roommatch, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        roomMatchRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        roomMatchRecycler.setAdapter(new RoomMatchesAdapter());
    }


    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getTxtTitle().setText("Room Matches");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
