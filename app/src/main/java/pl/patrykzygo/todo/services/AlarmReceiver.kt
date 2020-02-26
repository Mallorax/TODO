package pl.patrykzygo.todo.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.ui.MainActivity


class AlarmReceiver: BroadcastReceiver (){

    //TODO: Broadcast receiver triggers operation at designated time but
    // notification doesn't show up, which is next thing to fix
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Alarm fired", Toast.LENGTH_LONG).show()
        val ringtoneURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val mediaPlayer = MediaPlayer.create(context, ringtoneURI)
        mediaPlayer.start()
        var notificationBuilder = NotificationCompat.Builder(context!!,
            AppNotificationChannels.ALARM_NOTIFICATION_CHANNEL)
            .setContentTitle("Test notification")
            .setContentText("Content text")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_stat_access_alarms)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .setAutoCancel(true)

        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManager.notify(1, notificationBuilder.build())

    }

}