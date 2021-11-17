package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Item_DAO {
    private SQLiteDatabase database;
    private String[] columns = {
            ItemSQLiteOpenHelper.COLUNA_ID,
            ItemSQLiteOpenHelper.COLUNA_TITLE,
            ItemSQLiteOpenHelper.COLUNA_DESCRIPTION,
            ItemSQLiteOpenHelper.COLUNA_PRIORITY,
            ItemSQLiteOpenHelper.COLUNA_LOCATE,
            ItemSQLiteOpenHelper.COLUNA_PERSON
    };

    private ItemSQLiteOpenHelper sqliteOpenHelper;

    public Item_DAO(Context context) {
        sqliteOpenHelper = new ItemSQLiteOpenHelper(context);
    }

    public void open() throws SQLException {
        database = sqliteOpenHelper.getWritableDatabase();
    }

    public void close() {
        sqliteOpenHelper.close();
    }

    public void inserir(String title, String description, int priority,  String locate, String person) {
        ContentValues values = new ContentValues();
        values.put(ItemSQLiteOpenHelper.COLUNA_TITLE, title);
        values.put(ItemSQLiteOpenHelper.COLUNA_DESCRIPTION, description);
        values.put(ItemSQLiteOpenHelper.COLUNA_PRIORITY, String.valueOf(priority));
        values.put(ItemSQLiteOpenHelper.COLUNA_LOCATE, locate);
        values.put(ItemSQLiteOpenHelper.COLUNA_PERSON, person);
        database.insert(ItemSQLiteOpenHelper.TABELA, null, values);
    }

    public void alterar(long id, String title, String description, int priority, String locate, String person) {
        ContentValues values = new ContentValues();
        values.put(ItemSQLiteOpenHelper.COLUNA_TITLE, title);
        values.put(ItemSQLiteOpenHelper.COLUNA_DESCRIPTION, description);
        values.put(ItemSQLiteOpenHelper.COLUNA_PRIORITY, String.valueOf(priority));
        values.put(ItemSQLiteOpenHelper.COLUNA_LOCATE, locate);
        values.put(ItemSQLiteOpenHelper.COLUNA_PERSON, person);
        database.update(ItemSQLiteOpenHelper.TABELA, values,
                ItemSQLiteOpenHelper.COLUNA_ID + "=" + id, null);
    }

    public void apagar(long id) {
        database.delete(ItemSQLiteOpenHelper.TABELA,
                ItemSQLiteOpenHelper.COLUNA_ID
                        + " = " + id, null);
    }

    public Item buscar(long id) {
        Cursor cursor = database.query(ItemSQLiteOpenHelper.TABELA, columns, ItemSQLiteOpenHelper.COLUNA_ID + " = " +id, null, null, null ,null);
        cursor.moveToFirst();
        Item item = new Item();
        item.setId(cursor.getLong(0));
        item.setTitle(cursor.getString(1));
        item.setDescription(cursor.getString(2));
        item.setPriority(cursor.getInt(3));
        item.setUserEmail(cursor.getString(4));
        item.setUser(cursor.getString(5));
        cursor.close();
        return item;
    }

    public List<Item> getAll() {
        List<Item> items = new ArrayList<Item>();
        Cursor cursor = database.query(ItemSQLiteOpenHelper.TABELA, columns, null, null, null,null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Item item = new Item();
            item.setId(cursor.getLong(0));
            item.setTitle(cursor.getString(1));
            item.setDescription(cursor.getString(2));
            item.setPriority(cursor.getInt(3));
            item.setUserEmail(cursor.getString(4));
            item.setUser(cursor.getString(5));
            items.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }
}
