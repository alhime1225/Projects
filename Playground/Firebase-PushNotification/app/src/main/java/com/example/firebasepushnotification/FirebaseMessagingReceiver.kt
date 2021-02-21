package com.example.firebasepushnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingReceiver : FirebaseMessagingService() {
    /*
    If Notification contains any data payload, i.e it is received from the app server.
    If Notification contains any notification payload, i.e. it is sent via the Firebase Admin SDK.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // First case when notifications are received via
        // data event
        // Here, 'title' and 'message' are the assumed names
        // of JSON
        // attributes. Since here we do not have any data
        // payload, This section is commented out. It is
        // here only for reference purposes.
        /*if(remoteMessage.getData().size()>0){
            showNotification(remoteMessage.getData().get("title"),
                          remoteMessage.getData().get("message"));
        }*/

        // Second case when notification payload is
        // received.
        if (remoteMessage.getNotification() != null) {
            // Since the notification is received directly from
            // FCM, the title and the body can be fetched
            // directly as below.
            remoteMessage.notification?.title?.let {
                remoteMessage.notification!!.body?.let { it1 ->
                    showNotification(
                        it,
                        it1)
                }
            }
        }
    }

    // Method to get the custom Design for the display of
    // notification.
    fun getCustomDesign (title: String,
                        message : String) : RemoteViews{
        var remoteViews = RemoteViews(applicationContext.packageName, R.layout.notification)

        remoteViews.setTextViewText(R.id.title, title)
        remoteViews.setTextViewText(R.id.message, message)
        remoteViews.setImageViewResource(R.id.icon_only, R.drawable.ic_launcher_background)
        return remoteViews
    }

    fun showNotification(title : String,
                        message : String){
        // Pass the intent to switch to the MainActivity
        var intent = Intent(this, MainActivity::class.java)
        // Assign channel ID
        var channel_id = "notifacation_channel"
        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
        // the activities present in the activity stack,
        // on the top of the Activity that is to be launched
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Pass the intent to PendingIntent to start the
        // next Activity
        var pendingIntent = PendingIntent.getActivity(this,
                            0, intent,PendingIntent.FLAG_ONE_SHOT)
        // Create a Builder object using NotificationCompat
        // class. This will allow control over all the flags
        var value = arrayListOf(1000,1000,1000,1000,1000)
        var builder = NotificationCompat.Builder(applicationContext, channel_id)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        // A customized design for the notification can be
        // set only for Android versions 4.1 and above. Thus
        // condition for the same is checked here.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            builder = builder.setContent(getCustomDesign(title, message))
        } else {
            builder = builder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_background)
        }
        // Create an object of NotificationManager class to
        // notify the
        // user of events that happen in the background.
        var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Check if the Android Version is greater than Oreo
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var notificationChannel = NotificationChannel(channel_id, "web_app"
                , NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0, builder.build())
    }

}