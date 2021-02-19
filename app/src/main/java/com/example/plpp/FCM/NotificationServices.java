package com.example.plpp.FCM;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.plpp.R;
import com.example.plpp.awal;
import com.example.plpp.login;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;



public class NotificationServices extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getData().get("message"));
        String click_action = remoteMessage.getNotification().getClickAction();
        Intent intent2 = new Intent(click_action);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    private void showNotification(String message) {

        String tittle = "Apps Notification";
        String subject = "Notifikasi Baru!";
        String body = message;




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Random random = new Random();
            int chID = random.nextInt(9999 - 1000) + 1000;
            String id = "channel_" + chID;
            CharSequence name = "Product";
            int importance = NotificationManager.IMPORTANCE_MAX;
            @SuppressLint("WrongConstant") NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            mChannel.setDescription(body);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            notificationManager.createNotificationChannel(mChannel);

            Intent intent1 = new Intent(getApplicationContext(), login.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 123, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), "id_product")
                    .setSmallIcon(R.drawable.logodj) //your app icon
                    .setChannelId(id)
                    .setContentTitle(subject)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(body))
                    .setContentIntent(pendingIntent)
                    .setNumber(1)
                    .setColor(255)
                    .setContentText(body)
                    .setWhen(System.currentTimeMillis());
            notificationManager.notify(1, notificationBuilder.build());
        } else {
            Random random = new Random();
            int chID = random.nextInt(9999 - 1000) + 1000;
            String id = "channel_" + chID;
            Intent intent = new Intent(getApplicationContext(), login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
            NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify = new NotificationCompat.Builder
                    (getApplicationContext())
                    .setContentTitle(tittle)
                    .setContentText(body)
                    .setContentTitle(subject)
                    .setChannelId(id)
                    .setSmallIcon(R.drawable.logodj)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(body))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();

            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            notify.defaults |= Notification.DEFAULT_SOUND;
            notify.defaults |= Notification.DEFAULT_VIBRATE;
            notif.notify(0, notify);
        }
    }
}