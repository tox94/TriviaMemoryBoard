package com.zavrsni.unizg.fer.triviamemoryboard.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zavrsni.unizg.fer.triviamemoryboard.db.question.Question;
import com.zavrsni.unizg.fer.triviamemoryboard.db.result.Result;

import static com.zavrsni.unizg.fer.triviamemoryboard.db.question.Question.SQL_DELETE_QUESTION;

public class DbCreator extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tmb.db";
    private Context ctx;

    public DbCreator (Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = ctx;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Question.SQL_CREATE_QUESTION);
        db.execSQL(Result.SQL_CREATE_RESULT);

        DbInit init = new DbInit(ctx);
        init.initAll(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
