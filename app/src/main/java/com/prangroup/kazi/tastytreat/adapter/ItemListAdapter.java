package com.prangroup.kazi.tastytreat.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.activity.AddToCardActivity;
import com.prangroup.kazi.tastytreat.fragments.HomeFragment;
import com.prangroup.kazi.tastytreat.model.ItemDataModel;
import com.prangroup.kazi.tastytreat.servertask.ApiLinks;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;




public class ItemListAdapter extends BaseAdapter {
    private Context mContext;
    private Activity mActivity;


    public ItemListAdapter(Context context,Activity mActivity, ArrayList<ItemDataModel> infoArrayList) {
        this.mContext = context;
        this.mActivity = mActivity;

    }

    @Override
    public int getCount() {
        return HomeFragment.filteredItemDataDB.size();
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

            row = inflater.inflate(R.layout.item, viewGroup, false);
            ViewHolder.cv = (CardView)row.findViewById(R.id.cv);
            ViewHolder.personName = (TextView)row.findViewById(R.id.person_name);
            ViewHolder.personAge = (TextView)row.findViewById(R.id.person_age);
            ViewHolder.amount = (TextView)row.findViewById(R.id.textView3);
            ViewHolder.sign = (TextView)row.findViewById(R.id.textView4);
            ViewHolder.personPhoto = (ImageView)row.findViewById(R.id.ivproduct);
            ViewHolder.lloffer=(LinearLayout)row.findViewById(R.id.lloffer);

            row.setTag(viewHolder);
        } else {
            viewHolder=(ViewHolder) row.getTag();

        }



        ItemDataModel aItemDataModel= HomeFragment.filteredItemDataDB.get(position);
        String imageName=aItemDataModel.getImage();
        Picasso.get().load(ApiLinks.imagebase+imageName).into(ViewHolder.personPhoto);
        viewHolder.personName.setText(aItemDataModel.getItemname());
        viewHolder.personAge.setText(aItemDataModel.getMrp());
        String haveOffer=aItemDataModel.getHaveoffer();
        if (haveOffer.equalsIgnoreCase("0")){
            String isActive=aItemDataModel.getOfferstatus();
            if (isActive.equalsIgnoreCase("0")){
                String offertype=aItemDataModel.getOffertype();
                if (offertype.equalsIgnoreCase("1") || offertype.equalsIgnoreCase("2")){
                    viewHolder.lloffer.setVisibility(View.VISIBLE);
                    viewHolder.amount.setText(aItemDataModel.getMrp());
                    if (offertype.equalsIgnoreCase("1")){
                        viewHolder.sign.setText("Tk");
                    }
                    if (offertype.equalsIgnoreCase("1")){
                        viewHolder.sign.setText("%");
                    }
                }
            }
        }
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("offerevent","id= "+position);
                Log.e("posname",position+"//"+HomeFragment.filteredItemDataDB.get(position).toString());

                Intent addToCart=new Intent(mContext, AddToCardActivity.class);
                addToCart.putExtra("itemPos", position);
                mActivity.startActivity(addToCart);
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

        static  LinearLayout lloffer;
        static CardView cv;
        static TextView personName;
        static TextView personAge;
        static TextView amount;
        static TextView sign;
        static ImageView personPhoto;

    }
}
