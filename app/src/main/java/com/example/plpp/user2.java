package com.example.plpp;

public class user2 {
    private int id;
    private String npm, Nama, Password;


    public user2(int id, String Nama, String npm, String Password) {
        this.id = id;
        this.npm = npm;
        this.Nama = Nama;
        this.Password = Password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        this.Nama = nama;
    }

    public String getnpm() {
        return npm;
    }

    public void setnpm(String npm) {
        this.npm = npm;
    }

    public String getPassword() {return Password;}

    public void setPassword(String Password ) {this.Password = Password;}

}
