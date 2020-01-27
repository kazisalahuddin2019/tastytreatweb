package com.prangroup.kazi.tastytreat.fragments;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.activity.AddToCardActivity;
import com.prangroup.kazi.tastytreat.activity.MainActivity;
import com.prangroup.kazi.tastytreat.activity.SplashActivity;
import com.prangroup.kazi.tastytreat.adapter.AutoCompleteAdapter;
import com.prangroup.kazi.tastytreat.adapter.ItemListAdapter;
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;
import com.prangroup.kazi.tastytreat.model.ItemAddToCartDataModel;
import com.prangroup.kazi.tastytreat.model.ItemDataModel;
import com.prangroup.kazi.tastytreat.model.OfferItemDataModel;
import com.prangroup.kazi.tastytreat.model.ShowroomDataModel;
import com.prangroup.kazi.tastytreat.servertask.ApiLinks;
import com.prangroup.kazi.tastytreat.servertask.GetItemDataOperation;
import com.prangroup.kazi.tastytreat.servertask.GetShowroomOperation;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;
import com.prangroup.kazi.tastytreat.utils.MyTextWatcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.prangroup.kazi.tastytreat.activity.MainActivity.mAactivity;

public class HomeFragment extends Fragment {
    View rootView;
    public static Activity mActivity;
    public static Context mContext;
    public static String showroomList = "";
    public static ArrayList<ItemDataModel> freeItemDataDB=new ArrayList<ItemDataModel>();
    public static ArrayList<ItemDataModel> filteredItemDataDB=new ArrayList<ItemDataModel>();
    public static ArrayList<ItemDataModel> ItemDataDB=new ArrayList<ItemDataModel>();
    public static ArrayList<String> filteredShowroomDB = new ArrayList<String>();
    public static ArrayList<ShowroomDataModel> showroomDB = new ArrayList<ShowroomDataModel>();
    public static ArrayList<ItemAddToCartDataModel> orderDB = new ArrayList<ItemAddToCartDataModel>();
    public static ArrayList<OfferItemDataModel> OfferItemDataDB=new ArrayList<OfferItemDataModel>();
    public static ArrayList<String> locArr = new ArrayList<String>();
    public static String showRoom;
    public static ImageView ivlocdropdown;
    public static AlertDialog.Builder dialogBuilderr;
    public static AlertDialog searchAlertDialogg;
    int animationSourceBottomToBottom;
    public static AutoCompleteAdapter lAdapter;
    AutoCompleteTextView pickdepolistauto;
    Button btnsubmit;
    TextView tvname;
    public static ListView itemlist;
    public static ImageSlider imageSlider;
    public static List<SlideModel> imageList;
    public static RelativeLayout locll,imagesliderlayout;
    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mActivity = getActivity();
        mContext = getContext();

        imageList = new ArrayList<SlideModel>();
        imageList.add(new SlideModel(ApiLinks.imagebase+"undefine.jpg"));
        imageList.add(new SlideModel(ApiLinks.imagebase+"special.png"));
        imageList.add(new SlideModel(ApiLinks.imagebase+"house.jpg"));

