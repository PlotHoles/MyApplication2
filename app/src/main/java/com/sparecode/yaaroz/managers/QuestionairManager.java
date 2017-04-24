package com.sparecode.yaaroz.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by Sanket on 3/30/2017.
 */

public class QuestionairManager extends Observable {
    private Map<String, String> questionair;

    public QuestionairManager() {
        questionair = new HashMap<>();
    }

    public void setQidAid(String qid, String aid) {
        setChanged();
        questionair.put(qid, aid);
        notifyObservers(questionair);
    }

    public Map<String, String> getAllAnsweredQuestions() {
        return questionair;
    }


}
