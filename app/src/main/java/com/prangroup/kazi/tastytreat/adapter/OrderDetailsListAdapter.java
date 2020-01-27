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
import com.prangroup.kazi.tastytreat.fragments.CartFragment;
import com.prangroup.kazi.tastytreat.model.ItemAddToCartDataModel;
import com.prangroup.kazi.tastytreat.model.OrderDetailsDataModel;

import java.util.ArrayList;


public class OrderDetailsListAdapter extends BaseAdapter {
    private Context mContext;
    private Activity mActivity;
    ArrayList<OrderDetailsDataModel> itemList;


    public OrderDetailsListAdapter(Context context, Activity mActivity, ArrayList<OrderDetailsDataModel> itemList) {
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

            row = inflater.inflate(R.layout.orderitemdetails, viewGroup, false);
            ViewHolder.cv = (CardView)row.findViewById(R.id.cv);
            ViewHolder.product_name = (TextView)row.findViewById(R.id.product_name);
            ViewHolder.product_rate = (TextView)row.findViewById(R.id.product_rate);
            ViewHolder.product_qnty = (TextView)row.findViewById(R.id.product_qnty);
            ViewHolder.total = (TextView)row.findViewById(R.id.total);


            row.setTag(viewHolder);
        } else {
            viewHolder=(ViewHolder) row.getTag();

        }



        OrderDetailsDataModel aItemDataModel= itemList.get(position);

        viewHolder.product_name.setText(aItemDataModel.getItemname());
        viewHolder.product_rate.setText("Rate: "+aItemDataModel.getRate());
        viewHolder.product_qnty.setText("Quantity: "+aItemDataModel.getQnty());
        String type=aItemDataModel.getSold_type();
        if (type.equalsIgnoreCase("0")){
            viewHolder.total.setText("Type: Purchaged");
        }else{
            viewHolder.total.setText("Type: Free");
        }




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
        static TextView product_name,product_rate,product_qnty,total,remove;

    }
}
