package com.prangroup.kazi.tastytreat.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prangroup.kazi.tastytreat.R;

public class SplashActivity extends AppCompatActivity {

    public static Activity activity;
    public static Context context;
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    public static SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String userID = "code";
    public static final String typee = "type";
    public static final String location = "location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activity = (Activity) this;
        context = (Context) this;

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
           sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
           String code=sharedpreferences.getString(userID,"code not found");
           String type=sharedpreferences.getString(typee,"code not found");
        if (!code.equalsIgnoreCase("code not found")){
            if (type.equalsIgnoreCase("1")){
                MainActivity.userid=code;
                Intent dashIntent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(dashIntent);
            }else if (type.equalsIgnoreCase("2")){
                DeliveryManActivity.userid=code;
                Intent delIntent=new Intent(SplashActivity.this,DeliveryManActivity.class);
                startActivity(delIntent);
            }

        }else {
            Intent LoginIntent=new Intent(SplashActivity.this,RegisterActivity.class);
            startActivity(LoginIntent);
        }


            }
        }, SPLASH_DISPLAY_LENGTH);

    }

}

