package com.zavrsni.unizg.fer.triviamemoryboard.db.question;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zavrsni.unizg.fer.triviamemoryboard.db.DbCreator;

import java.util.ArrayList;

public class SqlQuestionRepository {
    private SQLiteOpenHelper dbHelper;
    private final Context ctx;

    public SqlQuestionRepository(Context ctx){
        dbHelper = new DbCreator(ctx);
        this.ctx = ctx;
    }

    public void close(){
        if(dbHelper != null){
            dbHelper.close();
        }
    }

    public QuestionFromDb getQuestion(int id){
        QuestionFromDb question = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery(
                    "SELECT *"
                        + " FROM " + Question.QuestionEntry.TABLE_NAME
                        + " WHERE " + Question.QuestionEntry._ID + " = ?",
            new String[] {String.valueOf(id)});
            Log.d(String.valueOf(id), "Našo sam " + id);

            if (cursor.moveToFirst()){
                question = new QuestionFromDb(
                        cursor.getInt(Question.getColumnPos(Question.QuestionEntry._ID)),
                        cursor.getString(Question.getColumnPos(Question.QuestionEntry.COLUMN_NAME_TEXT)),
                        cursor.getString(Question.getColumnPos(Question.QuestionEntry.COLUMN_NAME_ANSWER_ONE)),
                        cursor.getString(Question.getColumnPos(Question.QuestionEntry.COLUMN_NAME_ANSWER_TWO)),
                        cursor.getString(Question.getColumnPos(Question.QuestionEntry.COLUMN_NAME_ANSWER_THREE)),
                        cursor.getString(Question.getColumnPos(Question.QuestionEntry.COLUMN_NAME_ANSWER_FOUR)),
                        cursor.getString(Question.getColumnPos(Question.QuestionEntry.COLUMN_NAME_ANSWER_CORRECT))
                );
            }
            cursor.close();
        }catch(Exception e){
            Log.d("DEBUG", "Error SQLQuestionRepository getQuestion");
        }finally{
            if (db != null){
                db.close();
            }
        }
        return question;
    }

    public void updateQuestion(QuestionFromDb question){
        SQLiteDatabase db = null;
        try{
            db = dbHelper.getReadableDatabase();
            ContentValues val = new ContentValues();
            val.put(Question.QuestionEntry.COLUMN_NAME_TEXT, question.getQuestion());
            val.put(Question.QuestionEntry.COLUMN_NAME_ANSWER_ONE, question.getAnsA());
            val.put(Question.QuestionEntry.COLUMN_NAME_ANSWER_TWO, question.getAnsB());
            val.put(Question.QuestionEntry.COLUMN_NAME_ANSWER_THREE, question.getAnsC());
            val.put(Question.QuestionEntry.COLUMN_NAME_ANSWER_FOUR, question.getAnsD());
            val.put(Question.QuestionEntry.COLUMN_NAME_ANSWER_CORRECT, question.getCorrect());
            db.update(Question.QuestionEntry.TABLE_NAME, val, Question.QuestionEntry._ID + "-?",
                    new String[]{String.valueOf(question.getId())});
        }catch(Exception e){
            Log.d("DEBUG", "Error SQLQuestionRepository updateQuestion");
        }finally{
            if (db != null)
                db.close();
        }
    }

    public void createQuestion(QuestionFromDb question){
        dbHelper = new DbCreator(ctx);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try{
            ContentValues val = new ContentValues();
            val.put(Question.QuestionEntry._ID, question.getId());
            val.put(Question.QuestionEntry.COLUMN_NAME_TEXT, question.getQuestion());
            val.put(Question.QuestionEntry.COLUMN_NAME_ANSWER_ONE, question.getAnsA());
            val.put(Question.QuestionEntry.COLUMN_NAME_ANSWER_TWO, question.getAnsB());
            val.put(Question.QuestionEntry.COLUMN_NAME_ANSWER_THREE, question.getAnsC());
            val.put(Question.QuestionEntry.COLUMN_NAME_ANSWER_FOUR, question.getAnsD());
            val.put(Question.QuestionEntry.COLUMN_NAME_ANSWER_CORRECT, question.getCorrect());
            db.insert(Question.QuestionEntry.TABLE_NAME, null, val);
        }catch(Exception e){
            Log.d("DEBUG", "Error SQLQuestionRepository createQuestion");
        }finally{
            if (db != null)
                db.close();
        }
    }

    public void deleteQuestion(QuestionFromDb question){
        SQLiteDatabase db = null;
        try{
            db = dbHelper.getWritableDatabase();
            db.delete(Question.QuestionEntry.TABLE_NAME,
                    Question.QuestionEntry._ID + "=?",
                    new String[]{String.valueOf(question.getId())});
        }catch(Exception e){
            Log.d("DEBUG", "Error SQLQuestionRepository deleteQuestion");
        }finally{
            if (db != null)
                db.close();
        }
    }
}
