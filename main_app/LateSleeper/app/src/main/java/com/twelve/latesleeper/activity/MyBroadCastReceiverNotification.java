package com.twelve.latesleeper.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.twelve.latesleeper.R;

public class MyBroadCastReceiverNotification extends BroadcastReceiver {
    private  static  final  String CHANNEL_ID = "sleep_time_notification";
    private  static  final  String CHANNEL_NAME = "sleep_time";
    private  static  final  String CHANNEL_DESC = "sleep_time_notification_attempt";


    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = (NotificationManager)context.getSystemService(NotificationManager.class);
            Intent myIntent = new Intent(context,AlarmClockWakeUpActivity.class);
            manager.createNotificationChannel(channel);
            NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pi = PendingIntent.getActivity(context,9,myIntent,0);
            Notification notification = new Notification.Builder(context,CHANNEL_ID)
                    .setContentTitle("TIME FOR BED")
                    .setContentText("Remember your goal!")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setContentIntent(pi).build(); //getNotification();
            notificationManager.notify(1, notification);
        }


    }
}
