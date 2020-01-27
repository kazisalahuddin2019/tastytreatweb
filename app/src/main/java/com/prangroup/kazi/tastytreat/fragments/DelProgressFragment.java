package com.prangroup.kazi.tastytreat.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.activity.DeliveryManActivity;
import com.prangroup.kazi.tastytreat.activity.MainActivity;
import com.prangroup.kazi.tastytreat.adapter.DelManWiseOrderListAdapter;
import com.prangroup.kazi.tastytreat.adapter.DelManWiseOrderProgListAdapter;
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;
import com.prangroup.kazi.tastytreat.model.DelManOrderDataModel;
import com.prangroup.kazi.tastytreat.serverDataHandler.OrderListDelManWise;
import com.prangroup.kazi.tastytreat.servertask.GetdelmanWiseOrderListOperation;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.prangroup.kazi.tastytreat.activity.MainActivity.mAactivity;

public class DelProgressFragment extends Fragment {
    View rootView;
    public static Activity mActivity;
    public static Context mContext;
    public static ArrayList<DelManOrderDataModel> orderProhDataDB=new ArrayList<DelManOrderDataModel>();
    public static ListView orderlistt;
    public DelProgressFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_del_progress, container, false);
        mActivity = getActivity();
        mContext = getContext();

        orderlistt = (ListView)rootView.findViewById(R.id.orderlistt);
        String userid=DeliveryManActivity.userid;
        if (userid==null){
            userid= MainActivity.userid;
        }
        GetdelmanWiseOrderListOperation.sendDelID(userid, "1", mContext, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject getDataa) {
                Log.e("getData",getDataa.toString());
                try {
                    String status=getDataa.getString("status");
                    if (status.equalsIgnoreCase("1")){
                        JSONArray aOrderArrayy=getDataa.getJSONArray("data");
                        if (aOrderArrayy.length()>0){
                            Log.e("getData",""+aOrderArrayy.length());
                            orderProhDataDB.clear();
                            orderProhDataDB=OrderListDelManWise.getOrderList(aOrderArrayy);
                            if (orderProhDataDB.size()>0){
                                setupOrderCart();
                            }
                        }
                    }else{
                        HelpingLib.showmessage(mContext,"No data found");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        return rootView;
    }

    public static void setupOrderCart() {
        DelManWiseOrderProgListAdapter orderListAdapter = null;
        if (orderProhDataDB.size()>0){
            orderlistt.setVisibility(View.VISIBLE);
             orderListAdapter=new DelManWiseOrderProgListAdapter(mContext,mAactivity,orderProhDataDB);
            orderlistt.setAdapter(orderListAdapter);
        }else{
            HelpingLib.showmessage(mContext,"No data found");
            orderlistt.setVisibility(View.GONE);
            //DelManWiseOrderProgListAdapter orderListAdapter=new DelManWiseOrderProgListAdapter(mContext,mAactivity,orderProhDataDB);
//            orderListAdapter.notifyDataSetChanged();
//            orderlistt.setAdapter(orderListAdapter);
        }

    }
}
