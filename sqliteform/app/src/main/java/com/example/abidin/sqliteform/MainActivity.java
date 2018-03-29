package com.example.abidin.sqliteform;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.abidin.sqliteform.dbHelper.Profil2Helper;
import com.example.abidin.sqliteform.dbHelper.ProfilHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText nama, nim, keminatan, namadosen, nidk,namaptn,editsearch;
    private Button tambah;
    private Button tambah2;
    private Button buttonsearch;

    private ProfilAdapter profilAdapter;
    private ProfilHelper profilHelper;
    private Profil2Adapter profil2Adapter;
    private Profil2Helper profil2Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        nama = (EditText) findViewById(R.id.edit_nama);
        nim = (EditText) findViewById(R.id.edit_nim);
        keminatan = (EditText) findViewById(R.id.edit_keminatan);
        tambah = (Button) findViewById(R.id.btn_tambah);
        buttonsearch = (Button) findViewById(R.id.buttonsearch);
        editsearch = (EditText) findViewById(R.id.editsearch);

        namadosen = (EditText) findViewById(R.id.edit_namadosen);
        nidk = (EditText) findViewById(R.id.edit_nidk);
        namaptn = (EditText) findViewById(R.id.edit_namaptn);
        tambah2 = (Button) findViewById(R.id.btn_tambah2);


        profilHelper = new ProfilHelper(this);
        profilAdapter = new ProfilAdapter(this);

        profil2Helper = new Profil2Helper(this);
        profil2Adapter = new Profil2Adapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllData();
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tambah.getText().equals("update")) {


                } else {
                    insertData();
                    getAllData();
                }
            }
        });

        //2

        getAllData2();
        tambah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tambah2.getText().equals("update")) {


                } else {
                    insertData2();
                    getAllData2();
                }
            }
        });
        buttonsearch.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        profilHelper.open();

                        Cursor res = profilHelper.getSearchData(editsearch.getText().toString());

                        if (res.getCount() == 0) {
                            showMessage("Error", "Noting Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Nim :" + res.getString(1) + "\n");
                            buffer.append("Nama :" + res.getString(2) + "\n");
                            buffer.append("Keminatan :" + res.getString(3) + "\n\n");
                        }

                        showMessage("Data Mahasiswa", buffer.toString());
                        profilHelper.close();
                    }
                }
        );

        buttonsearch.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        profil2Helper.open();

                        Cursor res = profil2Helper.getSearchData(editsearch.getText().toString());

                        if (res.getCount() == 0) {
                            showMessage("Error", "Noting Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :" + res.getString(0) + "\n");
                            buffer.append("Nidn :" + res.getString(1) + "\n");
                            buffer.append("Namadosen :" + res.getString(2) + "\n");
                            buffer.append("Nama ptn :" + res.getString(3) + "\n\n");
                        }

                        showMessage("Data Dosen", buffer.toString());
                        profil2Helper.close();
                    }
                }
        );


    }
    private void insertData() {
        profilHelper.open();
        ProfilModel profil = new ProfilModel(nama.getText().toString(), nim.getText().toString(), keminatan.getText().toString());
        profilHelper.insert(profil);
        profilHelper.close();
    }

    //2
    private void insertData2() {
        profil2Helper.open();
        Profil2Model profil2 = new Profil2Model(namadosen.getText().toString(), nidk.getText().toString(), namaptn.getText().toString());
        profil2Helper.insert(profil2);
        profil2Helper.close();
    }

    private void getAllData() {
        profilHelper.open();
        // Ambil semua data mahasiswa di database
        ArrayList<ProfilModel> profilModels = profilHelper.getAllData();
        profilHelper.close();
        profilAdapter.addItem(profilModels);
        recyclerView.setAdapter(profilAdapter);
    }

    //2
    private void getAllData2() {
        profil2Helper.open();
        // Ambil semua data mahasiswa di database
        ArrayList<Profil2Model> profil2Models = profil2Helper.getAllData();
        profil2Helper.close();
        profil2Adapter.addItem(profil2Models);
        recyclerView.setAdapter(profil2Adapter);
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}





