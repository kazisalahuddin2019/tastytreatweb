package com.prangroup.kazi.tastytreat.servertask;

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

public class GetUserOrderListOperation {
    private  static String UPLOAD_URL = ApiLinks.get_UserOrderlistApi;
    static StringRequest stringRequest;

    static int MY_SOCKET_TIMEOUT_MS=1000000;
    public static  void sendUserID(final String userid, final Context context, final VolleyCallBack volleyCallBack){

        stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject rootObj=new JSONObject(s);
                            int lngth=rootObj.length();
                            volleyCallBack.onSuccess(rootObj);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

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


                params.put("id", userid);
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
