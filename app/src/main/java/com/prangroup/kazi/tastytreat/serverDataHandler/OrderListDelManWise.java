package com.prangroup.kazi.tastytreat.serverDataHandler;

import com.prangroup.kazi.tastytreat.model.DelManOrderDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderListDelManWise {
    public static ArrayList<DelManOrderDataModel> orderDataDB=new ArrayList<DelManOrderDataModel>();
    public static ArrayList<DelManOrderDataModel> getOrderList(JSONArray orderArray){
        orderDataDB.clear();
        for(int i=0;i<orderArray.length();i++){
            try {
                JSONObject aJsonObject=orderArray.getJSONObject(i);
                String id=aJsonObject.getString("id");
                String showroomname=aJsonObject.getString("showroomname");
                String showroomaddress=aJsonObject.getString("showroomaddress");
                String showroommobno=aJsonObject.getString("showroommobno");
                String showroomlat=aJsonObject.getString("showroomlat");
                String showroomlng=aJsonObject.getString("showroomlng");
                String customername=aJsonObject.getString("customername");
                String customermobno=aJsonObject.getString("customermobno");
                String delivery_address=aJsonObject.getString("delivery_address");
                /*String purchagedProduct=aJsonObject.getString("purchagedProduct");
                String freeProduct=aJsonObject.getString("freeProduct");*/
                String productprice=aJsonObject.getString("productprice");

                DelManOrderDataModel delManOrderDataModel=new DelManOrderDataModel(id,showroomname,showroomaddress,showroommobno,showroomlat,showroomlng,customername,
                        customermobno,delivery_address,"na","na",productprice);
                orderDataDB.add(delManOrderDataModel);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return orderDataDB;
    }
}
