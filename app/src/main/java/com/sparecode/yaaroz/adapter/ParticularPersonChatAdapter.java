package com.sparecode.yaaroz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.view.CustomTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by prima on 1/2/17.
 */

public class ParticularPersonChatAdapter extends RecyclerView.Adapter<ParticularPersonChatAdapter.PersonChatViewHolder> {



    public ParticularPersonChatAdapter() {

    }

    @Override
    public PersonChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chatofparticularperson, parent, false);
        PersonChatViewHolder personChatViewHolder = new PersonChatViewHolder(view);
        return personChatViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonChatViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class PersonChatViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imgSendPerticularChat)
        ImageView imgSendPerticularChat;
        @Bind(R.id.txtSendChat)
        CustomTextView txtSendChat;
        @Bind(R.id.txtReceiveChat)
        CustomTextView txtReceiveChat;
        @Bind(R.id.imgReceivePerticularChat)
        ImageView imgReceivePerticularChat;

        public PersonChatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }


}
