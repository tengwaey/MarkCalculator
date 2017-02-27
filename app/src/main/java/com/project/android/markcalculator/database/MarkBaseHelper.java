package com.project.android.markcalculator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.android.markcalculator.database.MarkDbSchema.MarkTable;

/**
 * Created by Matt on 16/2/2017.
 */

public class MarkBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "markBase.db";

    public MarkBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + MarkTable.NAME + "(" +
                " _id integer primary key autoincrement, "+
                MarkTable.Cols.UUID+", "+
                MarkTable.Cols.SEM+", "+
                MarkTable.Cols.DATE+", "+
                MarkTable.Cols.SUBJECTAMT+", "+
                MarkTable.Cols.GPA+")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
