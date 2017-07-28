package com.example.a15017470.ompm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 15017470 on 20/7/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ompm.db";
    private static final int DATABASE_VERSION = 5;
    private static final String TABLE_NAME = "money_table";
    private static final String COL1 = "_id";
    private static final String COL2 = "NAME";
    private static final String COL3 = "AMOUNT";
    private static final String COL4 = "DATE";
    private static final String COL5 = "NUMBER";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT," + COL3 + " TEXT," + COL4 + " TEXT," + COL5 + " TEXT )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insert(String name, String amount, String date, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, amount);
        contentValues.put(COL4, date);
        contentValues.put(COL5, number);
        long result = db.insert(TABLE_NAME, null, contentValues);
        Log.d("SQL Insert","" + result);
        db.close();
        return result;
    }

    public int update(Data data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL2, data.getName());
        values.put(COL3, data.getAmount());
        values.put(COL4, data.getDate());
        values.put(COL5, data.getNumber());
        String condition = COL1 + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_NAME, values, condition, args);
        db.close();
        return result;
    }

    public int deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COL1 + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NAME, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Data> getData(){
        ArrayList<Data> data = new ArrayList<Data>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do {
                data.add(new Data(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return data;
    }
}
