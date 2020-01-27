package com.prangroup.kazi.tastytreat.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.adapter.AutoCompleteAdapter;
import com.prangroup.kazi.tastytreat.fragments.AddressFragment;
import com.prangroup.kazi.tastytreat.fragments.CartFragment;
import com.prangroup.kazi.tastytreat.fragments.ButtonFragment;
import com.prangroup.kazi.tastytreat.fragments.DelCompleteFragment;
import com.prangroup.kazi.tastytreat.fragments.DelProgressFragment;
import com.prangroup.kazi.tastytreat.fragments.HomeFragment;
import com.prangroup.kazi.tastytreat.fragments.UserOrderListFragment;
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;
import com.prangroup.kazi.tastytreat.model.ItemDataModel;
import com.prangroup.kazi.tastytreat.model.OrderCollectionInfoModel;
import com.prangroup.kazi.tastytreat.model.ShowroomDataModel;
import com.prangroup.kazi.tastytreat.servertask.GetShowroomOperation;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;
import com.prangroup.kazi.tastytreat.utils.MyTextWatcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static AlertDialog.Builder dialogBuilderr;
    public static AlertDialog searchAlertDialogg;
    public static Activity mAactivity;
    public static Context mContext;
    AutoCompleteTextView pickdepolistauto;
     final String location = null;
    public static  String userid;
    int animationSourceBottomToBottom;
    public static AutoCompleteAdapter lAdapter;
    public static android.support.v4.app.FragmentTransaction ft;
    public static ArrayList<String> locArr=new ArrayList<String>();
    public static ArrayList<ShowroomDataModel> showroomDB=new ArrayList<ShowroomDataModel>();
    public static ArrayList<ItemDataModel> ItemDataDB=new ArrayList<ItemDataModel>();
    public static ArrayList<OrderCollectionInfoModel> orderCollectionDB=new ArrayList<OrderCollectionInfoModel>();
    Button btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAactivity=(Activity)this;
        mContext=(Context)this;

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.clientnavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SplashActivity.sharedpreferences = getSharedPreferences(SplashActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        final String location=SplashActivity.sharedpreferences.getString(SplashActivity.location,"location not found");
        if (location.equalsIgnoreCase("location not found")){
            GetShowroomOperation.getShowroomData(mAactivity, mContext, new VolleyCallBack() {
                        @Override
                        public void onSuccess(JSONObject getData) {
                            Log.e("getData",getData.toString());
                            try {
                                String msg=getData.getString("Message");
                                if (msg.equalsIgnoreCase("Successful")){
                                    JSONArray locationArray=getData.getJSONArray("data");
                                    JSONArray itemdataArray=getData.getJSONArray("itemdata");
                                    Log.e("locationArray",locationArray.toString());
                                    locArr.clear();
                                    showroomDB.clear();
                                    for (int i=0;i<locationArray.length();i++){
                                        JSONObject aLocObj=locationArray.getJSONObject(i);
                                        String id=aLocObj.getString("id");
                                        String code=aLocObj.getString("code");
                                        String location=aLocObj.getString("loc");
                                        ShowroomDataModel showroomDataModel=new ShowroomDataModel(id,code,location);
                                        showroomDB.add(showroomDataModel);
                                        if (!isLocExist(location)){
                                            locArr.add(location);
                                        }
                                    }

                                    ItemDataDB.clear();
                                    for (int m=0;m<itemdataArray.length();m++){
                                        JSONObject aItemObj=itemdataArray.getJSONObject(m);
                                        String itemcode=aItemObj.getString("itemcode");
                                        String itemname=aItemObj.getString("itemname");
                                        String image=aItemObj.getString("image");
                                        String haveimage=aItemObj.getString("haveimage");
                                        String haveoffer=aItemObj.getString("haveoffer");
                                        String offerstatus=aItemObj.getString("isactive");
                                        String offertype=aItemObj.getString("offertype");
                                        String amount=aItemObj.getString("amount");
                                        String qnty=aItemObj.getString("qnty");
                                        String freeqnty=aItemObj.getString("freeqnty");
                                        String freeItemCodes=aItemObj.getString("freeItemCodes");
                                        String freeItemName=aItemObj.getString("freeItemName");
                                        ItemDataModel itemDataModel=new ItemDataModel(itemcode,itemname,image,haveimage,haveoffer,offerstatus,offertype,amount,qnty,freeqnty,freeItemCodes,freeItemName,"0","0","1");
                                        ItemDataDB.add(itemDataModel);//1=not show
                                    }
                                    if (locArr.size()>0){
                                        getAddress();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                });
            //getAddress();

        }else{
            changeFragment(new HomeFragment());
        }
    }

    private boolean isLocExist(String location) {
        boolean res=false;
        for (int i=0;i<locArr.size();i++){
            String dbLoc=locArr.get(i);
            if (dbLoc.equalsIgnoreCase(location)){
                i=i+locArr.size();
                res=true;
            }
        }
        return res;
    }


    private void getAddress() {
        dialogBuilderr = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = mAactivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.pickup_location_layout, null);
        dialogBuilderr.setView(dialogView);
        searchAlertDialogg = dialogBuilderr.create();
        searchAlertDialogg.getWindow().getAttributes().windowAnimations = animationSourceBottomToBottom;
        searchAlertDialogg.show();

        pickdepolistauto = (AutoCompleteTextView) dialogView.findViewById(R.id.pickdepolistauto);
        btnsubmit = (Button) dialogView.findViewById(R.id.btnsubmit);
        lAdapter = new AutoCompleteAdapter(mContext, locArr);

        pickdepolistauto.setThreshold(0);
        pickdepolistauto.setAdapter(lAdapter);
        pickdepolistauto.addTextChangedListener(new MyTextWatcher(lAdapter));

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locData=pickdepolistauto.getText().toString();
                if (isExist(locData)){

                    writeToShared(locData);
                    searchAlertDialogg.dismiss();
                    changeFragment(new HomeFragment());

                }else{
                    HelpingLib.showmessage(mContext,"Location not available");
                }
            }
        });

    }

    public static boolean isExist(String locData) {
        boolean res = false;
        for (int i=0;i<locArr.size();i++){
            String dbData=locArr.get(i);
            if (dbData.equalsIgnoreCase(locData)){
                i=locArr.size()+1;
                res=true;
            }
        }
        return res;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFragment(new HomeFragment());
                    return true;
                case R.id.navigation_cart:
                    if (HomeFragment.orderDB.size()>0){
                        changeFragment(new CartFragment());
                        return true;
                    }else{
                        HelpingLib.showmessage(mContext,"No order found");
                    }

                case R.id.navigation_address:
                    changeFragment(new AddressFragment());
                    return true;

                case R.id.navigation_order:
                    changeFragment(new UserOrderListFragment());
                    return true;

            }
            return false;
        }

    };

    @SuppressLint("WrongConstant")
    public void changeFragment(Fragment fm){
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fm);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void writeToShared(String locdata) {
        SharedPreferences.Editor aEditor=SplashActivity.sharedpreferences.edit();
        aEditor.putString(SplashActivity.location,locdata);
        aEditor.commit();
    }


}
