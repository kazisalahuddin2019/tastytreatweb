package com.prangroup.kazi.tastytreat.fragments;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.activity.MainActivity;
import com.prangroup.kazi.tastytreat.adapter.ItemListAdapter;
import com.prangroup.kazi.tastytreat.adapter.OrderListAdapter;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;

import static com.prangroup.kazi.tastytreat.activity.MainActivity.mAactivity;

public class CartFragment extends Fragment {
    View rootView;
    public static Activity mActivity;
    public static Context mContext;
    public static ListView orderlist;
    public static Button btnaddtocart,tPrice;
    public static int totalProductPrice;
    public CartFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_addtocart, container, false);
        mActivity = getActivity();
        mContext = getContext();
        orderlist = (ListView)rootView.findViewById(R.id.orderlist);
        btnaddtocart = (Button) rootView.findViewById(R.id.btnaddtocart);
        tPrice = (Button)rootView.findViewById(R.id.tPrice);
        int cartSize=HomeFragment.orderDB.size();
        HomeFragment.locll.setVisibility(View.GONE);
        Log.e("cartSize",""+cartSize);
        if (cartSize<1){
            HelpingLib.showmessage(mContext,"No order  found");

        }else{
            setupOrderCart();
            setPrice();
        }
        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fm=new OrderProcessingFragment();
                MainActivity.ft = getActivity().getSupportFragmentManager().beginTransaction();
                MainActivity.ft.replace(R.id.content, fm);
                MainActivity.ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                MainActivity.ft.addToBackStack(null);
                MainActivity.ft.commit();
            }
        });
        return rootView;
    }

    public static void setupOrderCart() {
        OrderListAdapter orderListAdapter=new OrderListAdapter(mContext,mAactivity,HomeFragment.orderDB);
        orderlist.setAdapter(orderListAdapter);
    }


    public static void setPrice() {
        int tAmount=0;
        totalProductPrice=0;
        for (int i=0;i<HomeFragment.orderDB.size();i++){
            int price=Integer.valueOf(HomeFragment.orderDB.get(i).getTotalprice());
            tAmount=tAmount+price;
        }
        totalProductPrice=tAmount;
        tPrice.setText("Total: "+tAmount);

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
