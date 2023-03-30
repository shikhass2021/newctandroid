//to send a push from firebase console

package com.example.clevertaptestapp;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.pushnotification.NotificationInfo;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.util.Map;
import java.util.Random;


public class MyFcmMessageListenerService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message){
        try {
            if (message.getData().size() > 0) {
                Bundle extras = new Bundle();
                for (Map.Entry<String, String> entry : message.getData().entrySet()) {
                    extras.putString(entry.getKey(), entry.getValue());
                }

                NotificationInfo info = CleverTapAPI.getNotificationInfo(extras);

                //to get notification from forground and background

                // to
                boolean flag = info.fromCleverTap;
                if (!flag) {


// if payload from Firebse
                    Log.d("FCM data", "FCM data: " + new Gson().toJson(message));   // to print payload
                    Log.d("img", "img: " + message.getNotification().getImageUrl());
                    Log.d("title", "title = [" + message.getNotification().getTitle() + "]");
                    Log.d("message", "message = [" + message.getNotification().getBody() + "]");

                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    int notificationId = new Random().nextInt(23);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "S69")


                            .setContentTitle(message.getNotification().getTitle()) //the "title" value you sent in your notification
                            .setContentText(message.getNotification().getBody()) //ditto
                            .setAutoCancel(true);  //dismisses the notification on click

                    notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());
                }

                else {

                    Log.d("Clevertap","custom push handling"+extras);
                    // not from CleverTap handle yourself or pass to another provider
                    CleverTapAPI.createNotification(getApplicationContext(), extras);



                }
            }
        } catch (Throwable t) {
            Log.d("MYFCMLIST", "Error parsing FCM message", t);
        }
    }
}

