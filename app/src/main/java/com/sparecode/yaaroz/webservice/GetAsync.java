package com.sparecode.yaaroz.webservice;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.sparecode.yaaroz.application.Yaaroz;
import com.sparecode.yaaroz.interfaces.OnResponse;
import com.sparecode.yaaroz.model.CollectionAdapter;
import com.sparecode.yaaroz.utils.DebugLog;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;

import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by user_1 on 10/17/2016.
 */

public class GetAsync<T> extends AsyncTask<String, Void, String> {

    private Context context;
    private String url;
    private JSONObject jsonObject;
    private Class<T> clazz;
    private OnResponse<T> callback;


    public GetAsync(Context context, String url, JSONObject jsonObject, Class<T> clazz, OnResponse<T> callback) {
        this.context = context;
        this.url = url;
        this.jsonObject = jsonObject;
        this.clazz = clazz;
        this.callback = callback;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... params) {

        Request request = new Request.Builder()
                .url(url + jsonObject.toString())
                .build();
        Log.e(this.getClass().getName(), ":::: GET REQUEST URL IS :::: "
                + url + jsonObject.toString());

        try (Response response = Yaaroz.getRequestBuilder().getOkHttpClient().newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseString = response.body().string();
                //Log.e(this.getClass().getName(), " ::: DATA IS ::: " + responseString);
                return responseString;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        translate(response);
    }

    public void translate(String response) {
        if (response != null) {
            response = response.replace("[]", "\"\"");
            DebugLog.e("UPDATED RESPONSE::" + response);
            Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Collection.class, new CollectionAdapter()).create();
            T typeClass = null;
            try {
                typeClass = gson.fromJson(response, clazz);
                callback.onSuccess(typeClass);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                DebugLog.e("PARSING ERROR" + e);

                callback.onError();
            }
        }
    }
}
