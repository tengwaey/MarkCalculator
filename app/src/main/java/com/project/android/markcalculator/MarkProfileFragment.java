package com.project.android.markcalculator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MarkProfileFragment extends Fragment{

    private Button mViewList;
    private Button mEditProfile;

    private EditText mProfileName;
    private EditText mProfileId;
    private EditText mProfileCourse;
    private EditText mProfileSem;

    private int counter=0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_mark_profile, container, false);

        mProfileName = (EditText)v.findViewById(R.id.profile_name);
        mProfileName.setEnabled(false);

        mProfileId = (EditText)v.findViewById(R.id.profile_id);
        mProfileId.setEnabled(false);

        mProfileCourse = (EditText)v.findViewById(R.id.profile_course);
        mProfileCourse.setEnabled(false);

        mProfileSem = (EditText)v.findViewById(R.id.profile_sem);
        mProfileSem.setEnabled(false);

        mViewList = (Button)v.findViewById(R.id.view_result);
        mViewList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent (getActivity(), MarkListActivity.class);
                startActivity(intent);
            }
        });

        mEditProfile = (Button)v.findViewById(R.id.edit_profile);
        mEditProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(counter%2==0){
                    mEditProfile.setText(R.string.edit_profile_2);

                    mProfileName.setEnabled(true);
                    mProfileId.setEnabled(true);
                    mProfileCourse.setEnabled(true);
                    mProfileSem.setEnabled(true);

                    counter++;
                }else {
                    mEditProfile.setText(R.string.edit_profile);

                    mProfileName.setEnabled(false);
                    mProfileId.setEnabled(false);
                    mProfileCourse.setEnabled(false);
                    mProfileSem.setEnabled(false);

                    counter++;
                }

                //animation
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = mEditProfile.getWidth() ;
                    int cy = mEditProfile.getHeight() ;
                    float radius = mEditProfile.getWidth() / 2;
                    Animator anim = ViewAnimationUtils.createCircularReveal(mEditProfile, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mEditProfile.setVisibility(View.INVISIBLE);
                            mEditProfile.setVisibility(View.VISIBLE);
                        }
                    });
                    anim.start();
                }
                else{
                    mEditProfile.setVisibility(View.INVISIBLE);
                    mEditProfile.setVisibility(View.VISIBLE);
                    mEditProfile.setText(R.string.edit_profile);
                }
            }
        });

        return v;
    }
}
