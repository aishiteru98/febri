package com.example.plpp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager1 {
    private static final String TAG_TOKEN = "tagtoken";
    private static SharedPrefManager1 mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME1 = "darmajayaa";
    private static final String KEY_Nama = "keyusername";
    private static final String KEY_npm = "keynpm";
    private static final String KEY_ID = "keyid";
    private static final String KEY_token = "keytoken";
    private static final String KEY_PW = "keyPassword";

    private SharedPrefManager1(Context context) {
        mCtx = context;
    }
    public static synchronized SharedPrefManager1 getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager1(context);
        }
        return mInstance;
    }
    //this method will save the device token to shared preferences

    public void userLogin1(user2 User1) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, User1.getId());
        editor.putString(KEY_Nama, User1.getNama());
        editor.putString(KEY_npm, User1.getnpm());
                editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_Nama, null) != null;
    }

    //this method will give the logged in user
    public user2 getUser1() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        return new user2(
                sharedPreferences.getInt(KEY_ID, 1),
                sharedPreferences.getString(KEY_Nama, null),
                sharedPreferences.getString(KEY_npm, null),
                sharedPreferences.getString(KEY_PW, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, awal.class));
    }
}
