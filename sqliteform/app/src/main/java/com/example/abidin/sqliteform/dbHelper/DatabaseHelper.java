package com.example.abidin.sqliteform.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abidin on 20/02/2018.
 */

import static android.provider.BaseColumns._ID;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.Profil2Columns.NAMADOSEN;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.Profil2Columns.NAMAPTN;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.Profil2Columns.NIDK;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.ProfilColumns.NAMA;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.ProfilColumns.NIM;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.ProfilColumns.KEMINATAN;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.TABLE_PROFIL;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.TABLE_PROFIL2;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    public static String CREATE_TABLE_PROFIL = "create table " + TABLE_PROFIL +
            " (" + _ID + " integer primary key autoincrement, " +
            NAMA + " text not null, " +
            NIM + " text not null, " +
            KEMINATAN + " text not null);";


    //Table Ke 2
    public static String CREATE_TABLE_PROFIL2 = "create table " + TABLE_PROFIL2 +
            " (" + _ID + " integer primary key autoincrement, " +
            NAMADOSEN + " text not null, " +
            NIDK + " text not null, " +
            NAMAPTN + " text not null);";

    private static String DATABASE_NAME = "dbprofil";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PROFIL);
        db.execSQL(CREATE_TABLE_PROFIL2);
    }

    /*
    Method onUpgrade akan di panggil ketika terjadi perbedaan versi
    Gunakan method onUpgrade untuk melakukan proses migrasi data
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        Drop table tidak dianjurkan ketika proses migrasi terjadi dikarenakan data user akan hilang,
         */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFIL2);
        onCreate(db);
    }
}
