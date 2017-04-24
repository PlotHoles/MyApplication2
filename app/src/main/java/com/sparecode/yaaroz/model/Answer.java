package com.sparecode.yaaroz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer {

@SerializedName("sAns1")
@Expose
private String sAns1;
@SerializedName("sAns2")
@Expose
private String sAns2;
@SerializedName("sAns3")
@Expose
private String sAns3;

public String getSAns1() {
return sAns1;
}

public void setSAns1(String sAns1) {
this.sAns1 = sAns1;
}

public String getSAns2() {
return sAns2;
}

public void setSAns2(String sAns2) {
this.sAns2 = sAns2;
}

public String getSAns3() {
return sAns3;
}

public void setSAns3(String sAns3) {
this.sAns3 = sAns3;
}

}