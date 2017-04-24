package com.sparecode.yaaroz.fragments;

import android.content.Context;

import com.sparecode.yaaroz.interfaces.OnResponse;
import com.sparecode.yaaroz.model.CityWrapper;
import com.sparecode.yaaroz.model.UserCitySelectionWrapper;
import com.sparecode.yaaroz.webservice.GetRequest;
import com.sparecode.yaaroz.webservice.ReqestParameter;
import com.sparecode.yaaroz.webservice.RequestApi;

/**
 * Created by Sanket on 3/28/2017.
 */

public class SelectCityBackend {

    private Context mContext;
    private CityProvider cityProvider;

    SelectCityBackend(Context mContext, CityProvider cityProvider) {
        this.mContext = mContext;
        this.cityProvider = cityProvider;
        call();
    }

    private void call() {
        new GetRequest<CityWrapper>().toGetRequest(mContext, RequestApi.BASEURL, new ReqestParameter().toGetCity(), CityWrapper.class, new OnResponse<CityWrapper>() {
            @Override
            public void onSuccess(CityWrapper cityWrapper) {
                if (cityWrapper.getStatus() == 1) {
                    cityProvider.onCityReceive(cityWrapper);
                } else {
                    cityProvider.onFailure(cityWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                cityProvider.onFailure("Please try again..!!");
            }
        });
    }

    public void updateUserSelectedCity(String cityId, String userId) {
        new GetRequest<UserCitySelectionWrapper>().toGetRequest(mContext, RequestApi.BASEURL, new ReqestParameter().toUserCitySelection(cityId, userId), UserCitySelectionWrapper.class, new OnResponse<UserCitySelectionWrapper>() {
            @Override
            public void onSuccess(UserCitySelectionWrapper userCitySelectionWrapper) {
                if (userCitySelectionWrapper.getStatus() == 1) {
                    cityProvider.onUserCitySelection(userCitySelectionWrapper);
                } else {
                    cityProvider.onFailure(userCitySelectionWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                cityProvider.onFailure("Please try again !!");
            }
        });
    }

    interface CityProvider {
        void onCityReceive(CityWrapper cityWrapper);

        void onUserCitySelection(UserCitySelectionWrapper userCitySelectionWrapper);

        void onFailure(String msg);
    }
}
