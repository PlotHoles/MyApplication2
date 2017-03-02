package com.sparecode.yaaroz.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.adapter.RecentChatAdapter;
import com.sparecode.yaaroz.model.RecentChatData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by prima on 25/1/17.
 */
@SuppressLint("ValidFragment")
public class RecentChatFragment extends BaseFragment {

    int chatList;
    RecentChatData recentChatData;
    ArrayList<RecentChatData> recentChatDataList;
    @Bind(R.id.recyclerChatView)
    RecyclerView recyclerChatView;


    public RecentChatFragment(RecentChatData recentChatData) {
        this.recentChatData = recentChatData;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recentchatpage, container, false);


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

        recentChatDataList = new ArrayList<>();
        recentChatData = new RecentChatData();


        recentChatData.setName("prima shah");
        recentChatData.setDay("2 day ago");
        recentChatData.setDate("09/08/2016");

        for(chatList=0;chatList<=10;chatList++)
        {
            recentChatDataList.add(recentChatData);
        }


        RecentChatAdapter recentChatAdapter = new RecentChatAdapter(recentChatDataList);
        recyclerChatView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerChatView.setAdapter(recentChatAdapter);


    }
}
