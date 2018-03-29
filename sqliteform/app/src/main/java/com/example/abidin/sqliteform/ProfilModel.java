package com.example.abidin.sqliteform;

/**
 * Created by Abidin on 20/02/2018.
 */

public class ProfilModel {


        private int id;
        private String name;
        private String nim;
        private String keminatan;

        public ProfilModel(){

        }

        public ProfilModel(String name, String nim, String keminatan){
            this.name = name;
            this.nim = nim;
            this.keminatan = keminatan;
        }
        public ProfilModel(int id,String name, String nim, String keminatan){
            this.id = id;
            this.name = name;
            this.nim = nim;
            this.keminatan = keminatan;
        }

        public String getKeminatan(){return keminatan;}

        public void setKeminatan(String keminatan) { this.keminatan = keminatan;}

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }





    }


