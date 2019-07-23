package com.example.android.booking;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Booking_database_kgp.db";
    public static final String TABLE_NAME = "BOOKING_TABLE";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AUDITORIUM";
    public static final String COL_4 = "SLOT";
    public static final String COL_5="DATE";
    public static final String COL_6="PHONE";
    public static final String COL_7="STATUS";
    public database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AUDITORIUM TEXT,SLOT TEXT, DATE TEXT,PHONE TEXT,STATUS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String surname,String marks,String date,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String rej="PENDING";
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        contentValues.put(COL_5,date);
        contentValues.put(COL_6,phone);
        contentValues.put(COL_7,rej);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getrow(String sname)
    {
        String query = "select * from " + TABLE_NAME + " where "+ COL_2 + " = '" + sname + "'";
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cur = sql.rawQuery(query, null);
        return cur;
    }
    public Cursor getrow2(int i)
    {    i=(Integer)i;
        String query = "select * from " + TABLE_NAME + " where "+ COL_1 + " = '" + i + "'";
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cur = sql.rawQuery(query, null);
        return cur;
    }
    public void update_status(int i)
    {
        i=(Integer)i;
        ContentValues cv = new ContentValues();
        cv.put(COL_7,"approved");
        SQLiteDatabase sql = this.getReadableDatabase();

        sql.update(TABLE_NAME, cv, "ID="+i, null);
      // sql.execSQL("UPDATE "+TABLE_NAME+" SET STATUS='approved' WHERE id=6 ");
    }
    public void update_statusr(int i)
    {
        i=(Integer)i;
        ContentValues cv = new ContentValues();
        cv.put(COL_7,"rejected");
        SQLiteDatabase sql = this.getReadableDatabase();

        sql.update(TABLE_NAME, cv, "ID="+i, null);
        // sql.execSQL("UPDATE "+TABLE_NAME+" SET STATUS='approved' WHERE id=6 ");
    }

    public Cursor getall()
    {
        String query = "select * from " + TABLE_NAME ;
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cur = sql.rawQuery(query, null);
        return cur;
    }
}
