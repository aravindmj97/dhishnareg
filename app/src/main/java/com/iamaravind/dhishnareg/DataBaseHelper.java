package com.iamaravind.dhishnareg;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ARAVIND on 30-09-2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "eventDb.db";
    public static final String TABLE_NAME = "Event_Names";
    public static final String COL1 = "ID";
    public static final String COL2 = "Ename";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE "+ TABLE_NAME+"("+COL1+" INTEGER PRIMARY KEY ,"+COL2+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDb(String eid, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,eid);
        contentValues.put(COL2,name);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getdata(String eid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT Ename FROM "+TABLE_NAME+" WHERE ID = "+eid, null);
        return res;
    }
    public Cursor getdataa()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return res;
    }
    public Integer deletefrom (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,COL1+" = ?", new String[] {id});
    }
    public Integer deleteall()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "1", null);

    }
    public boolean updateto(String id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        long upresult = db.update(TABLE_NAME, contentValues, COL1 +" = ?", new String[] {id});
        if(upresult == -1)
            return false;
        else
            return true;
    }
}
