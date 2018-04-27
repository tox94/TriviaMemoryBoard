package com.zavrsni.unizg.fer.triviamemoryboard.db.question;

import android.provider.BaseColumns;

public class Question {
    private Question(){
        
    }
    
    private static final String TXT = " TEXT ";
    private static final String SEP = " , ";
    
    public static final String SQL_CREATE_QUESTION =
            "CREATE TABLE " + QuestionEntry.TABLE_NAME + " (" +
            QuestionEntry._ID + " INTEGER PRIMARY KEY, " + 
            QuestionEntry.COLUMN_NAME_TEXT + TXT + SEP + 
            QuestionEntry.COLUMN_NAME_ANSWER_ONE + TXT + SEP + 
            QuestionEntry.COLUMN_NAME_ANSWER_TWO + TXT + SEP +
            QuestionEntry.COLUMN_NAME_ANSWER_THREE + TXT + SEP +
            QuestionEntry.COLUMN_NAME_ANSWER_FOUR + TXT + SEP +
            QuestionEntry.COLUMN_NAME_ANSWER_CORRECT + TXT + " )";

    public static final String SQL_DELETE_QUESTION =
            "DROP TABLE IF EXISTS " + QuestionEntry.TABLE_NAME;

    public static int getColumnPos(String columnName){
        switch(columnName) {
            case QuestionEntry._ID:
                return 0;
            case QuestionEntry.COLUMN_NAME_TEXT:
                return 1;
            case QuestionEntry.COLUMN_NAME_ANSWER_ONE:
                return 2;
            case QuestionEntry.COLUMN_NAME_ANSWER_TWO:
                return 3;
            case QuestionEntry.COLUMN_NAME_ANSWER_THREE:
                return 4;
            case QuestionEntry.COLUMN_NAME_ANSWER_FOUR:
                return 5;
            case QuestionEntry.COLUMN_NAME_ANSWER_CORRECT:
                return 6;
            default:
                throw new RuntimeException(String.format("Invalid column nam: %s \nfor table %s",
                        columnName, QuestionEntry.TABLE_NAME));
        }
    }
    
    
    public static abstract class QuestionEntry implements BaseColumns{
        public static final String TABLE_NAME = "question";
        public static final String COLUMN_NAME_TEXT = "text";
        public static final String COLUMN_NAME_ANSWER_ONE = "ans1";
        public static final String COLUMN_NAME_ANSWER_TWO = "ans2";
        public static final String COLUMN_NAME_ANSWER_THREE = "ans3";
        public static final String COLUMN_NAME_ANSWER_FOUR = "ans4";
        public static final String COLUMN_NAME_ANSWER_CORRECT = "correct";
    }
}
