package com.sparecode.yaaroz.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.sparecode.yaaroz.R;
import com.sparecode.yaaroz.activity.GalleryActivity;
import com.sparecode.yaaroz.activity.MultiCameraActivity;
import com.sparecode.yaaroz.utils.Constants;
import com.sparecode.yaaroz.utils.Image;
import com.sparecode.yaaroz.utils.Params;
import com.sparecode.yaaroz.utils.Util;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


public class ListARoomFragments extends Fragment {

    @Bind(R.id.button2)
    Button button2;
    private String userChoosenTask;

    public ListARoomFragments() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_aroom_fragments, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.button2)
    public void onClick() {
        selectImage();
    }
    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {


                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (Util.hasCameraHardware(getActivity()))
                        initiateMultiCapture();
                    else
                        //Util.showLongSnack(view, "Sorry. Your device does not have a camera.");
                    Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    initiateMultiPicker();
                    Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case Constants.TYPE_MULTI_CAPTURE:
                handleResponseIntent(data);
                break;
            case Constants.TYPE_MULTI_PICKER:
                handleResponseIntent(data);
                break;
        }
    }
    private int getColumnCount() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        float thumbnailDpWidth = getResources().getDimension(R.dimen.thumbnail_width) / displayMetrics.density;
        return (int) (dpWidth / thumbnailDpWidth);
    }

    private void handleResponseIntent(Intent intent) {
        ArrayList<Image> imagesList = intent.getParcelableArrayListExtra(Constants.KEY_BUNDLE_LIST);

        //GalleryImagesAdapter imageAdapter = new GalleryImagesAdapter(this, imagesList, getColumnCount(), new Params());
        //recyclerView.setAdapter(imageAdapter);
    }
    private void initiateMultiCapture() {
        Intent intent = new Intent(getActivity(), MultiCameraActivity.class);
        Params params = new Params();
        params.setCaptureLimit(3);
        intent.putExtra(Constants.KEY_PARAMS, params);
        startActivityForResult(intent, Constants.TYPE_MULTI_CAPTURE);
    }

    private void initiateMultiPicker() {
        Intent intent = new Intent(getActivity(), GalleryActivity.class);
        Params params = new Params();
        params.setCaptureLimit(3);
        params.setPickerLimit(3);
        intent.putExtra(Constants.KEY_PARAMS, params);
        startActivityForResult(intent, Constants.TYPE_MULTI_PICKER);
    }
}
