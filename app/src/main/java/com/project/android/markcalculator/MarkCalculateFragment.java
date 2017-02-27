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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.UUID;

public class MarkCalculateFragment extends Fragment{
    private Mark mMark;

    private EditText score1;
    private EditText score2;
    private EditText score3;
    private EditText score4;
    private EditText score5;
    private EditText score6;

    private EditText credit1;
    private EditText credit2;
    private EditText credit3;
    private EditText credit4;
    private EditText credit5;
    private EditText credit6;

    private Button mCalculate;

    double gpa;
    double finalgpa;
    int credittotal;
    double score[] = new double[6];
    int credit[] = new int[6];
    DecimalFormat df = new DecimalFormat("#.00");

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //receiving data
        UUID markId=(UUID)getActivity().getIntent().getSerializableExtra(MarkCalculateActivity.EXTRA_MARK_ID);
        mMark = MarkLab.get(getActivity()).getMark(markId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mark_calculate, container, false);

        //temp
        //mSubjectAmt = (TextView) v.findViewById(R.id.subject_amt);
        //mSubjectAmt.setText("" + mMark.getSubjectamt());


        score1 = (EditText)v.findViewById(R.id.score1);
        score2 = (EditText)v.findViewById(R.id.score2);
        score3 = (EditText)v.findViewById(R.id.score3);
        score4 = (EditText)v.findViewById(R.id.score4);
        score5 = (EditText)v.findViewById(R.id.score5);
        score6 = (EditText)v.findViewById(R.id.score6);

        score1.setVisibility(View.GONE);
        score2.setVisibility(View.GONE);
        score3.setVisibility(View.GONE);
        score4.setVisibility(View.GONE);
        score5.setVisibility(View.GONE);
        score6.setVisibility(View.GONE);

        credit1 = (EditText)v.findViewById(R.id.credit1);
        credit2 = (EditText)v.findViewById(R.id.credit2);
        credit3 = (EditText)v.findViewById(R.id.credit3);
        credit4 = (EditText)v.findViewById(R.id.credit4);
        credit5 = (EditText)v.findViewById(R.id.credit5);
        credit6 = (EditText)v.findViewById(R.id.credit6);

        credit1.setVisibility(View.GONE);
        credit2.setVisibility(View.GONE);
        credit3.setVisibility(View.GONE);
        credit4.setVisibility(View.GONE);
        credit5.setVisibility(View.GONE);
        credit6.setVisibility(View.GONE);

        if(mMark.getSubjectamt()>=1) {
            score1.setVisibility(View.VISIBLE);
            credit1.setVisibility(View.VISIBLE);
        }
        if(mMark.getSubjectamt()>=2) {
            score2.setVisibility(View.VISIBLE);
            credit2.setVisibility(View.VISIBLE);
        }
        if(mMark.getSubjectamt()>=3) {
            score3.setVisibility(View.VISIBLE);
            credit3.setVisibility(View.VISIBLE);
        }
        if(mMark.getSubjectamt()>=4) {
            score4.setVisibility(View.VISIBLE);
            credit4.setVisibility(View.VISIBLE);
        }
        if(mMark.getSubjectamt()>=5) {
            score5.setVisibility(View.VISIBLE);
            credit5.setVisibility(View.VISIBLE);
        }
        if(mMark.getSubjectamt()>=6) {
            score6.setVisibility(View.VISIBLE);
            credit6.setVisibility(View.VISIBLE);
        }

        mCalculate = (Button)v.findViewById(R.id.calculate_button);
        mCalculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    score[0] = Double.parseDouble(score1.getText().toString());
                    credit[0] = Integer.parseInt(credit1.getText().toString());
                }catch(NumberFormatException e){
                }
                try {
                    score[1] = Double.parseDouble(score2.getText().toString());
                    credit[1] = Integer.parseInt(credit2.getText().toString());
                }catch(NumberFormatException e){
                }
                try {
                    score[2] = Double.parseDouble(score3.getText().toString());
                    credit[2] = Integer.parseInt(credit3.getText().toString());
                }catch(NumberFormatException e){
                }
                try {
                    score[3] = Double.parseDouble(score4.getText().toString());
                    credit[3] = Integer.parseInt(credit4.getText().toString());
                }catch(NumberFormatException e){
                }
                try {
                    score[4] = Double.parseDouble(score5.getText().toString());
                    credit[4] = Integer.parseInt(credit5.getText().toString());
                }catch(NumberFormatException e){
                }
                try {
                    score[5] = Double.parseDouble(score6.getText().toString());
                    credit[5] = Integer.parseInt(credit6.getText().toString());
                }catch(NumberFormatException e){
                }

                calculateMark(score,credit);
                mMark.setGpa(df.format(finalgpa));
                MarkFragment.mGpa.setText(mMark.getGpa());
                Toast.makeText(getActivity()," Calculated!\nPress Back", Toast.LENGTH_SHORT).show();

                //animation
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = mCalculate.getWidth() / 2;
                    int cy = mCalculate.getHeight() / 2;
                    float radius = mCalculate.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(mCalculate, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mCalculate.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                }
                else{
                    mCalculate.setVisibility(View.INVISIBLE);
                }
            }
        });

        return v;
    }

    public void calculateMark(double score[],int credit[]){
        for (int i=0;i<6;i++){
           if (score[i]>=80){
               gpa=4.0*credit[i]+gpa;
               credittotal=credit[i]+credittotal;
           }else
            if(score[i]>=75){
                gpa=3.67*credit[i]+gpa;
                credittotal=credit[i]+credittotal;
            }else
            if(score[i]>=70){
                gpa=3.33*credit[i]+gpa;
                credittotal=credit[i]+credittotal;
            }else
            if(score[i]>=65){
                gpa=3.0*credit[i]+gpa;
                credittotal=credit[i]+credittotal;
            }else
            if(score[i]>=60){
                gpa=2.67*credit[i]+gpa;
                credittotal=credit[i]+credittotal;
            }else
            if(score[i]>=55){
                gpa=2.33*credit[i]+gpa;
                credittotal=credit[i]+credittotal;
            }else
            if(score[i]>=50){
                gpa=2.0*credit[i]+gpa;
                credittotal=credit[i]+credittotal;
            }else
            if(score[i]>=45){
                gpa=1.5*credit[i]+gpa;
                credittotal=credit[i]+credittotal;
            }else
            if(score[i]>=0){
                gpa=1.0*credit[i]+gpa;
                credittotal=credit[i]+credittotal;
            }
        }
        finalgpa=gpa/credittotal;
    }

    @Override
    public void onPause(){
        super.onPause();
        MarkLab.get(getActivity()).updateMark(mMark);
    }
}
