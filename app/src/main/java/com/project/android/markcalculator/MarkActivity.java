package com.project.android.markcalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

public class MarkActivity extends SingleFragmentActivity {

    private static final String EXTRA_MARK_ID="com.project.android.markcalculator.mark_id";

    //telling markfragment which item ID to display, passing ID as intent extra
    public static Intent newIntent(Context packageContext, UUID markId){
        Intent intent = new Intent(packageContext, MarkActivity.class);
        intent.putExtra(EXTRA_MARK_ID, markId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        UUID markId=(UUID)getIntent().getSerializableExtra(EXTRA_MARK_ID);
        return MarkFragment.newInstance(markId);
    }
}
