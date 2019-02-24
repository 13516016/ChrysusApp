package com.example.chrysus;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class PromoService extends IntentService {

    public PromoService() {
        super("Promo Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Promo", "Promo sent");
        String channelId = "chrysus-1";
        String channelName = "promo";
        Notification notification = new NotificationCompat.Builder(this)
                .setPriority(1)
                .setContentTitle("Chrysus Reminder")
                .setContentText("Your money needs to be regularly checked! Check it out now!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setChannelId(channelId)
                .build();

        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(12345, notification);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName , NotificationManager.IMPORTANCE_LOW);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(mChannel);
            mNotificationManager.notify(12345 , notification);
        }
    }
}
