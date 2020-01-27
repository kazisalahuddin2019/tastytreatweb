package com.prangroup.kazi.tastytreat.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.fragments.DelHomeFragment;
import com.prangroup.kazi.tastytreat.fragments.UserOrderListFragment;
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;
import com.prangroup.kazi.tastytreat.model.DelManOrderDataModel;
import com.prangroup.kazi.tastytreat.model.OrderDetailsDataModel;
import com.prangroup.kazi.tastytreat.model.UserOrderListDataModel;
import com.prangroup.kazi.tastytreat.servertask.OrderUpdateOperation;
import com.prangroup.kazi.tastytreat.servertask.UserOrderDetailsOperation;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class UserOrderListAdapter extends BaseAdapter {
    private Context mContext;
    private Activity mActivity;
    ArrayList<UserOrderListDataModel> itemList;
    ArrayList<OrderDetailsDataModel> detailsDB=new ArrayList<OrderDetailsDataModel>();
    public static AlertDialog.Builder dialogBuilderr;
    public static AlertDialog searchAlertDialogg;
    int animationSourceBottomToBottom;

    public UserOrderListAdapter(Context context, Activity mActivity, ArrayList<UserOrderListDataModel> itemList) {
        this.mContext = context;
        this.mActivity = mActivity;
        this.itemList=itemList;

    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }




    @Override
    public View getView(final int position,View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View row=convertView;
        try {
            UserOrderListDataModel aItemDataModel= itemList.get(position);
            String status=aItemDataModel.getOrder_status();
            //if (status.equalsIgnoreCase(UserOrderListFragment.status)){


            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                viewHolder = new ViewHolder();

                row = inflater.inflate(R.layout.userorderitem, viewGroup, false);
                ViewHolder.cv = (CardView)row.findViewById(R.id.cv);
                ViewHolder.order_id = (TextView)row.findViewById(R.id.order_id);
                ViewHolder.delivery_address = (TextView)row.findViewById(R.id.delivery_address);
                ViewHolder.product_purchage = (TextView)row.findViewById(R.id.product_purchage);
                ViewHolder.product_free = (TextView)row.findViewById(R.id.product_free);
                ViewHolder.totalprice = (TextView)row.findViewById(R.id.totalprice);
                ViewHolder.processing = (TextView)row.findViewById(R.id.processing);
                ViewHolder.detils = (TextView)row.findViewById(R.id.detils);


                row.setTag(viewHolder);
            } else {
                viewHolder=(ViewHolder) row.getTag();

            }


            viewHolder.order_id.setText("Order ID: "+aItemDataModel.getId());
            viewHolder.delivery_address.setText("Delivery Address: "+aItemDataModel.getDelivery_address());
            viewHolder.product_purchage.setText("Product Purchage: "+aItemDataModel.getPurchageProduct());
            viewHolder.product_free.setText("Free: "+aItemDataModel.getFreeProduct());
            viewHolder.totalprice.setText("Total price: "+aItemDataModel.getTotal_amount());
            if (status.equalsIgnoreCase("1")){
                viewHolder.processing.setVisibility(View.VISIBLE);
                viewHolder.processing.setText("Complete");
            }
            viewHolder.detils.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("pos",""+position);
                    String orderID=itemList.get(position).getId();

                    UserOrderDetailsOperation.sendOrderID(orderID, mActivity, new VolleyCallBack() {
                        @Override
                        public void onSuccess(JSONObject getData) {
                            Log.e("getData",getData.toString());
                            try {
                                String status=getData.getString("status");
                                if (status.equalsIgnoreCase("1")){
                                    JSONArray jsonArray=getData.getJSONArray("data");
                                    if (jsonArray.length()>0){
                                        detailsDB.clear();
                                        for (int i=0;i<jsonArray.length();i++){
                                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                                            String itemname=jsonObject.getString("itemname");
                                            String qnty=jsonObject.getString("qnty");
                                            String rate=jsonObject.getString("rate");
                                            String sold_type=jsonObject.getString("sold_type");

                                            OrderDetailsDataModel orderDetailsDataModel=new OrderDetailsDataModel(itemname,qnty,rate,sold_type);
                                            detailsDB.add(orderDetailsDataModel);
                                        }
                                    }
                                    if (detailsDB.size()>0){
                                        dialogBuilderr = new AlertDialog.Builder(mContext);
                                        pickupDialog();
                                    }
                                }else{
                                    HelpingLib.showmessage(mContext,"No Data found");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });

            viewHolder.processing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("pos",""+position);
                    String orderID=itemList.get(position).getId();
                    OrderUpdateOperation.sendOrderData(orderID, "2", UserOrderListFragment.mActivity, UserOrderListFragment.mContext, new VolleyCallBack() {
                        @Override
                        public void onSuccess(JSONObject getData) {
                            Log.e("getData",getData+getData.toString());
                            try {
                                String status=getData.getString("status");
                                String msg=getData.getString("Message");
                                HelpingLib.showmessage(mContext,msg);
                                if (status.equalsIgnoreCase("1")){

                                    updateDatabase(orderID);
                                    UserOrderListFragment.setupOrderCart();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });

            //}
        }catch (Exception e){

        }



        return row;


    }


    private void pickupDialog() {
        dialogBuilderr = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.order_details_layout, null);
        dialogBuilderr.setView(dialogView);
        searchAlertDialogg = dialogBuilderr.create();
        searchAlertDialogg.getWindow().getAttributes().windowAnimations = animationSourceBottomToBottom;
        searchAlertDialogg.show();
        ListView listView=dialogView.findViewById(R.id.itemdetailslist);
        OrderDetailsListAdapter orderDetailsListAdapter=new OrderDetailsListAdapter(mContext,mActivity,detailsDB);
        listView.setAdapter(orderDetailsListAdapter);

    }

    private void updateDatabase(String orderID) {
        for (int i=0;i<UserOrderListFragment.orderListDataDB.size();i++){
            String dbOrderID=UserOrderListFragment.orderListDataDB.get(i).getId();
            if (dbOrderID.equalsIgnoreCase(orderID)){
                UserOrderListFragment.orderListDataDB.get(i).setOrder_status("2");
                i=UserOrderListFragment.orderListDataDB.size()+10;
            }
        }
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    private static class ViewHolder {


        static CardView cv;
        static TextView order_id,delivery_address,product_purchage,product_free,totalprice,processing,detils;

    }
}
