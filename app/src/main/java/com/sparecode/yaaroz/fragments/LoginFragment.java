package com.sparecode.yaaroz.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.dialog.SweetAlertDialog;
import com.sparecode.yaaroz.interfaces.LocationProvider;
import com.sparecode.yaaroz.location.LocationHelper;
import com.sparecode.yaaroz.model.FbModel;
import com.sparecode.yaaroz.model.User;
import com.sparecode.yaaroz.permission.PiemissionsCallback;
import com.sparecode.yaaroz.permission.PiemissionsRequest;
import com.sparecode.yaaroz.permission.PiemissionsUtils;
import com.sparecode.yaaroz.utils.DebugLog;
import com.sparecode.yaaroz.view.CustomTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by master on 30-12-2016.
 */

public class LoginFragment extends BaseFragment implements LocationProvider, UserBackend.UserProvider {
    @Bind(R.id.imgSplashLogo)
    ImageView imgSplashLogo;
    @Bind(R.id.btnFbLogin)
    CustomTextView btnFbLogin;
    private View view;
    private CallbackManager callbackManager;
    private UserBackend userBackend;
    private Location location;
    private static final int PERMISSIONS_CODE = 1337;
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE

    };
    PiemissionsRequest request;
    LocationHelper locationHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        request = new PiemissionsRequest(PERMISSIONS_CODE, PERMISSIONS);


        request.setCallback(new PiemissionsCallback() {
            @Override
            public void onGranted() {

            }

            @Override
            public boolean onDenied(HashMap<String, Boolean> rationalizablePermissions) {
                DebugLog.e("Permission Denied");
                return false;
            }
        });
        PiemissionsUtils.requestPermission(request);
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        DebugLog.e("" + loginResult.getAccessToken());
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        DebugLog.e("JSON OBJ::" + object);
                                        try {
                                            FbModel fbModel = new Gson().fromJson(object.toString(), FbModel.class);
                                            String profilePicUrl = URLEncoder.encode(object.getJSONObject("picture").getJSONObject("data").getString("url"), "utf-8");
                                            Log.e("PIC:", "" + profilePicUrl);
                                            if (location != null) {
                                                userBackend.callSignup(fbModel.getEmail(), URLEncoder.encode(fbModel.getPicture().getData().getUrl(),"UTF-8"), String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), fbModel.getId(), "123456");
                                            } else {
                                                new SweetAlertDialog(getActivity()).setCustomImage(R.drawable.yaaroz_logo).setTitleText("Please check your location service !").show();
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                        // Application code
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,link,email,gender,birthday,picture.type(large)");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        DebugLog.e("" + exception.toString());
                    }
                });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        locationHelper = new LocationHelper(getActivity(), this);
        userBackend = new UserBackend(getActivity(), this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btnFbLogin)
    void onFbLoginClick() {
        if (getActivity() != null) {

            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email", "user_birthday"));
            //printKeyHash(getActivity());
        }
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    private void printKeyHash(Context mContext) {
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(
                    "com.sparecode.yaaroz",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                DebugLog.e("KeyHash:" + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            DebugLog.e("" + e);
        } catch (NoSuchAlgorithmException e) {
            DebugLog.e("" + e);
        }
    }

    @Override
    public void setToolbarForFragment() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onNewLcoationReceived(Location location) {
        this.location = location;
    }

    @Override
    public void onUserUpdate(User user) {
        Log.e("USER:", "" + user.getData().getFbId());
        mainNavInterface.openSelectCity();
    }

    @Override
    public void onFailure(String msg) {
        Log.e("USER:", "" + msg);
        if (getActivity() != null) {
            new SweetAlertDialog(getActivity()).setTitleText("" + msg).show();
        }
    }
}
