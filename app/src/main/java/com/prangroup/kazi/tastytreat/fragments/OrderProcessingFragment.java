package com.prangroup.kazi.tastytreat.fragments;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.activity.MainActivity;
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;
import com.prangroup.kazi.tastytreat.model.OrderCollectionInfoModel;
import com.prangroup.kazi.tastytreat.model.UserAddressDataModel;
import com.prangroup.kazi.tastytreat.servertask.GetUserAddressOperation;
import com.prangroup.kazi.tastytreat.servertask.UserProductOrderOperation;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.prangroup.kazi.tastytreat.activity.MainActivity.orderCollectionDB;

public class OrderProcessingFragment extends Fragment {
    View rootView;
    public static Activity mActivity;
    public static Context mContext;
    LinearLayout deliveryprocess,paymentprocess,addressprocess,confirm,orderInfo,HomeOrderInfo;
    private RadioGroup rgdp,rgpmnt;
    private RadioButton rgdpbtn,rgpmntbtn;
    private Button btndeliverysubmit,btnaddresssubmit,btnpmntsubmit,btnconfirm;
    public static String dm="na",dp="na",da="na",pm="Cash";
    EditText etadr;
    int addAction=3;
    String charge="0";
    private RadioButton rbPM;
    ArrayList<UserAddressDataModel> addressDB=new ArrayList<UserAddressDataModel>();
    RadioGroup rgp;
    TextView tvpp,tvdc,tvtc,tvpm,tvda,tvoi,tvsn,tvaddress,tvmob;
    int tc;
    public OrderProcessingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_orderprocessing, container, false);
        mActivity = getActivity();
        mContext = getContext();
        tvpp=(TextView)rootView.findViewById(R.id.tvpp);
        tvdc=(TextView)rootView.findViewById(R.id.tvdc);
        tvtc=(TextView)rootView.findViewById(R.id.tvtc);
        tvpm=(TextView)rootView.findViewById(R.id.tvpm);
        tvda=(TextView)rootView.findViewById(R.id.tvda);
        tvoi=(TextView)rootView.findViewById(R.id.tvoi);
        tvsn=(TextView)rootView.findViewById(R.id.tvsn);
        tvaddress=(TextView)rootView.findViewById(R.id.tvaddress);
        tvmob=(TextView)rootView.findViewById(R.id.tvmob);
        etadr=(EditText)rootView.findViewById(R.id.etadr);
        rgp = (RadioGroup) rootView.findViewById(R.id.rgad);
        deliveryprocess=(LinearLayout) rootView.findViewById(R.id.deliveryprocess);
        paymentprocess=(LinearLayout) rootView.findViewById(R.id.paymentprocess);
        addressprocess=(LinearLayout) rootView.findViewById(R.id.addressprocess);
        confirm=(LinearLayout) rootView.findViewById(R.id.confirm);
        orderInfo=(LinearLayout) rootView.findViewById(R.id.orderInfo);
        HomeOrderInfo=(LinearLayout) rootView.findViewById(R.id.HomeOrderInfo);
        rgdp=(RadioGroup)rootView.findViewById(R.id.rgdp);
        rgpmnt=(RadioGroup)rootView.findViewById(R.id.rgpmnt);
        btnaddresssubmit=(Button)rootView.findViewById(R.id.btnaddrsubmit);
        btnpmntsubmit=(Button)rootView.findViewById(R.id.btnpmntsubmit);
        btndeliverysubmit=(Button)rootView.findViewById(R.id.btndeliverysubmit);
        btnconfirm=(Button)rootView.findViewById(R.id.btnconfirm);

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONArray aOrderArray=new JSONArray();
                if (HomeFragment.orderDB.size()>0){
                    for (int i=0;i<HomeFragment.orderDB.size();i++){
                        JSONObject aObj=new JSONObject();
                        try {
                            aObj.put("itemID",HomeFragment.orderDB.get(i).getItemCode());
                            aObj.put("qnty",HomeFragment.orderDB.get(i).getQnty());
                            aObj.put("rate",HomeFragment.orderDB.get(i).getRate());
                            aObj.put("type",HomeFragment.orderDB.get(i).getType());
                            aOrderArray.put(aObj);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
                Log.e("confirm",MainActivity.userid+HomeFragment.showRoom+da+CartFragment.totalProductPrice+charge+tc+pm+aOrderArray.toString());
                UserProductOrderOperation.setorderData(MainActivity.userid, HomeFragment.showRoom, dm,da, String.valueOf(CartFragment.totalProductPrice), String.valueOf(charge), String.valueOf(tc), pm, aOrderArray, mActivity, mContext, new VolleyCallBack() {
                    @Override
                    public void onSuccess(JSONObject getData) {
                        Log.e("",getData.toString());
                        try {
                            int status=getData.getInt("status");
                            String orderid=getData.getString("orderid");
                            if (status==1){
                                clearPreviousOrderData();
                                //redirectToHome();

                                if (dm.equalsIgnoreCase("0")){
                                    JSONObject aOrderInfoOnj=getData.getJSONObject("data");
                                    String name=aOrderInfoOnj.getString("name");
                                    String address=aOrderInfoOnj.getString("address");
                                    String mobno=aOrderInfoOnj.getString("mobno");
                                    String lat=aOrderInfoOnj.getString("lat");
                                    String lng=aOrderInfoOnj.getString("lng");
                                    OrderCollectionInfoModel orderInfoModel=new OrderCollectionInfoModel(orderid,name,address,mobno,lat,lng);
                                    MainActivity.orderCollectionDB.add(orderInfoModel);
                                    if (MainActivity.orderCollectionDB.size()>0){
                                        setUpInfo();
                                        confirm.setVisibility(View.GONE);
                                        orderInfo.setVisibility(View.VISIBLE);
                                    }

                                }else{
                                    confirm.setVisibility(View.GONE);
                                    HomeOrderInfo.setVisibility(View.VISIBLE);
                                }
                                String msg=getData.getString("msg");
                                HelpingLib.showmessage(mContext,msg);
                            }else{
                                HelpingLib.showmessage(mContext,"Order not placed");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        btnpmntsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                Log.e("Details:",dm+"/"+dp+"/"+da+"/"+pm);
                paymentprocess.setVisibility(View.GONE);
                confirm.setVisibility(View.VISIBLE);
                tvpp.setText("Product price: "+CartFragment.totalProductPrice);
                if (dm.equalsIgnoreCase("1")){
                    tvdc.setText("Home delivery charge: "+charge);
                    tvda.setText("Delivery address: "+da);
                }else{
                    tvdc.setVisibility(View.GONE);
                    tvda.setVisibility(View.GONE);
                }

                tc=CartFragment.totalProductPrice+Integer.valueOf(charge);
                tvtc.setText("Total price: "+tc);
                tvpm.setText("Payment method: "+pm);



            }
        });

        btnaddresssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addAction==1){
                    da=etadr.getText().toString();
                    if (da.equalsIgnoreCase("")){
                        addAction=1;
                    }else{
                        addAction=3;
                        addressprocess.setVisibility(View.GONE);
                        paymentprocess.setVisibility(View.VISIBLE);
                    }

                }
                if (da.equalsIgnoreCase("")){
                    HelpingLib.showmessage(mContext,"Delivery address not found");
                }else{
                    addressprocess.setVisibility(View.GONE);
                    paymentprocess.setVisibility(View.VISIBLE);
                }
                Log.e("del addr",da);
            }
        });

        btndeliverysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId=rgdp.getCheckedRadioButtonId();
                rgdpbtn=(RadioButton)rootView.findViewById(selectedId);
                dp=rgdpbtn.getText().toString();
                if (dp.equalsIgnoreCase("Takeaway")){
                    dm="0";
                    deliveryprocess.setVisibility(View.GONE);
                    paymentprocess.setVisibility(View.VISIBLE);
                }else if (dp.equalsIgnoreCase("Home Delivery(Charge apply)")){
                    GetUserAddressOperation.sendUserID(MainActivity.userid, mContext, new VolleyCallBack() {
                        @Override
                        public void onSuccess(JSONObject getData) {
                            Log.e("getData",getData.toString());
                            dm="1";
                            try {
                                String status=getData.getString("status");
                                charge=getData.getString("charge");

                                if (status.equalsIgnoreCase("1")){
                                    JSONArray jsonArray=getData.getJSONArray("data");
                                    addressDB.clear();
                                    for (int i=0;i<jsonArray.length();i++){
                                        JSONObject aJsonObject=jsonArray.getJSONObject(i);
                                        String id=aJsonObject.getString("id");
                                        String name=aJsonObject.getString("name");
                                        String details=aJsonObject.getString("details");

                                        UserAddressDataModel addressDataModel=new UserAddressDataModel(id,name,details);
                                        addressDB.add(addressDataModel);
                                    }
                                    UserAddressDataModel addressDataModel=new UserAddressDataModel("000","Others","");
                                    addressDB.add(addressDataModel);

                                    if (addressDB.size()>0){
                                        deliveryprocess.setVisibility(View.GONE);
                                        addressprocess.setVisibility(View.VISIBLE);

                                        for (int i=0;i<addressDB.size();i++){
                                            RadioButton rbn = new RadioButton(mContext);
                                            rbn.setId(Integer.parseInt(addressDB.get(i).getId()));
                                            if (addressDB.get(i).getDetails().equalsIgnoreCase("")){
                                                rbn.setText(addressDB.get(i).getName());
                                            }else{
                                                rbn.setText(addressDB.get(i).getName()+"("+addressDB.get(i).getDetails()+")");
                                            }

                                            rbn.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.colorPrimaryDark));
                                            rgp.addView(rbn);
                                            if (i==0){
                                                rbn.setChecked(true);
                                                da=addressDB.get(i).getDetails();
                                            }
                                        }

                                    }

                                    Log.e("getData",jsonArray.toString());
                                }else{
                                    addAction=1;
                                    deliveryprocess.setVisibility(View.GONE);
                                    addressprocess.setVisibility(View.VISIBLE);
                                    HelpingLib.showmessage(mContext,"No address found");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==000){
                    da="";
                    addAction=1;
                    etadr.setVisibility(View.VISIBLE);
                }else {
                    etadr.setVisibility(View.GONE);
                    addAction=2;
                    for (int i=0;i<addressDB.size();i++){
                        if (addressDB.get(i).getId().equalsIgnoreCase(String.valueOf(checkedId))){
                            da=addressDB.get(i).getDetails();
                            i=i+addressDB.size();
                        }
                    }
                }
            }
        });

        rgpmnt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rbPM=(RadioButton)group.findViewById(checkedId);
                pm=rbPM.getText().toString();
                String text=pm;
            }
        });
        return rootView;
    }

    private void setUpInfo() {
        OrderCollectionInfoModel aCollectionInfoModel=orderCollectionDB.get(0);
        tvoi.setText("order ID: "+aCollectionInfoModel.getOrderid());
        tvsn.setText("Showroom name:"+aCollectionInfoModel.getName());
        tvaddress.setText("Address:"+aCollectionInfoModel.getAddress());
        tvmob.setText("Mobile no: 0"+aCollectionInfoModel.getMobno());
    }

    private void redirectToHome() {
        Fragment fm=new HomeFragment();
        MainActivity.ft = getActivity().getSupportFragmentManager().beginTransaction();
        MainActivity.ft.replace(R.id.content, fm);
        MainActivity.ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        MainActivity.ft.addToBackStack(null);
        MainActivity.ft.commit();

    }

    private void clearPreviousOrderData() {
        HomeFragment.orderDB.clear();
        MainActivity.orderCollectionDB.clear();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
