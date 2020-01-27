package com.prangroup.kazi.tastytreat.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.prangroup.kazi.tastytreat.R;
import com.prangroup.kazi.tastytreat.interfaces.VolleyCallBack;
import com.prangroup.kazi.tastytreat.servertask.DeliveryManLoginOperation;
import com.prangroup.kazi.tastytreat.servertask.UserLoginOperation;
import com.prangroup.kazi.tastytreat.utils.HelpingLib;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {
    Button btnRegister,btnsubmit,btnlogin;
    private EditText etMobno,etotp,etid,etpw;
    public static Activity activity;
    public static Context context;
    public static String mobno,userid,otp,dbotp,id,pw;
    public LinearLayout llMobno,llOtp,laydelivery;
    public TextView tvdeliveryman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();
        tvdeliveryman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llMobno.setVisibility(View.GONE);
                laydelivery.setVisibility(View.VISIBLE);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
                }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegister();
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etotp.getText().toString().equalsIgnoreCase("")){
                    String useriderrtxt = getResources().getString(R.string.err_msg_otp);
                    etotp.setError(useriderrtxt);
                    return;
                }
                otp=etotp.getText().toString();
                if (dbotp.equalsIgnoreCase(otp)){
                    MainActivity.userid=userid;
                    writeToShared(userid,"1");
                    dashboard();
                }else{
                    HelpingLib.showmessage(context,"Invalid OTP");
                }
                //Log.e("verifyverify","verify");

            }
        });

    }

    private void writeToShared(String userid,String type) {
        SharedPreferences.Editor aEditor=SplashActivity.sharedpreferences.edit();
        aEditor.putString(SplashActivity.userID,userid);
        aEditor.putString(SplashActivity.typee,type);
        aEditor.commit();
    }

    private void dashboard() {
        Intent dashboardIntent=new Intent(context,MainActivity.class);
        startActivity(dashboardIntent);
        finish();
    }

    private void deliveryDashboard() {
        Intent dashboardIntent=new Intent(context,DeliveryManActivity.class);
        startActivity(dashboardIntent);
        finish();
    }



    private void initialize() {

        activity=(Activity)this;
        context=(Context)this;
        tvdeliveryman= (TextView) findViewById(R.id.tvdeliveryman);
        btnRegister= (Button) findViewById(R.id.btnRegister);
        etMobno= (EditText) findViewById(R.id.etMobno);
        etid= (EditText) findViewById(R.id.etid);
        etpw= (EditText) findViewById(R.id.etpw);
        btnsubmit= (Button) findViewById(R.id.btnsubmit);
        etotp=(EditText)findViewById(R.id.etotp);
        llMobno=(LinearLayout) findViewById(R.id.lay2);
        llOtp=(LinearLayout) findViewById(R.id.lay3);
        laydelivery=(LinearLayout) findViewById(R.id.laydelivery);
        btnlogin= (Button) findViewById(R.id.btnlogin);
    }

    private void handleLogin() {
            if(etid.getText().toString().equalsIgnoreCase("")){
                String useriderrtxt = getResources().getString(R.string.err_msg_mob_no);
                etid.setError(useriderrtxt);
                return;
        }

        if(etpw.getText().toString().equalsIgnoreCase("")){
            String useriderrtxt = getResources().getString(R.string.err_msg_mob_no);
            etpw.setError(useriderrtxt);
            return;
        }

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(activity, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                final String mToken = instanceIdResult.getToken();
                Log.e("Token",mToken);
                id=etid.getText().toString();
                pw=etpw.getText().toString();
                if (HelpingLib.isInternetConnected(context)){

                    DeliveryManLoginOperation.sendLogionData(id,pw,mToken, activity, context, new VolleyCallBack() {
                        @Override
                        public void onSuccess(JSONObject getData) {
                            Log.e("getData",getData.toString());
                            try {
                                String status=getData.getString("status");
                                if (status.equalsIgnoreCase("1")){
                                    userid=getData.getString("userid");
                                    DeliveryManActivity.userid=userid;
                                    writeToShared(userid,"2");
                                    deliveryDashboard();


                                }else{
                                    String msg=getData.getString("Message");
                                    HelpingLib.showmessage(context,msg);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
                else{
                    String intconnerr = getString(R.string.err_msg_inernet);
                    HelpingLib.showmessage(context,intconnerr);
                }
            }
        });



    }

    private void handleRegister() {
        if(etMobno.getText().toString().equalsIgnoreCase("")){
            String useriderrtxt = getResources().getString(R.string.err_msg_mob_no);
            etMobno.setError(useriderrtxt);
            return;
        }

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(activity, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                final String mToken = instanceIdResult.getToken();
                Log.e("Token",mToken);
                mobno=etMobno.getText().toString();
                if (HelpingLib.isInternetConnected(context)){

                    UserLoginOperation.sendLogionData(mobno,mToken, activity, context, new VolleyCallBack() {
                        @Override
                        public void onSuccess(JSONObject getData) {
                            Log.e("getData",getData.toString());
                            try {
                                String msg=getData.getString("Message");
                                if (msg.equalsIgnoreCase("Successful")){
                                    dbotp=getData.getString("otp");
                                    userid=getData.getString("userid");
                                    Log.e("otp",dbotp);
                                    llMobno.setVisibility(View.GONE);
                                    llOtp.setVisibility(View.VISIBLE);


                                }else{
                                    HelpingLib.showmessage(context,msg);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
                else{
                    String intconnerr = getString(R.string.err_msg_inernet);
                    HelpingLib.showmessage(context,intconnerr);
                }
            }
        });



    }

    @Override
    public void onBackPressed() {
        //logoutConformation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
        System.exit(0);
    }

}
