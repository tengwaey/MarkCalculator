package com.project.android.markcalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by Matt on 16/2/2017.
 */

public class MarkCalculateActivity extends SingleFragmentActivity{
    public static final String EXTRA_MARK_ID="com.project.android.markcalculator.mark_id";

    //telling markcalculatefragment which item ID to display, passing ID as intent extra
    public static Intent newIntent(Context packageContext, UUID markId){
        Intent intent = new Intent(packageContext, MarkCalculateActivity.class);
        intent.putExtra(EXTRA_MARK_ID, markId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return new MarkCalculateFragment();
    }
}

