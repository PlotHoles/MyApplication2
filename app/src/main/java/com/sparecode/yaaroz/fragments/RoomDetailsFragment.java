package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.model.RoomDetailWrapper;
import com.sparecode.yaaroz.view.CustomTextView;
import com.sparecode.yaaroz.view.CustomTextViewBold;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Sanket on 1/27/2017.
 */

public class RoomDetailsFragment extends BaseFragment implements RoomDetailsBackend.RoomDetailProvider {

    @Bind(R.id.textRent)
    CustomTextViewBold textRent;

    @Bind(R.id.textDescription)
    CustomTextView textDescription;
    @Bind(R.id.imgUserProfile)
    ImageView imgUserProfile;
    @Bind(R.id.TextAvailableDate)
    CustomTextViewBold TextAvailableDate;
    @Bind(R.id.TextRoomAddress)
    CustomTextView TextRoomAddress;
    @Bind(R.id.textRoomMates)
    CustomTextViewBold textRoomMates;
    @Bind(R.id.textLocation)
    CustomTextViewBold textLocation;
    @Bind(R.id.textLease)
    CustomTextViewBold textLease;
    @Bind(R.id.textGender)
    CustomTextViewBold textGender;
    @Bind(R.id.textFurnished)
    CustomTextViewBold textFurnished;
    @Bind(R.id.textBathRoom)
    CustomTextViewBold textBathRoom;
    @Bind(R.id.lookingforgeder)
    CustomTextViewBold lookingforgeder;
    @Bind(R.id.textAgeRange)
    CustomTextViewBold textAgeRange;
    @Bind(R.id.textSmoking)
    CustomTextViewBold textSmoking;
    @Bind(R.id.textPate)
    CustomTextViewBold textPate;
    @Bind(R.id.textAgeRangeMax)
    CustomTextViewBold textAgeRangeMax;

    private View view;
    RoomDetailsBackend roomDetailsBackend;
    String roomID;

    public RoomDetailsFragment(String roomID) {
        this.roomID = roomID;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_roomdetails, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        roomDetailsBackend = new RoomDetailsBackend(getActivity(),roomID, this);

    }

    @Override
    public void setToolbarForFragment() {

    }

    @Override
    public void onRoomDetailReceived(RoomDetailWrapper roomDetailWrapper) {
        if (getActivity() != null) {
            textDescription.setText(roomDetailWrapper.getData().get(0).getSDesc());
            textRent.setText(roomDetailWrapper.getData().get(0).getSRent());
            TextRoomAddress.setText(roomDetailWrapper.getData().get(0).getSAddress());
            TextAvailableDate.setText(roomDetailWrapper.getData().get(0).getDAvailableDate());
            textRoomMates.setText(roomDetailWrapper.getData().get(0).getSRoommates());
            textLocation.setText(roomDetailWrapper.getData().get(0).getNLocationId());
            textLease.setText(roomDetailWrapper.getData().get(0).getSLength()+"");
            textGender.setText(roomDetailWrapper.getData().get(0).getSGender());
            lookingforgeder.setText(roomDetailWrapper.getData().get(0).getSLookingForGender());
            textLocation.setText(roomDetailWrapper.getData().get(0).getSAddress());
            textBathRoom.setText(roomDetailWrapper.getData().get(0).getSBathroom());
            if(roomDetailWrapper.getData().get(0).getSSmoking().equals("0"))
            {
                textSmoking.setText("NO");
            }
            else if(roomDetailWrapper.getData().get(0).getSSmoking().equals("1"))
            {
                textSmoking.setText("Yes");
            }
            if(roomDetailWrapper.getData().get(0).getSPets().equals("0"))
            {
                textPate.setText("NO");
            }
            else if(roomDetailWrapper.getData().get(0).getSPets().equals("1"))
            {
                textPate.setText("Yes");
            }
            if(roomDetailWrapper.getData().get(0).getSFurnish().equals("0"))
            {
                textFurnished.setText("NO");
            }
            else if(roomDetailWrapper.getData().get(0).getSFurnish().equals("1"))
            {
                textFurnished.setText("Yes");
            }


            textAgeRange.setText(roomDetailWrapper.getData().get(0).getSLookingForAge()+" - ");
            textAgeRangeMax.setText(roomDetailWrapper.getData().get(0).getSLookingForAgeMax());
        }
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
