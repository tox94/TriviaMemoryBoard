package com.zavrsni.unizg.fer.triviamemoryboard.db.result;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zavrsni.unizg.fer.triviamemoryboard.db.DbCreator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SqlResultRepository {
    private SQLiteOpenHelper dbHelper;
    private final Context ctx;

    public SqlResultRepository(Context ctx){
        dbHelper = new DbCreator(ctx);
        this.ctx = ctx;
    }

    public void close(){
        if(dbHelper != null){
            dbHelper.close();
        }
    }

    public ResultFromDb getResult(int id){
        ResultFromDb result = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try{

            Cursor cursor = db.rawQuery(
                "SELECT *"
                        + " FROM " + Result.ResultEntry.TABLE_NAME
                        + " WHERE " + Result.ResultEntry._ID + " = ?",
                new String[] {String.valueOf(id)});

            if (cursor.moveToFirst()){
                result = new ResultFromDb(
                        cursor.getInt(Result.getColumnPos(Result.ResultEntry._ID)),
                        cursor.getString(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_USER)),
                        cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_LEVEL)),
                        cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_TIME)),
                        cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_RESULT)),
                        cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_CORRECT_MOVES)),
                        cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_INCORRECT_MOVES)),
                        cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_TRY_ORDER)),
                        cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_CORRECT_QUESTIONS)),
                        cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_INCORRECT_QUESTIONS))
                );
            }
            cursor.close();
        }catch(Exception e){
            Log.d("DEBUG", "Error SQLResultRepository getResult");
        }finally{
            if (db != null){
                db.close();
            }
        }
        return result;
    }

    public ArrayList<ResultFromDb> getAllResults(int level){
        ArrayList<ResultFromDb> list = new ArrayList<>();
        SQLiteDatabase db = null;
        String str = String.valueOf(level);
        try{
            db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM result " + Result.ResultEntry.TABLE_NAME +
                    " WHERE " + Result.ResultEntry.COLUMN_NAME_LEVEL + " = ?"
                    /*" ORDER BY " + Result.ResultEntry.COLUMN_NAME_RESULT + " DESC, " +
                    Result.ResultEntry.COLUMN_NAME_TIME + " ASC, " +
                    Result.ResultEntry.COLUMN_NAME_USER + " ASC LIMIT 10"*/, new String[]{str});
            if (cursor.moveToFirst()){
                do {
                    list.add(new ResultFromDb(
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry._ID)),
                            cursor.getString(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_USER)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_LEVEL)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_TIME)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_RESULT)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_CORRECT_MOVES)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_INCORRECT_MOVES)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_TRY_ORDER)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_CORRECT_QUESTIONS)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_INCORRECT_QUESTIONS))
                    ));
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Log.d("DEBUG", "Error SQLResultRepository getAllResults");
        }finally{
            if (db != null)
                db.close();
        }
        return list;
    }

    public ArrayList<ResultFromDb> getAllResultsForUser(String user, int i){
        ArrayList<ResultFromDb> list = new ArrayList<>();
        SQLiteDatabase db = null;
        String lev = String.valueOf(i);
        Log.d("DEBUG", "User: " + user);
        try{
            db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM result " + Result.ResultEntry.TABLE_NAME +
                            " WHERE " + Result.ResultEntry.COLUMN_NAME_USER + " = ?" +
                            " AND " + Result.ResultEntry.COLUMN_NAME_LEVEL + " = ?"
                    /*" ORDER BY " + Result.ResultEntry.COLUMN_NAME_RESULT + " DESC, " +
                    Result.ResultEntry.COLUMN_NAME_TIME + " ASC, " +
                    Result.ResultEntry.COLUMN_NAME_USER + " ASC LIMIT 10"*/, new String[]{user, lev});
            if (cursor.moveToFirst()){
                do {
                    list.add(new ResultFromDb(
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry._ID)),
                            cursor.getString(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_USER)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_LEVEL)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_TIME)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_RESULT)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_CORRECT_MOVES)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_INCORRECT_MOVES)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_TRY_ORDER)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_CORRECT_QUESTIONS)),
                            cursor.getInt(Result.getColumnPos(Result.ResultEntry.COLUMN_NAME_INCORRECT_QUESTIONS))
                    ));
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Log.d("DEBUG", "Error SQLResultRepository getAllResultsForUser");
        }finally{
            if (db != null)
                db.close();
        }
        return list;
    }

    public ArrayList<String> getAllUsers(int i){
        ArrayList<String> list = new ArrayList<String>();
        String lev = String.valueOf(i);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try{
            Cursor cursor = db.rawQuery(
                    "SELECT " + Result.ResultEntry.COLUMN_NAME_USER
                            + " FROM " + Result.ResultEntry.TABLE_NAME +
                            " WHERE " + Result.ResultEntry.COLUMN_NAME_LEVEL + " = ?",
                    new String[]{lev});
            if (cursor.moveToFirst()){
                do{
                    list.add(cursor.getString(0));
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch(Exception e){
            Log.d("DEBUG", "Error SQLResultRepository getAllUsers");
        }finally{
            if (db != null){
                db.close();
            }
        }
        return list;
    }

    public void updateResult(ResultFromDb result){
        SQLiteDatabase db = null;
        try{
            db = dbHelper.getReadableDatabase();
            ContentValues val = new ContentValues();
            val.put(Result.ResultEntry.COLUMN_NAME_USER, result.getUser());
            val.put(Result.ResultEntry.COLUMN_NAME_LEVEL, result.getLevel());
            val.put(Result.ResultEntry.COLUMN_NAME_TIME, result.getTime());
            val.put(Result.ResultEntry.COLUMN_NAME_RESULT, result.getResult());
            val.put(Result.ResultEntry.COLUMN_NAME_CORRECT_MOVES, result.getCorMoves());
            val.put(Result.ResultEntry.COLUMN_NAME_INCORRECT_MOVES, result.getIncMoves());
            val.put(Result.ResultEntry.COLUMN_NAME_TRY_ORDER, result.getOrdNun());
            val.put(Result.ResultEntry.COLUMN_NAME_CORRECT_QUESTIONS, result.getCorQues());
            val.put(Result.ResultEntry.COLUMN_NAME_INCORRECT_QUESTIONS, result.getIncQues());
            db.update(Result.ResultEntry.TABLE_NAME, val, Result.ResultEntry._ID + "-?",
                    new String[]{String.valueOf(result.getId())});
        }catch(Exception e){
            Log.d("DEBUG", "Error SQLResultRepository updateResult");
        }finally{
            if (db != null)
                db.close();
        }
    }

    public void createResult(ResultFromDb result){
        dbHelper = new DbCreator(ctx);
        SQLiteDatabase db = null;
        try{
            db = dbHelper.getWritableDatabase();
            ContentValues val = new ContentValues();
            val.put(Result.ResultEntry._ID, result.getId());
            val.put(Result.ResultEntry.COLUMN_NAME_USER, result.getUser());
            val.put(Result.ResultEntry.COLUMN_NAME_LEVEL, result.getLevel());
            val.put(Result.ResultEntry.COLUMN_NAME_TIME, result.getTime());
            val.put(Result.ResultEntry.COLUMN_NAME_RESULT, result.getResult());
            val.put(Result.ResultEntry.COLUMN_NAME_CORRECT_MOVES, result.getCorMoves());
            val.put(Result.ResultEntry.COLUMN_NAME_INCORRECT_MOVES, result.getIncMoves());
            val.put(Result.ResultEntry.COLUMN_NAME_TRY_ORDER, result.getOrdNun());
            val.put(Result.ResultEntry.COLUMN_NAME_CORRECT_QUESTIONS, result.getCorQues());
            val.put(Result.ResultEntry.COLUMN_NAME_INCORRECT_QUESTIONS, result.getIncQues());
            db.insert(Result.ResultEntry.TABLE_NAME, null, val);
            SqlResultRepository sqlr = new SqlResultRepository(ctx);
            try
            {
                ResultFromDb res = sqlr.getResult(result.getId());
                Log.d("DEBUG", "Unio je " + res.getId());
            }catch (Exception e){
                Log.d("DEBUG", "Nije unio");
            }

        }catch(Exception e){
            Log.d("DEBUG", "Error SQLResultRepository createResult");
        }finally{
            if (db != null)
                db.close();
        }
    }

    public void deleteResult(ResultFromDb result){
        SQLiteDatabase db = null;
        try{
            db = dbHelper.getWritableDatabase();
            db.delete(Result.ResultEntry.TABLE_NAME,
                    Result.ResultEntry._ID + "=?",
                    new String[]{String.valueOf(result.getId())});
        }catch(Exception e){
            Log.d("DEBUG", "Error SQLResultRepository deleteResult");
        }finally{
            if (db != null)
                db.close();
        }
    }

}
