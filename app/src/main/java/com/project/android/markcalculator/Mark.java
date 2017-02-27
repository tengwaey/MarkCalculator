package com.project.android.markcalculator;

import java.util.Date;
import java.util.UUID;


public class Mark {
    private UUID mId;
    private String mSem;
    private Date mDate;
    private int mSubjectamt;
    private String mGpa;

    public Mark(){
        this(UUID.randomUUID());
        //mId=UUID.randomUUID();
        //mDate = new Date();
    }

    public Mark(UUID id){
        mId = id;
        mDate = new Date();
    }

    public Date getDate(){
        return mDate;
    }

    public void setDate(Date date){
        mDate = date;
    }

    public int getSubjectamt(){
        return mSubjectamt;
    }

    public void setSubjectamt(int subjectamt){
        mSubjectamt = subjectamt;
    }

    public UUID getId(){
        return mId;
    }

    public String getSem(){
        return mSem;
    }

    public void setSem(String sem){
        mSem = sem;
    }

    public String getGpa(){
        return mGpa;
    }

    public void setGpa(String gpa){
        mGpa = gpa;
    }

}
