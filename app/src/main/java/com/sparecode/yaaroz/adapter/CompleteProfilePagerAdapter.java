package com.sparecode.yaaroz.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.model.QuestionData;
import com.sparecode.yaaroz.utils.DebugLog;
import com.sparecode.yaaroz.view.CustomTextView;

import java.util.List;

/**
 * Created by Sanket on 3/30/2017.
 */

public class CompleteProfilePagerAdapter extends PagerAdapter {
    private List<QuestionData> questionDataList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String selectedAns = "";
    private GetSelectedAnswers getSelectedAnswers;

    public CompleteProfilePagerAdapter(List<QuestionData> questionDataList, Context mContext, GetSelectedAnswers getSelectedAnswers) {
        this.questionDataList = questionDataList;
        this.mContext = mContext;
        this.getSelectedAnswers = getSelectedAnswers;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.row_questionair, container, false);
        CustomTextView customTextView = (CustomTextView) itemView.findViewById(R.id.txtQuestion);
        RadioGroup radioGroupAnswers = (RadioGroup) itemView.findViewById(R.id.radioGroupAnswers);
        RadioButton radioAns1 = (RadioButton) itemView.findViewById(R.id.radioAns1);
        RadioButton radioAns2 = (RadioButton) itemView.findViewById(R.id.radioAns2);
        RadioButton radioAns3 = (RadioButton) itemView.findViewById(R.id.radioAns3);

        customTextView.setText(questionDataList.get(position).getSQue());

        radioAns1.setText(questionDataList.get(position).getAnswers().get(0).getSAns1());
        radioAns2.setText(questionDataList.get(position).getAnswers().get(0).getSAns2());
        radioAns3.setText(questionDataList.get(position).getAnswers().get(0).getSAns3());


        radioGroupAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radioAns1) {
                    selectedAns = "sAns1";
                } else if (checkedId == R.id.radioAns2) {
                    selectedAns = "sAns2";
                } else if (checkedId == R.id.radioAns3) {
                    selectedAns = "sAns3";
                }
                DebugLog.e("SELECTED ANS:" + selectedAns);
                getSelectedAnswers.onAnswerSelection(questionDataList.get(position).getNQueId(), selectedAns);
            }
        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public int getCount() {

        return questionDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public interface GetSelectedAnswers {
        void onAnswerSelection(String qid, String aid);
    }
}
