package com.project.android.markcalculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Matt on 22/2/2017.
 */

public class MarkAchievementFragment extends Fragment{
    private Mark mMark;
    private CheckBox achievement1;
    private CheckBox achievement2;
    private CheckBox achievement3;
    private CheckBox achievement4;
    private CheckBox achievement5;
    private CheckBox achievement6;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID markId=(UUID)getActivity().getIntent().getSerializableExtra(MarkAchievementActivity.EXTRA_MARK_ID);
        mMark = MarkLab.get(getActivity()).getMark(markId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_achievement, container, false);

        achievement1 = (CheckBox)v.findViewById(R.id.check1);
        achievement1.setClickable(false);
        achievement2 = (CheckBox)v.findViewById(R.id.check2);
        achievement2.setClickable(false);
        achievement3 = (CheckBox)v.findViewById(R.id.check3);
        achievement3.setClickable(false);
        achievement4 = (CheckBox)v.findViewById(R.id.check4);
        achievement4.setClickable(false);
        achievement5 = (CheckBox)v.findViewById(R.id.check5);
        achievement5.setClickable(false);
        achievement6 = (CheckBox)v.findViewById(R.id.check6);
        achievement6.setClickable(false);

        if(Double.parseDouble(mMark.getGpa())>3.5){
            achievement1.setChecked(true);
            achievement3.setChecked(true);
            achievement4.setChecked(true);

            achievement1.setText(mMark.getDate().toString());
            achievement3.setText(mMark.getDate().toString());
            achievement4.setText(mMark.getDate().toString());

        }

        if(Integer.parseInt(mMark.getSem())>5){
            achievement2.setChecked(true);
            achievement4.setChecked(true);

            achievement2.setText(mMark.getDate().toString());
            achievement4.setText(mMark.getDate().toString());

        }

        if(Double.parseDouble(mMark.getGpa())>2.5){
            achievement3.setChecked(true);

            achievement3.setText(mMark.getDate().toString());

        }

        if(Integer.parseInt(mMark.getSem())>4){
            achievement4.setChecked(true);

            achievement4.setText(mMark.getDate().toString());

        }

        if(mMark.getSubjectamt()>2){
            achievement2.setChecked(true);

            achievement2.setText(mMark.getDate().toString());

        }

        return v;
    }
}
