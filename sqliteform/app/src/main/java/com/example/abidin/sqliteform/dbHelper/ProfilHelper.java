package com.example.abidin.sqliteform.dbHelper;

/**
 * Created by Abidin on 20/02/2018.
 */

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.abidin.sqliteform.ProfilModel;

import java.util.ArrayList;
import static android.provider.BaseColumns._ID;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.ProfilColumns.NAMA;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.ProfilColumns.NIM;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.ProfilColumns.KEMINATAN;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.TABLE_PROFIL;

public class ProfilHelper {
    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public ProfilHelper(Context context) {
        this.context = context;
    }

    public ProfilHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }


    public ArrayList<ProfilModel> getAllData() {
        Cursor cursor = database.query(TABLE_PROFIL, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<ProfilModel> arrayList = new ArrayList<>();
        ProfilModel profilModel;
        if (cursor.getCount() > 0) {
            do {
                profilModel = new ProfilModel();
                profilModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                profilModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                profilModel.setNim(cursor.getString(cursor.getColumnIndexOrThrow(NIM)));
                profilModel.setKeminatan(cursor.getString(cursor.getColumnIndexOrThrow(KEMINATAN)));
                arrayList.add(profilModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(ProfilModel profilModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NAMA, profilModel.getName());
        initialValues.put(NIM, profilModel.getNim());
        initialValues.put(KEMINATAN, profilModel.getKeminatan());
        return database.insert(TABLE_PROFIL, null, initialValues);
    }


    public int update(ProfilModel profilModel) {
        ContentValues args = new ContentValues();
        args.put(NAMA, profilModel.getName());
        args.put(NIM, profilModel.getNim());
        args.put(KEMINATAN, profilModel.getKeminatan());
        return database.update(TABLE_PROFIL, args, _ID + "= '" + profilModel.getId() + "'", null);
    }


    public int delete(int id) {
        return database.delete(TABLE_PROFIL, _ID + " = '" + id + "'", null);
    }

    public Cursor getSearchData(String nama){
        String query = "SELECT * FROM "+ DataBaseContract.TABLE_PROFIL +" WHERE "+ DataBaseContract.ProfilColumns.NAMA +" LIKE '"+ nama +"%'";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }





}
