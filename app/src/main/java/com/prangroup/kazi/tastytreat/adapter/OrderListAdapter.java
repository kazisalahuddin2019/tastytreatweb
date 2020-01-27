package com.prangroup.kazi.tastytreat.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.fragments.CartFragment;
import com.prangroup.kazi.tastytreat.fragments.HomeFragment;
import com.prangroup.kazi.tastytreat.model.ItemAddToCartDataModel;
import com.prangroup.kazi.tastytreat.model.ItemDataModel;
import com.prangroup.kazi.tastytreat.servertask.ApiLinks;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class OrderListAdapter extends BaseAdapter {
    private Context mContext;
    private Activity mActivity;
    ArrayList<ItemAddToCartDataModel> itemList;


    public OrderListAdapter(Context context, Activity mActivity, ArrayList<ItemAddToCartDataModel> itemList) {
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

            row = inflater.inflate(R.layout.orderitem, viewGroup, false);
            ViewHolder.cv = (CardView)row.findViewById(R.id.cv);
            ViewHolder.product_name = (TextView)row.findViewById(R.id.product_name);
            ViewHolder.product_rate = (TextView)row.findViewById(R.id.product_rate);
            ViewHolder.product_qnty = (TextView)row.findViewById(R.id.product_qnty);
            ViewHolder.total = (TextView)row.findViewById(R.id.total);
            ViewHolder.remove = (TextView)row.findViewById(R.id.remove);


            row.setTag(viewHolder);
        } else {
            viewHolder=(ViewHolder) row.getTag();

        }



        ItemAddToCartDataModel aItemDataModel= itemList.get(position);

        viewHolder.product_name.setText(aItemDataModel.getItemName());
        viewHolder.product_rate.setText("Rate: "+aItemDataModel.getRate());
        viewHolder.product_qnty.setText("Quantity: "+aItemDataModel.getQnty());
        viewHolder.total.setText("Price: "+aItemDataModel.getTotalprice());
        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("pos",""+position);
                itemList.remove(position);
                if (itemList.size()>0){
                    CartFragment.setupOrderCart();
                    CartFragment.setPrice();
                }

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
        static TextView product_name,product_rate,product_qnty,total,remove;

    }
}
