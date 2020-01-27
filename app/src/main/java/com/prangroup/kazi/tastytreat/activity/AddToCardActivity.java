package com.prangroup.kazi.tastytreat.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.fragments.HomeFragment;
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;
import com.prangroup.kazi.tastytreat.model.ItemAddToCartDataModel;
import com.prangroup.kazi.tastytreat.model.ItemDataModel;
import com.prangroup.kazi.tastytreat.servertask.ApiLinks;
import com.prangroup.kazi.tastytreat.servertask.GetItemStockInfoOperation;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddToCardActivity extends AppCompatActivity {
    ImageView ivba;
    TextView tvName,tvPrice,amount,sign,tvVal;
    static  LinearLayout lloffer;
    Button btnminus,btnplus,btnaddtocart;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=(Context)this;
        setContentView(R.layout.activity_add_to_cart);
        ivba=(ImageView)findViewById(R.id.ivproduct);
        tvName=(TextView)findViewById(R.id.name);
        tvPrice=(TextView)findViewById(R.id.price);
        tvVal=(TextView)findViewById(R.id.tvVal);
        btnminus=(Button) findViewById(R.id.btnminus);
        btnplus=(Button)findViewById(R.id.btnplus);
        btnaddtocart=(Button)findViewById(R.id.btnaddtocart);
        ivba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        int itemPos = intent.getExtras().getInt("itemPos",-100);
        final ItemDataModel aItemDataModel= HomeFragment.filteredItemDataDB.get(itemPos);
        String imageName=aItemDataModel.getImage();
        Picasso.get().load(ApiLinks.imagebase+imageName).into(ivba);
        tvName.setText(aItemDataModel.getItemname());
        tvPrice.setText(aItemDataModel.getMrp()+" Tk");
        amount = (TextView)findViewById(R.id.textView3);
        sign = (TextView)findViewById(R.id.textView4);
        lloffer=(LinearLayout)findViewById(R.id.lloffer);

        String haveOffer=aItemDataModel.getHaveoffer();
        if (haveOffer.equalsIgnoreCase("0")){
            String isActive=aItemDataModel.getOfferstatus();
            if (isActive.equalsIgnoreCase("0")){
                String offertype=aItemDataModel.getOffertype();
                if (offertype.equalsIgnoreCase("1") || offertype.equalsIgnoreCase("2")){
                    lloffer.setVisibility(View.VISIBLE);
                    amount.setText(aItemDataModel.getMrp());
                    if (offertype.equalsIgnoreCase("1")){
                        sign.setText("Tk");
                    }
                    if (offertype.equalsIgnoreCase("1")){
                        sign.setText("%");
                    }
                }
            }
        }

        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val=getCurrentVal();
                val=val+1;
                setUpdatedVal(val);
            }
        });
        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val=getCurrentVal();
                if (val>1){
                    val=val-1;
                    setUpdatedVal(val);
                }else{
                    HelpingLib.showmessage(mContext,"Minimum quantity 1");
                }

            }
        });

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int val=getCurrentVal();
                final String itemCode=aItemDataModel.getItemcode();
                final String name=aItemDataModel.getItemname();
                final String mrp=aItemDataModel.getMrp();
                final String showroomId=HomeFragment.showRoom;
                Log.e("order",showroomId+"/"+itemCode+""+val);
                GetItemStockInfoOperation.getItemStockInfo(showroomId, itemCode, mContext, new VolleyCallBack() {
                    @Override
                    public void onSuccess(JSONObject getData) {
                        Log.e("iteminfo",getData.toString());
                        String jsonFormattedString = null;
                        try {
                            jsonFormattedString = getData.getString("GetShowroomWithItemResult");
                            String jsondata = jsonFormattedString.replace("\\", "");
                            Log.e("jsondata", getData.toString());
                            JSONArray jsonArray = new JSONArray(jsondata);
                            JSONObject aItemObj=jsonArray.getJSONObject(0);

//                                //String code=aItemObj.getString("CODE");
//                                //String price=aItemObj.getString("MRP");
                                String qnty=aItemObj.getString("QNTY");
                                int qntychk=Integer.valueOf(qnty);
                                if (qntychk<0){
                                    HelpingLib.showmessage(mContext,"Stock out");
                                }else if (val>qntychk){
                                    HelpingLib.showmessage(mContext,"Insufficient quantity! please not order more than "+qntychk+" right now");
                                }else{
                                    int mrpchk=Integer.valueOf(mrp);
                                    int tPrice=val*mrpchk;
                                    ItemAddToCartDataModel itemDataModel=new ItemAddToCartDataModel(showroomId,itemCode,name,String.valueOf(val),mrp,String.valueOf(tPrice),"0");//0=normal 1=free
                                    HomeFragment.orderDB.add(itemDataModel);
                                    finish();
                                }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    private void setUpdatedVal(int val) {
        tvVal.setText(""+val);
    }

    private int  getCurrentVal() {
        int res=0;
        String value=tvVal.getText().toString();
        res=Integer.valueOf(value);
        return res;

    }
}
