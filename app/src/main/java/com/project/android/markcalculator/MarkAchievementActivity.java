package com.project.android.markcalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by Matt on 22/2/2017.
 */

public class MarkAchievementActivity extends SingleFragmentActivity {

    public static final String EXTRA_MARK_ID="com.project.android.markcalculator.mark_id";

    public static Intent newIntent(Context packageContext, UUID markId){
        Intent intent = new Intent(packageContext, MarkAchievementActivity.class);
        intent.putExtra(EXTRA_MARK_ID, markId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        return new MarkAchievementFragment();
    }
}
