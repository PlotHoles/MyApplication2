package com.sparecode.yaaroz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

@SerializedName("qid")
@Expose
private String qid;
@SerializedName("aid")
@Expose
private String aid;

public String getQid() {
return qid;
}

public void setQid(String qid) {
this.qid = qid;
}

public String getAid() {
return aid;
}

public void setAid(String aid) {
this.aid = aid;
}

}