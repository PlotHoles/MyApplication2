package com.sparecode.yaaroz.fragments;

import android.content.Context;

import com.sparecode.yaaroz.interfaces.OnResponse;
import com.sparecode.yaaroz.model.CityWrapper;
import com.sparecode.yaaroz.model.LocationWrapper;
import com.sparecode.yaaroz.webservice.GetRequest;
import com.sparecode.yaaroz.webservice.ReqestParameter;
import com.sparecode.yaaroz.webservice.RequestApi;

import java.util.ArrayList;

/**
 * Created by Sanket on 4/5/2017.
 */

public class ListARoomBackend {
    private Context mContext;
    private ListARoomDataProvider listARoomDataProvider;

    public ListARoomBackend(Context mContext, ListARoomDataProvider listARoomDataProvider) {
        this.mContext = mContext;
        this.listARoomDataProvider = listARoomDataProvider;
    }

    public void postRoom(String nCityId, String nLocationId, String address, String desc, String rent, String roommates, String gender, String sLength, String furnish, String bathroom, String smoking, String pets, String lookingforgender, String lookingforage, String availdate) {

    }

    public void postRoomWithImages(String nCityId, String nLocationId, String address, String desc, String rent, String roommates, String gender, String sLength, String furnish, String bathroom, String smoking, String pets, String lookingforgender, String lookingforage, String availdate, ArrayList<String> images) {

    }

    public void getCity() {
        new GetRequest<CityWrapper>().toGetRequest(mContext, RequestApi.BASEURL, new ReqestParameter().toGetCity(), CityWrapper.class, new OnResponse<CityWrapper>() {
            @Override
            public void onSuccess(CityWrapper cityWrapper) {
                if (cityWrapper.getStatus() == 1) {
                    listARoomDataProvider.provideCityList(cityWrapper);
                } else {
                    listARoomDataProvider.onRoomAddFailure(cityWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                listARoomDataProvider.onRoomAddFailure("Please try again..!!");
            }
        });
    }

    public void getLoations(String mCityId) {

        new GetRequest<LocationWrapper>().toGetRequest(mContext, RequestApi.BASEURL, new ReqestParameter().toGetLocation(mCityId), LocationWrapper.class, new OnResponse<LocationWrapper>() {
            @Override
            public void onSuccess(LocationWrapper locationWrapper) {
                if (locationWrapper.getStatus() == 1) {
                    listARoomDataProvider.provideLocationList(locationWrapper);
                } else {
                    listARoomDataProvider.onRoomAddFailure(locationWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                listARoomDataProvider.onRoomAddFailure("Please try again..!!");
            }
        });
    }

    public interface ListARoomDataProvider {
        void provideCityList(CityWrapper cityWrapper);

        void provideLocationList(LocationWrapper locationWrapper);

        //void onRoomAddSuccess();

        void onRoomAddFailure(String msg);
    }
}
