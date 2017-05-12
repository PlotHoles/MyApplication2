package com.sparecode.yaaroz.fragments;

import android.content.Context;

import com.sparecode.yaaroz.interfaces.OnResponse;
import com.sparecode.yaaroz.model.RoomMatchWrapper;
import com.sparecode.yaaroz.webservice.GetRequest;
import com.sparecode.yaaroz.webservice.ReqestParameter;
import com.sparecode.yaaroz.webservice.RequestApi;

/**
 * Created by Sanket on 5/11/2017.
 */

public class RoomMatchFilterBackend {

    private Context mContext;
    private String sLength,sRent,startdate,enddate,sFurnish,sBathroom,sSmoking,sPets,sLookingForAge,sLookingForAgeMax,MinLength,MaxLength,MinBudget,MaxBudget;
    private RoomMatchFilterProvider roomMatchFilterProvider;

    public RoomMatchFilterBackend(Context mContext, String sLength, String sRent, String startdate, String enddate, String sFurnish, String sBathroom, String sSmoking, String sPets, String sLookingForAge, String sLookingForAgeMax, String minLength, String maxLength, String minBudget, String maxBudget, RoomMatchFilterProvider roomMatchFilterProvider) {
        this.mContext = mContext;
        this.sLength = sLength;
        this.sRent = sRent;
        this.startdate = startdate;
        this.enddate = enddate;
        this.sFurnish = sFurnish;
        this.sBathroom = sBathroom;
        this.sSmoking = sSmoking;
        this.sPets = sPets;
        this.sLookingForAge = sLookingForAge;
        this.sLookingForAgeMax = sLookingForAgeMax;
        MinLength = minLength;
        MaxLength = maxLength;
        MinBudget = minBudget;
        MaxBudget = maxBudget;
        this.roomMatchFilterProvider = roomMatchFilterProvider;
        call();
    }

    private void call() {
        new GetRequest<RoomMatchWrapper>().toGetRequest(mContext, RequestApi.BASEURL, new ReqestParameter().toFilterRoom(sLength, sRent,
                startdate,enddate,sFurnish,sBathroom,sSmoking,sPets,sLookingForAge,sLookingForAgeMax,MinLength,MaxLength,MinBudget,MaxBudget)
                , RoomMatchWrapper.class, new OnResponse<RoomMatchWrapper>() {
            @Override
            public void onSuccess(RoomMatchWrapper roomMatchWrapper) {
                if (roomMatchWrapper.getStatus() == 1) {
                    roomMatchFilterProvider.onRoomListReceived(roomMatchWrapper);
                } else {
                    roomMatchFilterProvider.onError(roomMatchWrapper.getMessage());
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    interface RoomMatchFilterProvider{
        void onRoomListReceived(RoomMatchWrapper roomMatchWrapper);

        void onError(String msg);
    }
}
