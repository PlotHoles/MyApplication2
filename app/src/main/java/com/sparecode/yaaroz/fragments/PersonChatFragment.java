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
import com.sparecode.yaaroz.adapter.ParticularPersonChatAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prima on 1/2/17.
 */

public class PersonChatFragment extends BaseFragment {



    @Bind(R.id.recyclerParticularChatView)
    RecyclerView recyclerParticularChatView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatofparticularperson, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbar().setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





        ParticularPersonChatAdapter particularPersonChatAdapter=new ParticularPersonChatAdapter();
        recyclerParticularChatView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerParticularChatView.setAdapter(particularPersonChatAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
