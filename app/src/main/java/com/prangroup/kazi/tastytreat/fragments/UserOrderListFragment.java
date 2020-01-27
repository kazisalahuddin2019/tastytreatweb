package com.prangroup.kazi.tastytreat.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.activity.DeliveryManActivity;
import com.prangroup.kazi.tastytreat.activity.MainActivity;
import com.prangroup.kazi.tastytreat.adapter.DelManWiseOrderProgListAdapter;
import com.prangroup.kazi.tastytreat.adapter.UserOrderListAdapter;
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;
import com.prangroup.kazi.tastytreat.model.DelManOrderDataModel;
import com.prangroup.kazi.tastytreat.model.UserAddressDataModel;
import com.prangroup.kazi.tastytreat.model.UserOrderListDataModel;
import com.prangroup.kazi.tastytreat.serverDataHandler.OrderListDelManWise;
import com.prangroup.kazi.tastytreat.serverDataHandler.UserOrderList;
import com.prangroup.kazi.tastytreat.servertask.GetUserOrderListOperation;
import com.prangroup.kazi.tastytreat.servertask.GetdelmanWiseOrderListOperation;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.prangroup.kazi.tastytreat.activity.MainActivity.mAactivity;

public class UserOrderListFragment extends Fragment {
    View rootView;
    public static Activity mActivity;
    public static Context mContext;
    public static ArrayList<UserOrderListDataModel> orderListDataDB=new ArrayList<UserOrderListDataModel>();
    public static ListView orderlistt;
    public static String status;
    public UserOrderListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_orderlist, container, false);
        mActivity = getActivity();
        mContext = getContext();
        status="0";

        orderlistt = (ListView)rootView.findViewById(R.id.orderlistt);

        GetUserOrderListOperation.sendUserID(MainActivity.userid, mContext, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject getDataa) {
                Log.e("getData",getDataa.toString());
                try {
                    String status=getDataa.getString("status");
                    if (status.equalsIgnoreCase("1")){
                        JSONArray aOrderArrayy=getDataa.getJSONArray("data");
                        if (aOrderArrayy.length()>0){
                            Log.e("getData",""+aOrderArrayy.length());
                            orderListDataDB.clear();
                            orderListDataDB= UserOrderList.getOrderList(aOrderArrayy);
                            if (orderListDataDB.size()>0){
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

    public static ArrayList<UserOrderListDataModel> filtered(String status){
        ArrayList<UserOrderListDataModel> userAddressDataModelArrayList = new ArrayList<>();
        try {
            for (UserOrderListDataModel userOrderListDataModel : orderListDataDB) {
                if(userOrderListDataModel.getOrder_status().equalsIgnoreCase(status)){
                    userAddressDataModelArrayList.add(userOrderListDataModel);
                }
            }

        }catch (Exception e){

        }
        return userAddressDataModelArrayList;
    }

    public static void setupOrderCart() {
        UserOrderListAdapter orderListAdapter = null;
        if (orderListDataDB.size()>0){
            orderlistt.setVisibility(View.VISIBLE);

               try {
                   orderListAdapter=new UserOrderListAdapter(mContext,mAactivity,filtered(status));
                   orderlistt.setAdapter(orderListAdapter);
               }catch (Exception e){
                   HelpingLib.showmessage(mContext,"No data found");
                   //e.printStackTrace();
               }

        }else{
            HelpingLib.showmessage(mContext,"No data found");
            orderlistt.setVisibility(View.GONE);

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // TODO Add your menu entries here
        inflater.inflate(R.menu.actionmenu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pending:
                status="0";
                setupOrderCart();
                break;

            case R.id.inprogress:
                status="1";
                setupOrderCart();
                break;

            case R.id.complete:
                status="2";
                setupOrderCart();
                break;
        }
        return true;

    }
}
