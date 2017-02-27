package com.project.android.markcalculator.database;

public class MarkDbSchema {
    public static final class MarkTable{
        public static final String NAME="marks";

        public static final class Cols{
            public static final String UUID="uuid";
            public static final String SEM="sem";
            public static final String DATE="date";
            public static final String SUBJECTAMT="subjectamt";
            public static final String GPA="gpa";
        }
    }
}
