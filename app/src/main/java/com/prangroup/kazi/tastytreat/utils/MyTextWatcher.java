package com.prangroup.kazi.tastytreat.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.prangroup.kazi.tastytreat.adapter.AutoCompleteAdapter;


/**
 * Created by CS-MIS-HW-Altaf on 4/3/2018.
 */

public class MyTextWatcher implements TextWatcher {
    private AutoCompleteAdapter lAdapter;
    public MyTextWatcher(AutoCompleteAdapter lAdapter) {
        this.lAdapter = lAdapter;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        Log.e("value",s.toString());
        if(s.toString().length() > 0)
            lAdapter.getFilter().filter(s.toString().toLowerCase());
    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s.toString().length() > 0)
            lAdapter.getFilter().filter(s.toString().toLowerCase());
    }

}
