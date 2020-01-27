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
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;
import com.prangroup.kazi.tastytreat.model.DelManOrderDataModel;
import com.prangroup.kazi.tastytreat.model.OrderDetailsDataModel;
import com.prangroup.kazi.tastytreat.servertask.OrderUpdateOperation;
import com.prangroup.kazi.tastytreat.servertask.UserOrderDetailsOperation;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DelManWiseOrderListAdapter extends BaseAdapter {
    private Context mContext;
    private Activity mActivity;
    ArrayList<DelManOrderDataModel> itemList;
    ArrayList<OrderDetailsDataModel> detailsDB=new ArrayList<OrderDetailsDataModel>();
    public static AlertDialog.Builder dialogBuilderrr;
    public static AlertDialog searchAlertDialoggg;
    int animationSourceBottomToBottom;

    public DelManWiseOrderListAdapter(Context context, Activity mActivity, ArrayList<DelManOrderDataModel> itemList) {
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

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();

            row = inflater.inflate(R.layout.delmanorderitem, viewGroup, false);
            ViewHolder.cv = (CardView)row.findViewById(R.id.cv);
            ViewHolder.order_id = (TextView)row.findViewById(R.id.order_id);
            ViewHolder.Showroom_name = (TextView)row.findViewById(R.id.Showroom_name);
            ViewHolder.showroom_address = (TextView)row.findViewById(R.id.showroom_address);
            ViewHolder.showroom_mobno = (TextView)row.findViewById(R.id.showroom_mobno);
            ViewHolder.customername = (TextView)row.findViewById(R.id.customername);
            ViewHolder.customermobno = (TextView)row.findViewById(R.id.customermobileno);
            ViewHolder.delivery_address = (TextView)row.findViewById(R.id.delivery_address);
            ViewHolder.product_purchage = (TextView)row.findViewById(R.id.product_purchage);
            ViewHolder.product_free = (TextView)row.findViewById(R.id.product_free);
            ViewHolder.totalprice = (TextView)row.findViewById(R.id.totalprice);
            ViewHolder.detilss = (TextView)row.findViewById(R.id.detilss);
            ViewHolder.processing = (TextView)row.findViewById(R.id.processing);


            row.setTag(viewHolder);
        } else {
            viewHolder=(ViewHolder) row.getTag();

        }

        DelManOrderDataModel aItemDataModel= itemList.get(position);

        viewHolder.order_id.setText("Order ID: "+aItemDataModel.getId());
        viewHolder.Showroom_name.setText("Showroom name: "+aItemDataModel.getShowroomname());
        viewHolder.showroom_address.setText("Showroom address: "+aItemDataModel.getShowroomaddress());
        viewHolder.showroom_mobno.setText("Showroom mobno: "+aItemDataModel.getShowroommobno());
        viewHolder.customername.setText("Customer name: "+aItemDataModel.getCustomername());
        viewHolder.customermobno.setText("Customer contact no: "+aItemDataModel.getCustomermobno());
        viewHolder.delivery_address.setText("Delivery Address: "+aItemDataModel.getDelivery_address());
        viewHolder.product_purchage.setText("Product Purchage: "+aItemDataModel.getPurchagedProduct());
        viewHolder.product_free.setText("Free: "+aItemDataModel.getFreeProduct());
        viewHolder.totalprice.setText("Total price: "+aItemDataModel.getProductprice());

        viewHolder.detilss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("pos",""+position);
                String orderID=itemList.get(position).getId();

                UserOrderDetailsOperation.sendOrderID(orderID, DelHomeFragment.mActivity, new VolleyCallBack() {
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
                                    dialogBuilderrr = new AlertDialog.Builder(DelHomeFragment.mContext);
                                    pickupDialog();
                                }
                            }else{
                                HelpingLib.showmessage(DelHomeFragment.mContext,"No Data found");
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
                OrderUpdateOperation.sendOrderData(orderID, "1", DelHomeFragment.mActivity, DelHomeFragment.mContext, new VolleyCallBack() {
                    @Override
                    public void onSuccess(JSONObject getData) {
                        Log.e("getData",getData+getData.toString());
                        try {
                            String status=getData.getString("status");
                            String msg=getData.getString("Message");
                            HelpingLib.showmessage(mContext,msg);
                            if (status.equalsIgnoreCase("1")){
                                DelHomeFragment.orderDataDB.remove(position);
                                DelHomeFragment.setupOrderCart();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


        return row;
    }

    private void pickupDialog() {
        dialogBuilderrr = new AlertDialog.Builder(DelHomeFragment.mContext);
        LayoutInflater inflater = DelHomeFragment.mActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.order_details_layout, null);
        dialogBuilderrr.setView(dialogView);
        searchAlertDialoggg = dialogBuilderrr.create();
        searchAlertDialoggg.getWindow().getAttributes().windowAnimations = animationSourceBottomToBottom;
        searchAlertDialoggg.show();
        ListView listView=dialogView.findViewById(R.id.itemdetailslist);
        OrderDetailsListAdapter orderDetailsListAdapter=new OrderDetailsListAdapter(DelHomeFragment.mContext,DelHomeFragment.mActivity,detailsDB);
        listView.setAdapter(orderDetailsListAdapter);

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
        static TextView order_id,Showroom_name,showroom_address,showroom_mobno,customername,
                customermobno,delivery_address,product_purchage,product_free,totalprice,detilss,processing;

    }
}
