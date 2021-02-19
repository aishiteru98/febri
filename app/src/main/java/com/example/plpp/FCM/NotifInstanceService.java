package com.example.plpp.FCM;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.plpp.R;
import com.example.plpp.SharedPrefManager1;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;


public class NotifInstanceService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String recent_token) {
      FirebaseInstanceId.getInstance().getToken();
      SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(getString(R.string.FCM_TOKEN), recent_token);
      editor.commit();

    }
}
