package com.prangroup.kazi.tastytreat.servertask;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class GetUserAddressOperation {
    private  static String UPLOAD_URL = ApiLinks.get_userAddressApi;
    static StringRequest stringRequest;

    static int MY_SOCKET_TIMEOUT_MS=1000000;
    public static  void sendUserID(final String userid, final Context context, final VolleyCallBack volleyCallBack){
        UPLOAD_URL=UPLOAD_URL+userid;
        //Showing the progress dialog
        //final ProgressDialog loading = ProgressDialog.show(context,"Processing...","Please wait...",false,false);
        stringRequest = new StringRequest(Request.Method.GET, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        UPLOAD_URL="";
                        UPLOAD_URL=ApiLinks.get_userAddressApi;
                        //loading.dismiss();
                        try {
                            JSONObject rootObj=new JSONObject(s);
                            volleyCallBack.onSuccess(rootObj);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
 //                       loading.dismiss();
//                        NetworkResponse response = volleyError.networkResponse;
//                        int statuscode=response.statusCode;
                        UPLOAD_URL="";
                        UPLOAD_URL=ApiLinks.get_userAddressApi;
                        Log.e("data","statuscode="+volleyError);
//
//                        Toast.makeText(MainActivity.activity, message, Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String

                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters


                //params.put("userid", userid);
                //Log.e("data","location="+Utilities.latitude+"/"+Utilities.longitude);
                //returning parameters
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

}
