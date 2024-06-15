package com.example.doctor;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmedi extends SQLiteOpenHelper {
    public DBmedi(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table Userdetails (mname Text primary key, amount TEXT, unitprice TEXT, expdate TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Userdetails");

    }
    public Boolean insertusermdata(String mname, String amount, String price, String expdate)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mname",mname);
        contentValues.put("amount",amount);
        contentValues.put("price",price);
        contentValues.put("expdate",expdate);

        long result = DB.insert("Userdetails", null, contentValues);
        if (result==1){
            return false;
        }else{
            return true;
        }

    }

    public Boolean updateusermdata(String mname, String amount, String price, String expdate) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        contentValues.put("price", price);
        contentValues.put("expdate", expdate);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where mname = ?", new String[]{mname});
        if (cursor.getCount() > 0)
        {
            long result = DB.update("Userdetails", contentValues, "mname=?", new String[]{mname});
            if (result == 1) {
                return false;
            } else {
                return true;
            }

        } else {
            return false;
        }
    }

        public Boolean deleteusermdata (String mname) {
            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("Select * from Userdetails where mname = ?", new String[]{mname});
            if (cursor.getCount() > 0) {

                long result = DB.delete("Userdetails", "mname=?", new String[]{mname});
                if (result == 1) {
                    return false;
                } else {
                    return true;
                }

            }else
            {
                return false;
            }

        }

    public Cursor getmdata () {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetils ", null);
        return cursor;

    }
    }
