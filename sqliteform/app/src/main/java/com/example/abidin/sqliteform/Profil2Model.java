package com.example.abidin.sqliteform;

/**
 * Created by Abidin on 21/02/2018.
 */

public class Profil2Model {

    private int id;
    private String namadosen;
    private String nidk;
    private String namaptn;

    public Profil2Model(){

    }

    public Profil2Model(String namadosen, String nidk, String namaptn){
        this.namadosen = namadosen;
        this.nidk = nidk;
        this.namaptn = namaptn;
    }
    public Profil2Model(int id,String namadosen, String nidk, String namaptn){
        this.id = id;
        this.namadosen = namadosen;
        this.nidk = nidk;
        this.namaptn = namaptn;
    }

    public String getNamaptn(){return namaptn;}

    public void setNamaptn(String namaptn) { this.namaptn = namaptn;}

    public String getNidk() {
        return nidk;
    }

    public void setNidk(String nidk) {
        this.nidk = nidk;
    }

    public String getNamedosen() {
        return namadosen;
    }

    public void setNamadosen(String namadosen) {
        this.namadosen = namadosen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
