package com.zavrsni.unizg.fer.triviamemoryboard.db.result;

import android.provider.BaseColumns;

public class Result {
    private Result(){
        
    }

    private static final String TXT = " TEXT";
    private static final String SEP = ", ";

    public static final String SQL_CREATE_RESULT =
            "CREATE TABLE " + ResultEntry.TABLE_NAME + " (" +
                    ResultEntry._ID + " INTEGER PRIMARY KEY" + SEP +
                    ResultEntry.COLUMN_NAME_USER + TXT + SEP +
                    ResultEntry.COLUMN_NAME_LEVEL + " INTEGER, " +
                    ResultEntry.COLUMN_NAME_TIME + " INTEGER, " +
                    ResultEntry.COLUMN_NAME_RESULT + " INTEGER, " +
                    ResultEntry.COLUMN_NAME_CORRECT_MOVES + " INTEGER, " +
                    ResultEntry.COLUMN_NAME_INCORRECT_MOVES + " INTEGER, " +
                    ResultEntry.COLUMN_NAME_TRY_ORDER + " INTEGER, " +
                    ResultEntry.COLUMN_NAME_CORRECT_QUESTIONS + " INTEGER, " +
                    ResultEntry.COLUMN_NAME_INCORRECT_QUESTIONS+ " INTEGER)";

    public static final String SQL_DELETE_RESULT=
            "DROP TABLE IF EXISTS " + ResultEntry.TABLE_NAME;

    public static int getColumnPos(String columnName){
        switch(columnName) {
            case ResultEntry._ID:
                return 0;
            case ResultEntry.COLUMN_NAME_USER:
                return 1;
            case ResultEntry.COLUMN_NAME_LEVEL:
                return 2;
            case ResultEntry.COLUMN_NAME_TIME:
                return 3;
            case ResultEntry.COLUMN_NAME_RESULT:
                return 4;
            case ResultEntry.COLUMN_NAME_CORRECT_MOVES:
                return 5;
            case ResultEntry.COLUMN_NAME_INCORRECT_MOVES:
                return 6;
            case ResultEntry.COLUMN_NAME_TRY_ORDER:
                return 7;
            case ResultEntry.COLUMN_NAME_CORRECT_QUESTIONS:
                return 8;
            case ResultEntry.COLUMN_NAME_INCORRECT_QUESTIONS:
                return 9;
            default:
                throw new RuntimeException(String.format("Invalid column name: %s \nfor table %s",
                        columnName, ResultEntry.TABLE_NAME));
        }
    }


    public static abstract class ResultEntry implements BaseColumns{
        public static final String TABLE_NAME = "result";
        public static final String COLUMN_NAME_USER = "user";
        public static final String COLUMN_NAME_LEVEL = "level";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_RESULT = "res";
        public static final String COLUMN_NAME_CORRECT_MOVES = "corMoves";
        public static final String COLUMN_NAME_INCORRECT_MOVES = "incMoves";
        public static final String COLUMN_NAME_TRY_ORDER = "ordNum";
        public static final String COLUMN_NAME_CORRECT_QUESTIONS = "corQues";
        public static final String COLUMN_NAME_INCORRECT_QUESTIONS = "incQues";
    }
}
