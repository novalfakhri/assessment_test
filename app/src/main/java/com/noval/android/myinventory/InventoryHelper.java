package com.noval.android.myinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Toshiba on 5/29/2017.
 */

public class InventoryHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyInventory.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Inventory";
    public static final String _ID = "_id";
    public static final String COLUMN_TANGGAL = "tanggal";
    public static final String COLUMN_NAMA_BARANG = "nama";
    public static final String COLUMN_HARGA = "harga";
    public static final String COLUMN_STOK = "stok";
    public static final String COLUMN_PEMASOK = "pemasok";

    public InventoryHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TANGGAL + " TEXT NOT NULL,"
                + COLUMN_NAMA_BARANG + " TEXT NOT NULL,"
                + COLUMN_HARGA + " INTEGER NOT NULL,"
                + COLUMN_STOK + " INTEGER NOT NULL,"
                + COLUMN_PEMASOK + " TEXT);";
        db.execSQL(QUERY_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertInventory(String tanggal, String nama, int harga, int stok, String pemasok) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TANGGAL, tanggal);
        contentValues.put(COLUMN_NAMA_BARANG, nama);
        contentValues.put(COLUMN_HARGA, harga);
        contentValues.put(COLUMN_STOK, stok);
        contentValues.put(COLUMN_PEMASOK, pemasok);

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateInventory(int id, String tanggal, String nama, int harga, int stok, String pemasok) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TANGGAL, tanggal);
        contentValues.put(COLUMN_NAMA_BARANG, nama);
        contentValues.put(COLUMN_HARGA, harga);
        contentValues.put(COLUMN_STOK, stok);
        contentValues.put(COLUMN_PEMASOK, pemasok);

        db.update(TABLE_NAME, contentValues, _ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }
    public Integer deleteInventory(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, _ID + " = ? ", new String[]{Integer.toString(id)});
    }

    public Cursor getInventory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                _ID + " = ? ", new String[]{Integer.toString(id)});
        return res;
    }

    public Cursor getAllInventories() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }


}
