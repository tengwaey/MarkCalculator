package com.project.android.markcalculator;


import android.support.v4.app.Fragment;

//Hosting mark_list_fragment
public class MarkListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new MarkListFragment();
    }
}
