package com.prangroup.kazi.tastytreat.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.fragments.DelHomeFragment;
import com.prangroup.kazi.tastytreat.fragments.DelProgressFragment;
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;
import com.prangroup.kazi.tastytreat.model.DelManOrderDataModel;
import com.prangroup.kazi.tastytreat.servertask.OrderUpdateOperation;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DelManWiseOrderProgListAdapter extends BaseAdapter {
    private Context mContext;
    private Activity mActivity;
    ArrayList<DelManOrderDataModel> itemList;


    public DelManWiseOrderProgListAdapter(Context context, Activity mActivity, ArrayList<DelManOrderDataModel> itemList) {
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

            row = inflater.inflate(R.layout.delmanorderitemprog, viewGroup, false);
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
            ViewHolder.complete = (TextView)row.findViewById(R.id.complete);


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

        viewHolder.complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("pos",""+position);
                String orderID=itemList.get(position).getId();
                OrderUpdateOperation.sendOrderData(orderID, "2", DelProgressFragment.mActivity, DelProgressFragment.mContext, new VolleyCallBack() {
                    @Override
                    public void onSuccess(JSONObject getData) {
                        Log.e("getData",getData+getData.toString());
                        try {
                            String status=getData.getString("status");
                            String msg=getData.getString("Message");
                            HelpingLib.showmessage(mContext,msg);
                            if (status.equalsIgnoreCase("1")){
                                DelProgressFragment.orderProhDataDB.remove(position);
                                DelProgressFragment.setupOrderCart();
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
                customermobno,delivery_address,product_purchage,product_free,totalprice,complete;

    }
}
