package com.example.sqliteex.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "USER";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "USER";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String DES = "des";

    public DBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE" + TABLE_NAME +"("+
                ID+" INTEGER PRIMARY KEY, " +
                NAME + "TEXT, "+
                DES + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {
        String sql  = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
    public String addUSER(String user , String gender , String des){
        SQLiteDatabase  db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,user);
        contentValues.put(GENDER,gender);
        contentValues.put(DES,des);
        long isAdd = db.insert(TABLE_NAME,null,contentValues);
        if (isAdd == -1){
            return "ADD Failt";
        }
        db.close();
        return "Add Success";
    }
    public String updateUser(int id, String user, String gender , String des){
        SQLiteDatabase  db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,user);
        contentValues.put(GENDER,gender);
        contentValues.put(DES,des);
        int isUpdate = db.update(TABLE_NAME,contentValues,ID+" =? ", new String[] {id+""});
        if (isUpdate > 0){
            return "Update success";
        }
        db.close();
        return "Update fail";
    }
    public String deleteUser (int id){
        SQLiteDatabase  db = this.getWritableDatabase();
        int isDelete = db.delete(TABLE_NAME,ID + " =? ",  new String[] {id+""});
        if (isDelete > 0){
            return "Delete Success"
        }
        db.close();
        return "Detele Fail";
    }
    public Cursor getAllUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql  = "SELECT * FROM" + TABLE_NAME;
        Cursor c = db.rawQuery(sql,null);
        return c;
    }
}
