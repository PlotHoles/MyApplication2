package com.sparecode.yaaroz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sanket on 3/30/2017.
 */

public class QuestionData {

    @SerializedName("nQueId")
    @Expose
    private String nQueId;
    @SerializedName("sQue")
    @Expose
    private String sQue;
    @SerializedName("Answers")
    @Expose
    private List<Answer> answers = null;

    public String getNQueId() {
        return nQueId;
    }

    public void setNQueId(String nQueId) {
        this.nQueId = nQueId;
    }

    public String getSQue() {
        return sQue;
    }

    public void setSQue(String sQue) {
        this.sQue = sQue;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
