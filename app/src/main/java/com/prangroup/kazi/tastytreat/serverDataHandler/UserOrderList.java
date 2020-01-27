package com.prangroup.kazi.tastytreat.serverDataHandler;

import com.prangroup.kazi.tastytreat.model.UserOrderListDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserOrderList {
    public static ArrayList<UserOrderListDataModel> UserOrderDataDB=new ArrayList<UserOrderListDataModel>();
    public static ArrayList<UserOrderListDataModel> getOrderList(JSONArray orderArray){
        UserOrderDataDB.clear();
        for(int i=0;i<orderArray.length();i++){
            try {
                JSONObject aJsonObject=orderArray.getJSONObject(i);
                String id=aJsonObject.getString("id");
                String order_status=aJsonObject.getString("order_status");
                String delivery_address=aJsonObject.getString("delivery_address");
                //String purchageProduct=aJsonObject.getString("purchageProduct");
                //String freeProduct=aJsonObject.getString("freeProduct");
                String total_amount=aJsonObject.getString("total_amount");

                UserOrderListDataModel userOrderListDataModel=new UserOrderListDataModel(id,order_status,delivery_address,"","",total_amount);
                UserOrderDataDB.add(userOrderListDataModel);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return UserOrderDataDB;
    }
}
