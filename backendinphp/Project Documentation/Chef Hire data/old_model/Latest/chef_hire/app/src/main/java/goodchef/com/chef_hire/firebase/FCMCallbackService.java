package goodchef.com.chef_hire.firebase;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;

import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.SplashScreen;


/**
 * Created by santosh on 14/01/2017.
 */

public class FCMCallbackService extends FirebaseMessagingService {

    private static final String TAG = "FCMCallbackService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "Welcome:" + remoteMessage.getFrom());
        try {
            Log.d(TAG, "From:" + remoteMessage.getFrom());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String message="";
        try {
            message =remoteMessage.getNotification().getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(message.length() == 0){
                message = remoteMessage.getData().get("message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Message Title:" + remoteMessage.getNotification().getTitle());
        Log.d(TAG, "Message Body:" + remoteMessage.getNotification().getBody());

        sendNotification(message);

    }



    public void sendNotification(String message) {
        int color = getResources().getColor(R.color.colorPrimary);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent = new Intent(this, SplashScreen.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);




        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setContentTitle(""+getString(R.string.app_name))// change "notification.getTitle()" to "getString(R.string.app_name"
                .setContentText(message)// change "notification.getBody()" to "message"
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(color)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))  // change "notification.getBody()" to "message"
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, builder.build());
    }

}