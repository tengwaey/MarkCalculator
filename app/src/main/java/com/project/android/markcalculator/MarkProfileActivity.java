package com.project.android.markcalculator;

import android.support.v4.app.Fragment;


public class MarkProfileActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
        return new MarkProfileFragment();
    }
}
