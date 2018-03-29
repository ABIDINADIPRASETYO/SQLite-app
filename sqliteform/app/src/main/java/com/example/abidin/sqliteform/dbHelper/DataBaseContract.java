package com.example.abidin.sqliteform.dbHelper;

import android.provider.BaseColumns;
/**
 * Created by Abidin on 20/02/2018.
 */

public class DataBaseContract {

    static String TABLE_PROFIL = "table_profil";

    static final class ProfilColumns implements BaseColumns {

        // ProfilModel nama
        static String NAMA = "nama";
        // ProfilModel nim
        static String NIM = "nim";
        // ProfilModel keminatan
        static  String KEMINATAN = "keminatan";

    }

    static String TABLE_PROFIL2 = "table_profil2";

    static final class Profil2Columns implements BaseColumns {

        // ProfilModel namadosen
        static String NAMADOSEN = "namadosen";
        // ProfilModel nidk
        static String NIDK = "nidk";
        // ProfilModel NamaPTN
        static  String NAMAPTN = "namaptn";

    }

}
