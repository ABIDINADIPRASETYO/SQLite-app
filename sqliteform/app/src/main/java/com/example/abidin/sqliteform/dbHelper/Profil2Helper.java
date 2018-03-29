package com.example.abidin.sqliteform.dbHelper;

/**
 * Created by Abidin on 21/02/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.abidin.sqliteform.Profil2Model;

import java.util.ArrayList;
import static android.provider.BaseColumns._ID;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.Profil2Columns.NAMADOSEN;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.Profil2Columns.NIDK;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.Profil2Columns.NAMAPTN;
import static com.example.abidin.sqliteform.dbHelper.DataBaseContract.TABLE_PROFIL2;

public class Profil2Helper {

    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public Profil2Helper(Context context) {
        this.context = context;
    }

    public Profil2Helper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }


    public ArrayList<Profil2Model> getAllData() {
        Cursor cursor = database.query(TABLE_PROFIL2, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<Profil2Model> arrayList = new ArrayList<>();
        Profil2Model profil2Model;
        if (cursor.getCount() > 0) {
            do {
                profil2Model = new Profil2Model();
                profil2Model.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                profil2Model.setNamadosen(cursor.getString(cursor.getColumnIndexOrThrow(NAMADOSEN)));
                profil2Model.setNidk(cursor.getString(cursor.getColumnIndexOrThrow(NIDK)));
                profil2Model.setNamaptn(cursor.getString(cursor.getColumnIndexOrThrow(NAMAPTN)));
                arrayList.add(profil2Model);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Profil2Model profilModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NAMADOSEN, profilModel.getNamedosen());
        initialValues.put(NIDK, profilModel.getNidk());
        initialValues.put(NAMAPTN, profilModel.getNamaptn());
        return database.insert(TABLE_PROFIL2, null, initialValues);
    }


    public int update(Profil2Model profilModel) {
        ContentValues args = new ContentValues();
        args.put(NAMADOSEN, profilModel.getNamedosen());
        args.put(NIDK, profilModel.getNidk());
        args.put(NAMAPTN, profilModel.getNamaptn());
        return database.update(TABLE_PROFIL2, args, _ID + "= '" + profilModel.getId() + "'", null);
    }


    public int delete(int id) {
        return database.delete(TABLE_PROFIL2, _ID + " = '" + id + "'", null);
    }


    public Cursor getSearchData(String nama){
        String query = "SELECT * FROM "+ DataBaseContract.TABLE_PROFIL2 +" WHERE "+ DataBaseContract.Profil2Columns.NAMADOSEN +" LIKE '"+ nama +"%'";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

}
