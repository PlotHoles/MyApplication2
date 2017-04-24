package com.sparecode.yaaroz.fragments;

import android.content.Context;

import com.sparecode.yaaroz.interfaces.OnResponse;
import com.sparecode.yaaroz.model.QuestionWrapper;
import com.sparecode.yaaroz.webservice.GetRequest;
import com.sparecode.yaaroz.webservice.ReqestParameter;
import com.sparecode.yaaroz.webservice.RequestApi;

/**
 * Created by Sanket on 3/31/2017.
 */

public class CompleteProfileBackend {
    private Context mContext;
    private GetQuestions getQuestions;

    public CompleteProfileBackend(Context mContext, GetQuestions getQuestions) {
        this.mContext = mContext;
        this.getQuestions = getQuestions;
        callWs();
    }

    private void callWs() {
        new GetRequest<QuestionWrapper>().toGetRequest(mContext, RequestApi.BASEURL, new ReqestParameter().toGetQuestions(), QuestionWrapper.class, new OnResponse<QuestionWrapper>() {
            @Override
            public void onSuccess(QuestionWrapper questionWrapper) {
                if (questionWrapper.getStatus() == 1) {
                    getQuestions.QuestionReceived(questionWrapper);
                } else {
                    getQuestions.OnError(questionWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                getQuestions.OnError("Please try again !!");
            }
        });
    }

    interface GetQuestions {
        void QuestionReceived(QuestionWrapper questionWrapper);

        void OnError(String msg);
    }
}
