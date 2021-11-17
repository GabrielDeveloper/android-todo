package com.example.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemSQLiteOpenHelper  extends SQLiteOpenHelper {
    public static final String TABELA = " Item ";
    public static final String COLUNA_ID = " id ";
    public static final String COLUNA_TITLE = " title ";
    public static final String COLUNA_DESCRIPTION = " description ";
    public static final String COLUNA_PRIORITY = " priority ";
    public static final String COLUNA_LOCATE = " locate ";
    public static final String COLUNA_PERSON = " person ";

    private static final String DATABASE_NAME = "items.db";

    private static final int DATABASE_VERSION = 1;

    private static final String CRIAR_BANCO = " create table "
            + TABELA + "("
            + COLUNA_ID + " integer primary key autoincrement , "
            + COLUNA_TITLE + " text not null , "
            + COLUNA_DESCRIPTION + " text not null, "
            + COLUNA_PRIORITY + " integer not null, "
            + COLUNA_LOCATE + " text not null, "
            + COLUNA_PERSON + " text not null);";
    public ItemSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL (CRIAR_BANCO);
    }
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db. execSQL (" DROP TABLE IF EXISTS " + TABELA );
        onCreate (db);
    }
}
