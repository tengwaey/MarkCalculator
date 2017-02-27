package com.project.android.markcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.project.android.markcalculator.database.MarkBaseHelper;
import com.project.android.markcalculator.database.MarkCursorWrapper;
import com.project.android.markcalculator.database.MarkDbSchema;
import com.project.android.markcalculator.database.MarkDbSchema.MarkTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Matt on 14/2/2017.
 */

//managing storage
public class MarkLab {
    private static MarkLab sMarkLab;

    //private List<Mark> mMarks;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static MarkLab get(Context context){
        if(sMarkLab == null){
            sMarkLab = new MarkLab(context);
        }
        return sMarkLab;
    }

    private MarkLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new MarkBaseHelper(mContext).getWritableDatabase();

        //mMarks = new ArrayList<>();
    }

    public List<Mark> getMarks(){
        //return mMarks;
        //return new ArrayList<>();
        List<Mark> marks = new ArrayList<>();

        MarkCursorWrapper cursor = queryMarks(null,null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                marks.add(cursor.getMark());
                cursor.moveToNext();
            }
        }finally{
            cursor.close();
        }
        return marks;
    }

    public Mark getMark(UUID id){
//        for(Mark mark : mMarks){
//            if(mark.getId().equals(id)){
//                return mark;
//            }
//        }
        //return null;
        MarkCursorWrapper cursor = queryMarks(
                MarkTable.Cols.UUID+" = ?",
                new String[]{id.toString()}
        );
        try{
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMark();
        }finally{
            cursor.close();
        }
    }

    //add new mark
    public void addMark(Mark m){
        //mMarks.add(m);
        ContentValues values = getContentValues(m);
        mDatabase.insert(MarkTable.NAME,null,values);
    }

    //delete mark
    public int deleteMark(Mark m){
        //mMarks.remove(m);
        String uuidString = m.getId().toString();
        return mDatabase.delete(
                MarkTable.NAME,
                MarkTable.Cols.UUID + " = ?",
                new String[] {uuidString}
        );
    }

    public void updateMark(Mark mark){
        String uuidString = mark.getId().toString();
        ContentValues values = getContentValues(mark);

        mDatabase.update(MarkTable.NAME, values, MarkTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    private static ContentValues getContentValues (Mark mark){
        ContentValues values = new ContentValues();
        values.put(MarkTable.Cols.UUID, mark.getId().toString());
        values.put(MarkTable.Cols.SEM, mark.getSem());
        values.put(MarkTable.Cols.DATE, mark.getDate().getTime());
        values.put(MarkTable.Cols.SUBJECTAMT, ""+mark.getSubjectamt());
        values.put(MarkTable.Cols.GPA, mark.getGpa());

        return values;
    }

    private MarkCursorWrapper queryMarks(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                MarkTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new MarkCursorWrapper(cursor);
    }
}
