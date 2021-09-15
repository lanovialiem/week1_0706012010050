package com.tecdehcode.week1_0706012010050;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class Loading {

    private AlertDialog alert;
    private Activity activity;

    Loading(Activity myActivity) {
        activity = myActivity;
    }

    void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading, null));
        builder.setCancelable(true);

        alert = builder.create();
        alert.show();
    }

    void dismissDialog(){
        alert.dismiss();
    }
}
