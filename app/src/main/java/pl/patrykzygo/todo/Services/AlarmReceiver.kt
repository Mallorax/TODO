package pl.patrykzygo.todo.Services

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import pl.patrykzygo.todo.R
import pl.patrykzygo.todo.ui.MainActivity

class AlarmReceiver: BroadcastReceiver (){

    //TODO: Broadcast receiver triggers operation at designated time but
    // notification doesn't show up, which is next thing to fix

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Alarm fired", Toast.LENGTH_LONG).show()
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT )
        val ringtoneURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val mediaPlayer = MediaPlayer.create(context, ringtoneURI)
        mediaPlayer.start()
        var notificationBuilder = NotificationCompat.Builder(context!!,
            AppNotificationChannels.ALARM_NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_add_alarm_24px)
            .setContentTitle("Test")
            .setContentText("Just a test if notification will pop")
            .setSound(ringtoneURI)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notificationBuilder.build())

    }
}