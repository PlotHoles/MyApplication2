package com.sparecode.yaaroz.webservice;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.util.Pair;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.sparecode.yaaroz.application.Yaaroz;
import com.sparecode.yaaroz.interfaces.OnResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by user_1 on 10/18/2016.
 */

public class PostAsync<T> extends AsyncTask<String, Void, String> {

    private Context context;
    private String url;
    private List<Pair<String, String>> pairList;
    private String keyImage;
    private File file;
    private Class<T> clazz;
    private OnResponse<T> callback;

    private static final String IMGUR_CLIENT_ID = "111";
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");

    public PostAsync(Context context, String url, List<Pair<String, String>> pairList, String keyImage, File file, Class<T> clazz, OnResponse<T> t) {
        this.context = context;
        this.url = url;
        this.pairList = pairList;
        this.keyImage = keyImage;
        this.file = file;
        this.clazz = clazz;
        this.callback = t;
    }

    public PostAsync(Context context, String url, List<Pair<String, String>> pairList, Class<T> clazz, OnResponse<T> t) {
        this.context = context;
        this.url = url;
        this.pairList = pairList;
        this.clazz = clazz;
        this.callback = t;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... params) {

        MultipartBody.Builder multipartBuilder =  new MultipartBody.Builder();
        multipartBuilder.setType(MultipartBody.FORM);

        for (Pair pair : pairList)
            multipartBuilder.addFormDataPart(pair.first.toString(), pair.second.toString());

        if (file != null)
            multipartBuilder.addFormDataPart(keyImage, file.getAbsolutePath(), RequestBody.create(MEDIA_TYPE_PNG, file));

        RequestBody requestBody  = multipartBuilder.build();
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = Yaaroz.getRequestBuilder().getOkHttpClient().newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseString = response.body().string();
                Log.e(this.getClass().getName(), " ::: DATA IS ::: " + responseString);
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
            GsonBuilder gson = new GsonBuilder();
            T typeClass = null;
            try {
                typeClass = gson.create().fromJson(response, clazz);
                callback.onSuccess(typeClass);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                callback.onError();
            }
        }
    }
}

