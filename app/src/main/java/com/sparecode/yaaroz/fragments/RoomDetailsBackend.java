package com.sparecode.yaaroz.fragments;

import android.content.Context;

import com.sparecode.yaaroz.interfaces.OnResponse;
import com.sparecode.yaaroz.model.RoomDetailWrapper;
import com.sparecode.yaaroz.webservice.GetRequest;
import com.sparecode.yaaroz.webservice.ReqestParameter;
import com.sparecode.yaaroz.webservice.RequestApi;

/**
 * Created by hitesh on 11/5/17.
 */

public class RoomDetailsBackend {

    private Context context;
    private String roomid;
    RoomDetailProvider roomDetailProvider;

    public RoomDetailsBackend(Context context, String roomid, RoomDetailProvider roomDetailProvider) {
        this.context = context;
        this.roomid = roomid;
        this.roomDetailProvider = roomDetailProvider;
        call();
    }
    private void call() {
        new GetRequest<RoomDetailWrapper>().toGetRequest(context, RequestApi.BASEURL, new ReqestParameter().toGetRoomDetail(roomid), RoomDetailWrapper.class, new OnResponse<RoomDetailWrapper>() {
            @Override
            public void onSuccess(RoomDetailWrapper roomDetailWrapper) {
                if (roomDetailWrapper.getStatus() == 1) {
                    roomDetailProvider.onRoomDetailReceived(roomDetailWrapper);
                } else {
                    roomDetailProvider.onError(roomDetailWrapper.getMessage());
                }
            }
            @Override
            public void onError() {

            }
        });
    }

    interface RoomDetailProvider {
        void onRoomDetailReceived(RoomDetailWrapper roomDetailWrapper);

        void onError(String msg);
    }
}
