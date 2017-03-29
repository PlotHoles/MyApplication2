package com.sparecode.yaaroz.interfaces;

/**
 * Created by user_1 on 10/14/2016.
 */

public interface OnResponse<T> {
    void onSuccess(T t);
    void onError();
}