        tvname=rootView.findViewById(R.id.tvname);
        locll=(RelativeLayout) rootView.findViewById(R.id.locll);
        imagesliderlayout=(RelativeLayout) rootView.findViewById(R.id.imagesliderlayout);
        itemlist = (ListView)rootView.findViewById(R.id.itemlist);
        imageSlider = rootView.findViewById(R.id.image_slider);
        ivlocdropdown=rootView.findViewById(R.id.ivdropdown);
        ivlocdropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAddress();
            }
        });
        SplashActivity.sharedpreferences = mActivity.getSharedPreferences(SplashActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        final String location = SplashActivity.sharedpreferences.getString(SplashActivity.location, "location not found");
        tvname.setText(location);
        locll.setVisibility(View.VISIBLE);
        if (location.equalsIgnoreCase("location not found")) {
            Intent intent = new Intent(mActivity, MainActivity.class);
            mActivity.startActivity(intent);

        } else {
            loadItem(location);
        }


        return rootView;
    }

    private static void loadItem(final String location) {

        GetShowroomOperation.getShowroomData(mAactivity, mContext, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject getData) {
                Log.e("getData", getData.toString());
                try {
                    String msg = getData.getString("Message");
                    if (msg.equalsIgnoreCase("Successful")) {
                        JSONArray locationArray = getData.getJSONArray("data");
                        JSONArray itemdataArray=getData.getJSONArray("itemdata");
                        Log.e("locationArray", locationArray.toString());
                        locArr.clear();
                        showroomDB.clear();
                        for (int i = 0; i < locationArray.length(); i++) {
                            JSONObject aLocObj = locationArray.getJSONObject(i);
                            String id = aLocObj.getString("id");
                            String code = aLocObj.getString("code");
                            String location = aLocObj.getString("loc");
                            ShowroomDataModel showroomDataModel = new ShowroomDataModel(id, code, location);
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
                        if (locArr.size() > 0) {
                            getShowroomID(location);
                            if (filteredShowroomDB.size() > 0) {
                                getItemDataFromServer();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private static boolean isLocExist(String location) {
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

    private static void getItemDataFromServer() {
        GetItemDataOperation.getItemData(showroomList, mAactivity, mContext, new VolleyCallBack() {
            @Override
            public void onSuccess(JSONObject getData) {
                String jsonFormattedString = null;
                try {
                    jsonFormattedString = getData.getString("GetShowroomResult");
                    String jsondata = jsonFormattedString.replace("\\", "");
                    Log.e("jsondata", getData.toString());
                    JSONArray jsonArray = new JSONArray(jsondata);
                    JSONObject aJsonObject=jsonArray.getJSONObject(0);
                    showRoom=aJsonObject.getString("S_CODE");
                    filteredItemDataDB.clear();
                    freeItemDataDB.clear();
                    int lngth=jsonArray.length();
                    Log.e("size",""+lngth);
                    for (int i=0;i<lngth;i++){
                        JSONObject aItemObj=jsonArray.getJSONObject(i);
                        String code=aItemObj.getString("CODE");
                        String price=aItemObj.getString("MRP");
                        updateItemDB(code,price);

                    }
                    setPage();
                    Log.e("jsonArray", jsonArray.get(0).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    HelpingLib.showmessage(mContext,"Server data parsing error");
                }


            }
        });
    }

    private static void setPage() {
        int actualsize=ItemDataDB.size();
        int filteredsize=filteredItemDataDB.size();
        int freeIteemsize=freeItemDataDB.size();
        Log.e("sizesize",actualsize+"///"+filteredsize+"///"+freeIteemsize);
        locll.setVisibility(View.VISIBLE);
        if (OfferItemDataDB.size()>0){
            imagesliderlayout.setVisibility(View.VISIBLE);
            sliderImage();
        }else{
            imagesliderlayout.setVisibility(View.GONE);
        }

        //RVAdapter adapter = new RVAdapter();
        ItemListAdapter itemListAdapter=new ItemListAdapter(mContext,mAactivity,filteredItemDataDB);
        itemlist.setAdapter(itemListAdapter);


    }

    private static void updateItemDB(String code,String price) {

        for (int j=0;j<ItemDataDB.size();j++){
            ItemDataModel aItemDataModel=ItemDataDB.get(j);
            String dbItemCode=aItemDataModel.getItemcode();
            if (dbItemCode.equalsIgnoreCase(code)){
                aItemDataModel.setIsShown("0");
                String haveoffer=aItemDataModel.getHaveoffer();//0=have offer 1=discount 2=percentage 3=product offer
                String offerIsActive=aItemDataModel.getOfferstatus(); //o=offer active
                if (haveoffer.equalsIgnoreCase("0") && offerIsActive.equalsIgnoreCase("0")){

                    String offerType=aItemDataModel.getOffertype();
                    if (offerType.equalsIgnoreCase("1")){
                        String discount=aItemDataModel.getAmount();
                        int mrp=Integer.valueOf(price)-Integer.valueOf(discount);
                        aItemDataModel.setActualPrice(price);
                        aItemDataModel.setMrp(String.valueOf(mrp));
                        filteredItemDataDB.add(aItemDataModel);
                        freeItemDataDB.add(aItemDataModel);
                    }else if (offerType.equalsIgnoreCase("2")){
                        int discountPercnt=Integer.valueOf(aItemDataModel.getAmount())/100;
                        int discountAmount=Integer.valueOf(price)*discountPercnt;
                        int mrp=Integer.valueOf(price)-Integer.valueOf(discountAmount);
                        aItemDataModel.setActualPrice(price);
                        aItemDataModel.setMrp(String.valueOf(mrp));
                        filteredItemDataDB.add(aItemDataModel);
                        freeItemDataDB.add(aItemDataModel);
                    }else if (offerType.equalsIgnoreCase("3")){
                        aItemDataModel.setActualPrice(price);
                        aItemDataModel.setMrp(String.valueOf(price));
                        filteredItemDataDB.add(aItemDataModel);
                        freeItemDataDB.add(aItemDataModel);
                    }else if (offerType.equalsIgnoreCase("NULL")){
                        aItemDataModel.setActualPrice(price);
                        aItemDataModel.setMrp(String.valueOf(price));
                        filteredItemDataDB.add(aItemDataModel);
                    }
                }else{
                    aItemDataModel.setActualPrice(price);
                    aItemDataModel.setMrp(String.valueOf(price));
                    filteredItemDataDB.add(aItemDataModel);
                }

//work in this line
            }
        }
    }

    private static void getShowroomID(String location) {
        String showroomListDemo = "";
        for (int i = 0; i < showroomDB.size(); i++) {
            String dbLocData = showroomDB.get(i).getLocation();
            if (dbLocData.equalsIgnoreCase(location)) {
                String dbCodeData = showroomDB.get(i).getCode();
                filteredShowroomDB.add(dbCodeData);
                if (i == showroomDB.size() - 2) {
                    showroomListDemo = showroomListDemo + dbCodeData;
                } else {
                    showroomListDemo = showroomListDemo + dbCodeData + ",";
                }

            }
        }
        showroomList = showroomListDemo.substring(0, showroomListDemo.length() - 1);
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
                    tvname.setText(locData);
                    MainActivity.writeToShared(locData);
                    getShowroomID(locData);
                    if (filteredShowroomDB.size() > 0) {
                        getItemDataFromServer();
                    }
                    searchAlertDialogg.dismiss();

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


    private static void sliderImage(){
        imageList = new ArrayList<SlideModel>();
        for (int i=0;i<OfferItemDataDB.size();i++){
            imageList.add(new SlideModel(ApiLinks.imagebase+OfferItemDataDB.get(i).getName()));
        }

        imageSlider.setImageList(imageList, false);
        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Log.e("posname",i+"//"+imageList.get(i).getImageUrl());
                Intent addToCart=new Intent(mContext, AddToCardActivity.class);
                addToCart.putExtra("itemPos", i);
                mActivity.startActivity(addToCart);

            }
        });
    }
}
