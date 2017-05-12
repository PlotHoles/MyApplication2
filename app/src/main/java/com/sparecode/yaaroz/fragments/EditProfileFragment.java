package com.sparecode.yaaroz.fragments;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.dialog.SweetAlertDialog;
import com.sparecode.yaaroz.model.User;
import com.sparecode.yaaroz.utils.Prefs;
import com.sparecode.yaaroz.view.CustomButton;
import com.sparecode.yaaroz.view.CustomEditText;
import com.sparecode.yaaroz.view.seekbar.RangeSeekBar;

import org.json.JSONArray;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sanket on 2/2/2017.
 */

public class EditProfileFragment extends BaseFragment implements UserBackend.UserProvider {

    @Bind(R.id.ageRangeSeekBar)
    RangeSeekBar ageRangeSeekBar;
    @Bind(R.id.linearCompleteProfile)
    LinearLayout linearCompleteProfile;
    @Bind(R.id.imgEdtProfilePic)
    ImageView imgEdtProfilePic;
    @Bind(R.id.edtEditFname)
    CustomEditText edtEditFname;
    @Bind(R.id.edtEditLname)
    CustomEditText edtEditLname;
    @Bind(R.id.edtEditEmail)
    CustomEditText edtEditEmail;
    @Bind(R.id.radioEditMale)
    RadioButton radioEditMale;
    @Bind(R.id.radioEditFeMale)
    RadioButton radioEditFeMale;
    @Bind(R.id.radioGroupGender)
    RadioGroup radioGroupGender;
    @Bind(R.id.radioGroupSmoking)
    RadioGroup radioGroupSmoking;
    @Bind(R.id.radioGroupPets)
    RadioGroup radioGroupPets;
    @Bind(R.id.edtEditSchool)
    CustomEditText edtEditSchool;
    @Bind(R.id.edtEditProfession)
    CustomEditText edtEditProfession;
    @Bind(R.id.edtEditAboutme)
    CustomEditText edtEditAboutme;
    @Bind(R.id.btnProfileSave)
    CustomButton btnProfileSave;
    String selectionOfSmoking,selectionOfpets;
    private  String age;
    private String smoking = "", pets = "", school = "", profession = "", aboutme = "", gender = "";
    private UserBackend userBackend;
    public static JSONArray jsonArray;

    @Override
    public void setToolbarForFragment() {

    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // ageRangeSeekBar.setRangeValues(18,65);
        getUser();
        userBackend = new UserBackend(getActivity(), this);
        ageRangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Number minValue, Number maxValue) {
                age = "" + minValue;
            }
        });
        radioGroupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (radioEditMale.getId() == checkedId) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }
            }
        });
        radioGroupSmoking.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (R.id.radioSmokingYes == checkedId) {
                    smoking = "yes";
                } else {
                    smoking = "no";
                }
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                selectionOfSmoking = (String) radioButton.getText();
                Toast.makeText(getActivity(),selectionOfSmoking, Toast.LENGTH_SHORT).show();
            }
        });
        radioGroupPets.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (R.id.radioPetYes == checkedId) {
                    pets = "yes";
                } else {
                    pets = "no";
                }
                RadioButton radioButton = (RadioButton) view.findViewById(checkedId);
                selectionOfpets = (String) radioButton.getText();
                Toast.makeText(getActivity(),selectionOfpets, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.linearCompleteProfile)
    void onCompleteProfileClick() {
        mainNavInterface.openCompleteProfileFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private static final String[] PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int PERMISSIONS_CODE = 1337;

    @OnClick(R.id.imgEdtProfilePic)
    void onEditProfilePicClick() {
       /* PiemissionsRequest request;
        request = new PiemissionsRequest(PERMISSIONS_CODE, PERMISSIONS);
        request.setCallback(new PiemissionsCallback() {
            @Override
            public void onGranted() {
                TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(getActivity())
                        .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                            @Override
                            public void onImageSelected(Uri uri) {
                                // here is selected uri
                                DebugLog.e("URI::" + uri);
                            }
                        })
                        .create();

                tedBottomPicker.show(getActivity().getSupportFragmentManager());
            }

            @Override
            public boolean onDenied(HashMap<String, Boolean> rationalizablePermissions) {
                DebugLog.e("Permission Denied" + rationalizablePermissions);
                return false;
            }
        });
        PiemissionsUtils.requestPermission(request);
*/
    }

    private User user;

    private User getUser() {
        user = new Gson().fromJson(Prefs.getString("user", ""), User.class);
        return user;
    }

    @OnClick(R.id.btnProfileSave)
    void onProfileSaveClick() {
        if (!edtEditFname.getText().toString().trim().isEmpty()) {
            if (!edtEditLname.getText().toString().trim().isEmpty()) {
                if (jsonArray != null) {
                    userBackend.callUpdateProfile(edtEditFname.getText().toString(), edtEditLname.getText().toString(), edtEditProfession.getText().toString(), edtEditSchool.getText().toString(), age, selectionOfSmoking, selectionOfpets,edtEditAboutme.getText().toString(),user.getData().getId(), jsonArray.toString());
                } else {
                    userBackend.callUpdateProfile(edtEditFname.getText().toString(), edtEditLname.getText().toString(), edtEditProfession.getText().toString(), edtEditSchool.getText().toString(), age,selectionOfSmoking, selectionOfpets, edtEditAboutme.getText().toString(), user.getData().getId(),"");
                }
            } else {
                new SweetAlertDialog(getActivity()).setTitleText("Please enter Last Name").show();
            }
        } else {
            new SweetAlertDialog(getActivity()).setTitleText("Please enter First Name").show();
        }
    }

    @Override
    public void onUserUpdate(User user) {
        if (getActivity() != null)
        {
            Toast.makeText(getActivity(),user.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(String msg) {

    }


}
