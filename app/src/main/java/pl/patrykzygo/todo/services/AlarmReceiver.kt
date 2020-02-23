package pl.patrykzygo.todo.services

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
    companion object{
        private const val ALARM_TAG = "ALARM"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(ALARM_TAG, "start of onRecieve")
        setupNotificationChannel(context)
        Toast.makeText(context, "Alarm fired", Toast.LENGTH_LONG).show()
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT )
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
            .setChannelId("10001")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context!!)){
            notify(1, notificationBuilder.build())
        }
        Log.d(ALARM_TAG, "end of onRecieve")

    }

    private fun setupNotificationChannel(context: Context?){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                AppNotificationChannels.ALARM_NOTIFICATION_CHANNEL,
                "Default alarm channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager: NotificationManager =
                context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            Log.d(ALARM_TAG, "Notification channel created")
        }
    }
}