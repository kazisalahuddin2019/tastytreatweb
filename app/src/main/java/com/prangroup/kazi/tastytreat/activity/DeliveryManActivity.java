package com.prangroup.kazi.tastytreat.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.fragments.AddressFragment;
import com.prangroup.kazi.tastytreat.fragments.CartFragment;
import com.prangroup.kazi.tastytreat.fragments.DelCompleteFragment;
import com.prangroup.kazi.tastytreat.fragments.DelHomeFragment;
import com.prangroup.kazi.tastytreat.fragments.DelProgressFragment;
import com.prangroup.kazi.tastytreat.fragments.HomeFragment;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;


public class DeliveryManActivity extends AppCompatActivity {
    public static Activity mAactivity;
    public static Context mContext;
    public static  String userid;
    public static android.support.v4.app.FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryman);
        mAactivity=(Activity)this;
        mContext=(Context)this;

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        changeFragment(new DelHomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFragment(new DelHomeFragment());
                    return true;
                case R.id.navigation_cart:

                        changeFragment(new DelProgressFragment());
                        return true;

                case R.id.navigation_address:
                    changeFragment(new DelCompleteFragment());
                    return true;
            }
            return false;
        }

    };

    @SuppressLint("WrongConstant")
    public void changeFragment(Fragment fm){
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fm);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
    }




}
