package com.example.plpp;

public class user1  {
    private int id;
    private String npm, name, token;


    public user1 (int id, String nama, String npm, String token) {
        this.id = id;
        this.npm = npm;
        this.name = nama;
        this.token= token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nama) {
        this.name = nama;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public  String getToken () {
        return  token;
    }

    public void setToken (String token){
        this.token = token;
    }

}
