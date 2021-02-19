package com.example.plpp;

import java.security.PublicKey;

public class user {
    private int id;
    private String nik, nama, Password;

    public user(int id, String nama, String nik, String Password) {
        this.id = id;
        this.nik = nik;
        this.nama = nama;
        this.Password = Password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }

    public String getnik() {
        return nik;
    }

    public void setnik(String nik) {
        this.nik = nik;
    }

    public  String getPassword() {return Password;}

    public void setPassword(String password) {
        this.Password = password;
    }
}
