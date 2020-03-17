package com.example.sqlitesv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SinhVienDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "qlsv.db";
    private static final String TABLE_NAME = "SV";
    private static final String id = "id";
    private static final String ten = "ten";
    private static final String ns = "ns";
    private static final String truong = "truong";
    private static final String gioiTinh = "gioiTinh";
    private static final String soThich = "soThich";
    public SinhVienDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS SV(id INTEGER PRIMARY KEY AUTOINCREMENT, ten TEXT, ns TEXT, truong TEXT, gioiTinh INTEGER, soThich TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void themSV(SinhVienModel svMoi){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ten, svMoi.getHoTen());
        values.put(ns, svMoi.getNs());
        values.put(truong, svMoi.getTruong());
        values.put(gioiTinh, svMoi.getGioiTinh());
        values.put(soThich, svMoi.getSoThich());
        database.insert(TABLE_NAME, null, values);
        database.close();
    }
    void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
}
