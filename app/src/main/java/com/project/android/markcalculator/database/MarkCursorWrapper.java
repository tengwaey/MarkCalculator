package com.project.android.markcalculator.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.project.android.markcalculator.Mark;
import com.project.android.markcalculator.database.MarkDbSchema.MarkTable;

import java.util.Date;
import java.util.UUID;


public class MarkCursorWrapper extends CursorWrapper {
    public MarkCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Mark getMark(){
        String uuidString = getString(getColumnIndex(MarkTable.Cols.UUID));
        String sem = getString(getColumnIndex(MarkTable.Cols.SEM));
        long date = getLong(getColumnIndex(MarkTable.Cols.DATE));
        int subjectamt = getInt(getColumnIndex(MarkTable.Cols.SUBJECTAMT));
        String gpa = getString(getColumnIndex(MarkTable.Cols.GPA));

        Mark mark = new Mark(UUID.fromString(uuidString));
        mark.setSem(sem);
        mark.setDate(new Date(date));
        mark.setSubjectamt(subjectamt);
        mark.setGpa(gpa);

        return mark;
    }
}
