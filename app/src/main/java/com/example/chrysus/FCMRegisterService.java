package com.example.chrysus;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.chrysus.util.ConfigReader;
import com.example.chrysus.util.FirebaseAuthWrapper;
import com.example.chrysus.util.request.Request;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FCMRegisterService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String channelId = "chrysus-2";
        String channelName = "payment";

        Log.d("TEST", remoteMessage.getData().get("message"));
        Notification notification = new NotificationCompat.Builder(this)
                .setPriority(1)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setSmallIcon(R.mipmap.ic_launcher)
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

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        String uid = null;
        while (uid == null){
            uid = FirebaseAuthWrapper.getFirebaseAuthUid();
        }
        try {
            String registerTokenPayload = new JSONObject()
                    .put("account", uid)
                    .put("token", token)
                    .toString();
            String result = Request.post(new ConfigReader(this).getValue("register_device_url")
                    ,registerTokenPayload
                    ,"");

            Log.d("FCMRegister", registerTokenPayload);
            JSONObject resultJSON = new JSONObject(result);
            if (resultJSON.getBoolean("success")){
                Log.d("FCMRegister", "Register Success!");
            } else {
                Log.d("FCMRegister", "Register Failed!");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
