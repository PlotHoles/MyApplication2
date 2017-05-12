package com.sparecode.yaaroz.fragments;

import android.content.Context;

import com.sparecode.yaaroz.interfaces.OnResponse;
import com.sparecode.yaaroz.model.RoomMatchWrapper;
import com.sparecode.yaaroz.webservice.GetRequest;
import com.sparecode.yaaroz.webservice.ReqestParameter;
import com.sparecode.yaaroz.webservice.RequestApi;

/**
 * Created by Sanket on 4/26/2017.
 */

public class RoomMatchBackend {
    //{"method":"room_match","nCityId":"1","nLocationId":"1"}
    private Context mContext;
    private String nCityId;
    private String nLocationId;
    private RoomMatchesProvider roomMatchesProvider;

    public RoomMatchBackend(Context mContext, String nCityId, String nLocationId, RoomMatchesProvider roomMatchesProvider) {
        this.mContext = mContext;
        this.nCityId = nCityId;
        this.nLocationId = nLocationId;
        this.roomMatchesProvider = roomMatchesProvider;
        call();
    }

    private void call() {
        new GetRequest<RoomMatchWrapper>().toGetRequest(mContext, RequestApi.BASEURL, new ReqestParameter().toGetRoomMatches(nCityId, nLocationId), RoomMatchWrapper.class, new OnResponse<RoomMatchWrapper>() {
            @Override
            public void onSuccess(RoomMatchWrapper roomMatchWrapper) {
                if (roomMatchWrapper.getStatus() == 1) {
                    roomMatchesProvider.onRoomListReceived(roomMatchWrapper);
                } else {
                    roomMatchesProvider.onError(roomMatchWrapper.getMessage());
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    interface RoomMatchesProvider {
        void onRoomListReceived(RoomMatchWrapper roomMatchWrapper);

        void onError(String msg);
    }
}
