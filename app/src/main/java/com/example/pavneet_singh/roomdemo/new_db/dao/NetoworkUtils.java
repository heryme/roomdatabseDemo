package com.example.pavneet_singh.roomdemo.new_db.dao;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetoworkUtils {
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
