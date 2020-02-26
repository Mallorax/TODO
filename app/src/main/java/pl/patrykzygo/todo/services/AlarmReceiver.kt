package pl.patrykzygo.todo.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import pl.patrykzygo.todo.R

class AlarmReceiver: BroadcastReceiver (){

    //TODO: Broadcast receiver triggers operation at designated time but
    // notification doesn't show up, which is next thing to fix
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent!!.extras
        val id = bundle.getLong("id")
        Toast.makeText(context, "Alarm fired: $id", Toast.LENGTH_LONG).show()
        val ringtoneURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val mediaPlayer = MediaPlayer.create(context, ringtoneURI)
        mediaPlayer.start()
        var notificationBuilder = NotificationCompat.Builder(context!!,
            AppNotificationChannels.ALARM_NOTIFICATION_CHANNEL)
            .setContentTitle("Notification number:$id")
            .setContentText("Content text")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_stat_access_alarms)
            .setAutoCancel(true)
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .setAutoCancel(true)

        var notification = notificationBuilder.build()

        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManager.notify(id.toInt(), notification)



    }

}