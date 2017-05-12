package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.adapter.RoomMatchesAdapter;
import com.sparecode.yaaroz.model.RoomMatchData;
import com.sparecode.yaaroz.model.RoomMatchWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sanket on 1/24/2017.
 */

public class RoomMatchesFragment extends BaseFragment implements RoomMatchBackend.RoomMatchesProvider,RoomMatchFilterBackend.RoomMatchFilterProvider {

    @Bind(R.id.roomMatchRecycler)
    RecyclerView roomMatchRecycler;
    @Bind(R.id.fabroommatchLocation)
    FloatingActionButton fabroommatchLocation;
    @Bind(R.id.fabroommatchFilter)
    FloatingActionButton fabroommatchFilter;
    private View view;
    private String nCityId = "1";
    private String nLocationId = "1";
    RoomMatchWrapper roomMatchWrapper;
    private String RoomId;
    public RoomMatchesFragment() {
    }

    public RoomMatchesFragment(RoomMatchWrapper roomMatchWrapper) {
        this.roomMatchWrapper = roomMatchWrapper;
        Log.e("roomcons",roomMatchWrapper.getData().get(0).getNCityId());
       // data.addAll(roomMatchWrapper.getData());
    }

    public void setnCityId(String nCityId) {
        this.nCityId = nCityId;
    }

    public void setnLocationId(String nLocationId) {
        this.nLocationId = nLocationId;
    }

    private RoomMatchBackend roomMatchBackend;
    private List<RoomMatchData> data;
    private RoomMatchesAdapter roomMatchesAdapter;

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
        data = new ArrayList<>();
        roomMatchRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        roomMatchesAdapter = new RoomMatchesAdapter(getActivity(), data, new RoomMatchesAdapter.OnRoomSelectListener() {
            @Override
            public void onRoomSelected(RoomMatchData roomMatchData) {

            }

            @Override
            public void onUserIconSelected(RoomMatchData roomMatchData) {

            }

            @Override
            public void onChatIconSelected(RoomMatchData roomMatchData) {

            }

            @Override
            public void onFavIconClicked(RoomMatchData roomMatchData) {

            }
            @Override
            public void onRoomDetailClick(RoomMatchData roomMatchData) {
                RoomId =roomMatchData.getNRoomId();
                Log.e("::","ID::"+RoomId);
                mainNavInterface.openRoomDetailsPage(RoomId);
            }
        });
        //Log.e("room",roomMatchFilterWrapper.getData().get(0).getNCityId());
        roomMatchRecycler.setAdapter(roomMatchesAdapter);
        roomMatchBackend = new RoomMatchBackend(getActivity(), nCityId, nLocationId, this);
    }


    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getTxtTitle().setText("Room Matches");
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRoomListReceived(RoomMatchWrapper roomMatchWrapper) {
        if (getActivity() != null) {
            data.addAll(roomMatchWrapper.getData());
            roomMatchesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(String msg) {

    }

    @OnClick(R.id.fabroommatchFilter)
    public void onClick() {
        mainNavInterface.openRoomMatchesFilter();
    }
}
