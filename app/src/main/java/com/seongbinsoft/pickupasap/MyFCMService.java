package com.seongbinsoft.pickupasap;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class MyFCMService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i("TAG", "onMessageReceived!");
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("ch1", "push channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this, "ch1");
        }else {
            builder = new NotificationCompat.Builder(this, "");
        }
        String notiTitle = "Title";         //알림정보가 없을때 기본제목
        String notiText = "message.....";   //알림정보가 없을때 기본글씨

        if (remoteMessage.getNotification() != null){
            notiTitle = remoteMessage.getNotification().getTitle();
            notiText = remoteMessage.getNotification().getBody();
        }

        builder.setSmallIcon(R.drawable.ic_baseline_notifications_active_24);
        builder.setContentTitle(notiTitle);
        builder.setContentText(notiText);

        Notification notification = builder.build();
        notificationManager.notify(10, notification);
    }
}
