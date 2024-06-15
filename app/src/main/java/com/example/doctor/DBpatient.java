package com.example.doctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBpatient extends SQLiteOpenHelper {
    public DBpatient(Context context) {
        super(context, "Puserdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(pname TEXT primary key, age TEXT, address TEXT, number TEXT, payments TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Userdetails");

    }

    public Boolean insertpuserdata(String pname, String age, String address, String number, String payments) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pname", pname);
        contentValues.put("age", age);
        contentValues.put("address", address);
        contentValues.put("number", number);
        contentValues.put("payments", payments);

        long result = db.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updatepuserdata(String pname, String age, String address, String number, String payments) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("age", age);
        contentValues.put("address", address);
        contentValues.put("number", number);
        contentValues.put("payments", payments);
        Cursor Cursor = db.rawQuery("Select * from Userdetails where pname = ?", new String[]{pname});
        if (Cursor.getCount() > 0) {
            long result = db.update("Userdetails", contentValues, "pname=?", new String[]{pname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletepdata(String pname) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor Cursor = db.rawQuery("Select * from Userdetails where pname = ?", new String[]{pname});
        if (Cursor.getCount() > 0) {
            long result = db.delete("Userdetails", "pname=?", new String[]{pname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }


    }

    public Cursor getpdata ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Userdetails ", null);
        return cursor;



    }
}
