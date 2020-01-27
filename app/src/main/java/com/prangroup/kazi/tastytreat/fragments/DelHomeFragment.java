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
import com.prangroup.kazi.tastytreat.adapter.DelManWiseOrderListAdapter;
import com.prangroup.kazi.tastytreat.adapter.OrderListAdapter;
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;
import com.prangroup.kazi.tastytreat.model.DelManOrderDataModel;
import com.prangroup.kazi.tastytreat.model.ItemDataModel;
import com.prangroup.kazi.tastytreat.serverDataHandler.OrderListDelManWise;
import com.prangroup.kazi.tastytreat.servertask.GetdelmanWiseOrderListOperation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.prangroup.kazi.tastytreat.activity.MainActivity.mAactivity;

public class DelHomeFragment extends Fragment {
    View rootView;
    public static Activity mActivity;
    public static Context mContext;
    public static ArrayList<DelManOrderDataModel> orderDataDB=new ArrayList<DelManOrderDataModel>();
    public static ListView orderlist;
    public DelHomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_del_home, container, false);
        mActivity = getActivity();
        mContext = getContext();

        orderlist = (ListView)rootView.findViewById(R.id.orderlist);

        GetdelmanWiseOrderListOperation.sendDelID(DeliveryManActivity.userid, "0", mContext, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject getData) {
                Log.e("getData",getData.toString());
                try {
                    String status=getData.getString("status");
                    if (status.equalsIgnoreCase("1")){
                        JSONArray aOrderArray=getData.getJSONArray("data");
                        if (aOrderArray.length()>0){
                            Log.e("PendinggetData",""+aOrderArray.length());
                            orderDataDB.clear();
                            orderDataDB=OrderListDelManWise.getOrderList(aOrderArray);
                            if (orderDataDB.size()>0){
                                setupOrderCart();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        return rootView;
    }

    public static void setupOrderCart() {
        DelManWiseOrderListAdapter orderListAdapter=new DelManWiseOrderListAdapter(mContext,mAactivity,orderDataDB);
        orderlist.setAdapter(orderListAdapter);
    }
}
