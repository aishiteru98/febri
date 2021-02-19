package com.example.plpp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class PushMessageServiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_message_service);
        FirebaseMessaging.getInstance().subscribeToTopic("sendmessage");
        FirebaseInstanceId.getInstance().getToken();
    }
}
