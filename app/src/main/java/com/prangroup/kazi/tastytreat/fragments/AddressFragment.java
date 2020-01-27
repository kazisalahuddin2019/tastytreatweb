package com.prangroup.kazi.tastytreat.fragments;


import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prangroup.kazi.tastytreat.R;

public class AddressFragment extends Fragment {
    View rootView;
    public static Activity mActivity;
    public static Context mContext;

    public AddressFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_address, container, false);
        mActivity = getActivity();
        mContext = getContext();

        return rootView;
    }


    private static void setPage() {


    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
