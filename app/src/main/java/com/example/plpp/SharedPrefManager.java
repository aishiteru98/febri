package com.example.plpp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "darmajayaa";
    private static final String KEY_nama = "keyusername";
    private static final String KEY_nik = "keynik";
    private static final String KEY_ID = "keyid";
    private static final  String KEY_Token = "keytoken";
    private static final String KEY_PSW = "keypsw";
    private static SharedPrefManager mInstance;
    private static Context ctx;

    private SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will store the user data in shared preferences
    public void userLogin(user user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_nama, user.getnama());
        editor.putString(KEY_nik, user.getnik());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_nama, null) != null;
    }

    //this method will give the logged in user
    public user getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        sharedPreferences.getString(KEY_PSW,null);
        return new user(
                sharedPreferences.getInt(KEY_ID, 1),
                sharedPreferences.getString(KEY_nama, null),
                sharedPreferences.getString(KEY_nik, null),
                sharedPreferences.getString(KEY_PSW,null)
        );

    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, awal.class));
    }
}

