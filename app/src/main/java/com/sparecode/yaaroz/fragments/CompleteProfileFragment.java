package com.sparecode.yaaroz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.BaseActivity;
import com.sparecode.yaaroz.adapter.CompleteProfilePagerAdapter;
import com.sparecode.yaaroz.dialog.SweetAlertDialog;
import com.sparecode.yaaroz.managers.QuestionairManager;
import com.sparecode.yaaroz.model.QuestionData;
import com.sparecode.yaaroz.model.QuestionWrapper;
import com.sparecode.yaaroz.utils.DebugLog;
import com.sparecode.yaaroz.view.CustomButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sanket on 3/30/2017.
 */

public class CompleteProfileFragment extends BaseFragment implements Observer, CompleteProfilePagerAdapter.GetSelectedAnswers, CompleteProfileBackend.GetQuestions {

    @Bind(R.id.viewPagerQuestionair)
    ViewPager viewPagerQuestionair;
    @Bind(R.id.btnSkipQuestion)
    CustomButton btnSkipQuestion;
    private View view;
    private CompleteProfilePagerAdapter completeProfilePagerAdapter;
    private List<QuestionData> questionDataList;
    private QuestionairManager questionairManager;
    private CompleteProfileBackend completeProfileBackend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_completeprofile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        questionairManager = new QuestionairManager();
        setupPager();
        completeProfileBackend = new CompleteProfileBackend(getActivity(), this);
    }

    private void setupPager() {
        questionDataList = new ArrayList<>();
        completeProfilePagerAdapter = new CompleteProfilePagerAdapter(questionDataList, getActivity(), this);
        viewPagerQuestionair.setAdapter(completeProfilePagerAdapter);
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getTxtTitle().setText("Complete Your Profile");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        DebugLog.e("::" + ((QuestionairManager) o).getAllAnsweredQuestions());


    }

    @Override
    public void onAnswerSelection(String qid, String aid) {
        questionairManager.setQidAid(qid, aid);
        DebugLog.e("ALL ANSWERS ARE:" + questionairManager.getAllAnsweredQuestions());
        JSONObject jsonObject = new JSONObject(questionairManager.getAllAnsweredQuestions());
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        EditProfileFragment.jsonArray = jsonArray;
        DebugLog.e("::CONVERTED JSON OBJECTS ARE:" + jsonObject);
        DebugLog.e("::CONVERTED JSON ARRAY IS:" + jsonArray);
    }

    @Override
    public void QuestionReceived(QuestionWrapper questionWrapper) {
        if (getActivity() != null) {
            questionDataList.addAll(questionWrapper.getData());
            completeProfilePagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void OnError(String msg) {
        if (getActivity() != null) {
            new SweetAlertDialog(getActivity()).setTitleText("" + msg).show();
        }
    }
}

