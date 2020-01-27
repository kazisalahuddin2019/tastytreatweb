package com.prangroup.kazi.tastytreat.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CS-MIS-HW-Altaf on 11/13/2017.
 */

public class HelpingLib {
    public static String otp = "no";

    public static boolean isInternetConnected(Context context) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else
            connected = false;
        return connected;
    }

    public static void showmessage(Context context, String msgBody) {
        Toast.makeText(context, msgBody, Toast.LENGTH_SHORT).show();
    }

    public static String getCurrDate() {

        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String Date = format.format(curDate);
        return Date;
    }

    public static String getCurrTime() {

        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");
        String time = format.format(curDate);

        return time;
    }

    public static String deviceHW(Activity activity) {
        String deviceHW = "";
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        deviceHW = height + "-" + width;
        return deviceHW;
    }

    public void checkGpsPermission(Context context, Activity activity) {
        if (!checkPermission(context)) {
            requestPermission(context, activity);
        } else {
            Log.e("checkPermission", "Permission already granted");

        }

    }

    private static void requestPermission(Context context, Activity activity) {
        final int PERMISSION_REQUEST_CODE = 1;


        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {

            Toast.makeText(context, "GPS permission allows us to access location data.", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
    }
    //End gps permission check


    public static void openGpsSettings(Context context, Activity activity) {
        if (!checkPermission(context)) {
            requestPermissions(context, activity);
            try {
                Log.e("tttttt","time=="+new Date(System.currentTimeMillis()));
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("checkPermission", "Permission already granted");

        }

    }

    private static boolean checkPermission(final Context context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }

    }

    public static void requestPermissions(final Context context, Activity activity) {
        final int PERMISSION_REQUEST_CODE = 1;
        Object res = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {

            Toast.makeText(context, "GPS permission allows us to access location data.", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
    }

    public static String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.WEBP, 30, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


}
